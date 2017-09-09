package com.three.modules.manage.service;

import com.three.modules.manage.domain.Article;

import java.util.List;

/**
 * Created by three on 2017/9/7.
 */
public interface ArticleService {

    //总数
    int queryAmount();

    //最近5篇
    List<Article> queryLast5Article();

    void add(Article article);
}
