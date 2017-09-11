package com.three.modules.manage.service.impl;

import com.three.modules.manage.dao.TypeMapper;
import com.three.modules.manage.domain.Type;
import com.three.modules.manage.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by three on 2017/9/8.
 */
@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    TypeMapper typeMapper;

    @Override
    public List<Type> queryAll() {
        return typeMapper.queryAll();
    }

    @Override
    public List<Map<String, Object>> queryArticleOfTypeAmount() {
        List<Map<String, Object>> list=typeMapper.queryArticleOfTypeAmount();
        for(Map<String,Object> map : list){
            if(map.get("aId")==null){
                map.put("amount",0);
            }
        }

        return list;
    }

    @Override
    public void deleteById(Integer id) {
        typeMapper.deleteById(id);
    }

    @Override
    public void update(Type type) {
        typeMapper.update(type);
    }

    @Override
    public void addReturnId(Type type) {
        typeMapper.addReturnId(type);
    }
}
