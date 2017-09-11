package com.three.modules.manage.service.impl;

import com.three.modules.manage.dao.LabelMapper;
import com.three.modules.manage.domain.Label;
import com.three.modules.manage.service.LabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by three on 2017/9/8.
 */
@Service
public class LabelServiceImpl implements LabelService {

    @Autowired
    LabelMapper labelMapper;

    @Override
    public List<Label> queryAll() {
        return labelMapper.queryAll();
    }

    @Override
    public List<Map<String, Object>> queryArticleOfLabelAmount() {
        List<Map<String, Object>>list=labelMapper.queryArticleOfLabelAmount();
        for(Map<String,Object> map:list){
           if(map.get("aId")==null){
               map.put("amount",0);
           }
        }
        return list;
    }

    @Override
    public void deleteById(Integer id) {
        labelMapper.deleteById(id);
    }

    @Override
    public void update(Label label) {
        labelMapper.update(label);
    }
}
