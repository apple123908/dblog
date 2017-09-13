package com.three.modules.manage.service;

import com.github.pagehelper.PageInfo;
import com.three.common.domain.base.Page;
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

    //分页查询
    PageInfo<Article> queryByPage(Page<Article> articlePage);

    Article queryById(int articleId);

    void edit(Article article);

    List<Article> queryLast5ArticleIncludeImageType();

    //全部包含type
    List<Article> queryAllIncludeType();

    Article queryAllById(Integer articleId);

    //获取访问量最高的5篇
    List<Article> queryMostArticles5();

    //上下2片（附近）
    List<Article> queryNearBy(Integer articleId);

    //点击量+1
    void addHits(Integer articleId);

    void deleteById(Integer articleId);
}
