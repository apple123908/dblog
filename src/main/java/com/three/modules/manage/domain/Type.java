package com.three.modules.manage.domain;

import java.util.List;

/**
 * Created by three on 2017/9/7.
 */
public class Type {

    private Integer id;

    private String name;

    private List<Article> articles;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }
}
