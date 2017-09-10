package com.three.modules.manage.controller;

import com.github.pagehelper.PageInfo;
import com.three.common.annotation.LogAction;
import com.three.common.domain.R;
import com.three.common.domain.base.Page;
import com.three.modules.manage.domain.Article;
import com.three.modules.manage.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by three on 2017/9/9.
 */
@Controller
@RequestMapping(value = "/manage/article")
public class ArticleController {

    @Autowired
    ArticleService articleService;


    @RequestMapping(value = "/add")
    @ResponseBody
    @LogAction(name="新增文章")
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
    public String queryByPage(Page<Article> articlePage){
        PageInfo<Article>page=articleService.queryByPage(articlePage);
        return null;
    }

}
