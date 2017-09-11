package com.three.modules.manage.service;

import com.three.modules.manage.domain.Type;

import java.util.List;
import java.util.Map;

/**
 * Created by three on 2017/9/8.
 */
public interface TypeService {

    List<Type> queryAll();

    //查询文章type种类中各个数量
    List<Map<String,Object>> queryArticleOfTypeAmount();

    void deleteById(Integer id);

    void update(Type type);

    void addReturnId(Type type);
}
