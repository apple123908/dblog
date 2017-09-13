package com.three.modules.manage.controller;

import com.github.pagehelper.PageInfo;
import com.three.common.annotation.LogAction;
import com.three.common.domain.R;
import com.three.common.domain.base.Page;
import com.three.common.util.JsonUtil;
import com.three.modules.manage.domain.Article;
import com.three.modules.manage.domain.Label;
import com.three.modules.manage.domain.Type;
import com.three.modules.manage.service.ArticleService;
import com.three.modules.manage.service.LabelService;
import com.three.modules.manage.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by three on 2017/9/9.
 */
@Controller
@RequestMapping(value = "/manage/article")
public class ArticleController {

    @Autowired
    ArticleService articleService;

    @Autowired
    TypeService typeService;

    @Autowired
    LabelService labelService;


    @RequestMapping(value = "/add")
    @ResponseBody
    @LogAction(name="发布文章")
    public R add(@RequestBody Article article){

        articleService.add(article);
        return new R();
    }


    @RequestMapping(value = "/toManageArticleIndex")
    @LogAction(name = "访问 文章管理")
    public String toManageArticleIndex(){

        return "modules/sys/manage/article";
    }

    /**
     * 分页查询
     * @return
     */
    @RequestMapping(value = "/queryByPage")
    @ResponseBody
    @LogAction(name="文章管理-分页查询")
    public String queryByPage(@RequestBody Page<Article> articlePage){
        PageInfo<Article>page=articleService.queryByPage(articlePage);
        String jsonArticle = JsonUtil.object2String(page);
        return jsonArticle;
    }

    @RequestMapping(value = "/toEditArticle/{articleId}")
    public String toEditArticle(@PathVariable int articleId, Model model){
        Article article=articleService.queryById(articleId);

        //查询所有的类别和标签（select）
        List<Type> types=typeService.queryAll();

        List<Label> labels=labelService.queryAll();

        model.addAttribute("types",types);
        model.addAttribute("label",labels);
        model.addAttribute("article",article);
        return "modules/sys/manage/toEditArticle";
    }


    /**
     * 修改
     * @param article
     * @return
     */
    @RequestMapping(value = "/edit")
    @ResponseBody
    @LogAction(name="修改文章")
    public R editArticle(@RequestBody Article article){
        articleService.edit(article);
        return new R();
    }

    /**
     * 删除
     * @return
     */
    @RequestMapping(value = "/deleteById")
    @ResponseBody
    @LogAction(name="删除文章")
    public R deleteById(@RequestBody Article article){
        articleService.deleteById(article.getId());
        return new R();
    }
}
