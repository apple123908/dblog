package com.three.modules.manage.domain;

import java.util.Set;

/**
 * Created by three on 2017/9/7.
 */
public class Label {

    private Integer id;

    private String name;

    private Set<Article> articles;

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

    public Set<Article> getArticles() {
        return articles;
    }

    public void setArticles(Set<Article> articles) {
        this.articles = articles;
    }
}
