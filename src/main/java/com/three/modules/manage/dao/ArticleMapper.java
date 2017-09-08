package com.three.modules.manage.dao;

import com.three.modules.manage.domain.Article;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by three on 2017/9/7.
 */
@Mapper
public interface ArticleMapper {

    int queryAmount();

    List<Article> queryLast5Article();
}
