package com.three.modules.manage.dao;

import com.three.common.dao.base.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * Created by three on 2017/9/8.
 */
@Mapper
public interface LabelMapper<Label> extends BaseMapper<Label>{

    List<Map<String,Object>> queryArticleOfLabelAmount();
}
