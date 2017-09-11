package com.three.modules.manage.service;

import com.three.modules.manage.domain.Label;

import java.util.List;
import java.util.Map;

/**
 * Created by three on 2017/9/8.
 */
public interface LabelService {

    List<Label> queryAll();

    //查询文章中各个label的数量
    List<Map<String,Object>> queryArticleOfLabelAmount();

    void deleteById(Integer id);

    void update(Label label);

    void addReturnId(Label label);
}
