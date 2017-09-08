package com.three.modules.manage.service.impl;

import com.three.modules.manage.dao.ArticleMapper;
import com.three.modules.manage.domain.Article;
import com.three.modules.manage.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by three on 2017/9/7.
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    ArticleMapper articleMapper;

    @Override
    public int queryAmount() {

        return articleMapper.queryAmount();
    }

    @Override
    public List<Article> queryLast5Article() {
        return articleMapper.queryLast5Article();
    }
}
