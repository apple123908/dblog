package com.three.modules.manage.dao;

import com.three.common.dao.base.BaseMapper;
import com.three.modules.manage.domain.Article;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * Created by three on 2017/9/7.
 */
@Mapper
public interface ArticleMapper extends BaseMapper<Article>{

    int queryAmount();

    List<Article> queryLast5Article();

    //article_label中间表添加数据
    void addMiddle(List<Map<String, Object>> list);


    void deleteMiddle(Integer id);
}
