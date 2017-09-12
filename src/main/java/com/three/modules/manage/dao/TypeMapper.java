package com.three.modules.manage.dao;

import com.three.common.dao.base.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * Created by three on 2017/9/8.
 */
@Mapper
public interface TypeMapper<Type> extends BaseMapper<Type> {

    List<Map<String, Object>> queryArticleOfTypeAmount();

    List<Type> queryTypeOfArticles();
}
