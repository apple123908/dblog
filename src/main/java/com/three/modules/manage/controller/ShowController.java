package com.three.modules.manage.controller;

import com.three.common.util.HtmlUtil;
import com.three.common.util.JsonUtil;
import com.three.modules.manage.domain.Article;
import com.three.modules.manage.domain.Friends;
import com.three.modules.manage.service.ArticleService;
import com.three.modules.manage.service.FriendsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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

    /**
     * 主页
     * @param model
     * @return
     */
    @RequestMapping(value = "/index")
    public String toMain(Model model){
        //最近5篇
        List<Article> articlesLast5 = articleService.queryLast5ArticleIncludeImageType();
        //删除html标签，并限制首页内容太多
        for (Article a: articlesLast5){
            String describe = HtmlUtil.getTextFromHtml(a.getContent());
            if(describe!=null){
                int length=describe.length()<=50?describe.length():50;
                describe = describe.substring(0, length);
                a.setContent(describe+"...");
            }
        }

        //所有博友信息
        List<Friends> friendss = friendsService.queryAll();

        model.addAttribute("articles", JsonUtil.object2String(articlesLast5));
        model.addAttribute("allFriendJson",friendss);

        return "modules/sys/show/index";
    }

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
}
