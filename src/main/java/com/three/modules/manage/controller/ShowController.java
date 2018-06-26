package com.three.modules.manage.controller;

import com.three.common.util.HtmlUtil;
import com.three.common.util.JsonUtil;
import com.three.modules.manage.domain.Article;
import com.three.modules.manage.domain.Friends;
import com.three.modules.manage.domain.Type;
import com.three.modules.manage.service.ArticleService;
import com.three.modules.manage.service.FriendsService;
import com.three.modules.manage.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * 展示
 * Created by three on 2017/9/12.
 */
@Controller
@RequestMapping(value = "/show")
public class ShowController {

    @Autowired
    ArticleService articleService;

    @Autowired
    FriendsService friendsService;

    @Autowired
    TypeService typeService;

    @Value("${qiniu.url}")
    private String qiniuBaseUrl;



    /**
     * 展示所有的文章，按照时间（新->旧）
     * @param model
     * @return
     */
    @RequestMapping(value = "/allArticles")
    public String queryAllArticle(Model model){
        List<Article> list=articleService.queryAllIncludeType();
        model.addAttribute("allArticle",list);
        return "modules/sys/show/allArticlelist";
    }

    /**
     * 查询所有类别下文章的信息
     * @param model
     * @return
     */
    @RequestMapping(value = "/showTypeList")
    public String showTypeList(Model model){
        List<Type> list=typeService.queryTypeOfArticles();
        String result=JsonUtil.object2String(list);
        model.addAttribute("typeOfArticles",result);
        return "modules/sys/show/typeShow";
    }

    @RequestMapping(value = "/specifiedArticle/{articleId}",method = RequestMethod.GET)
    public String specifiedArticle(@PathVariable Integer articleId, Model model){
        //增加访问量
        articleService.addHits(articleId);
        //单片文章内容信息
        Article article=articleService.queryAllById(articleId);
        if(article.getAccessory()!=null&&!"".equals(article.getAccessory())){
            article.setAccessory(qiniuBaseUrl+article.getAccessory());
        }
        //最新的5篇文章的标题
        List<Article> articlesLast5 = articleService.queryLast5Article();

        //点击最高的排行榜文章
        List<Article> mostHitsArticles = articleService.queryMostArticles5();

        //查询出上一篇文章和下一篇文章（按照时间）
        List<Article> nearby = articleService.queryNearBy(articleId);

        model.addAttribute("article",JsonUtil.object2String(article));
        model.addAttribute("ultramodern",articlesLast5);
        model.addAttribute("hits",mostHitsArticles);
        model.addAttribute("nearby",nearby);
        return "modules/sys/show/specifiedArticle";
    }


}
