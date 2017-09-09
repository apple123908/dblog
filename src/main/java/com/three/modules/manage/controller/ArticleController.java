package com.three.modules.manage.controller;

import com.three.modules.manage.domain.Article;
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


    @RequestMapping(value = "/add")
    @ResponseBody
    public String add(@RequestBody Article article){

        return null;
    }
}
