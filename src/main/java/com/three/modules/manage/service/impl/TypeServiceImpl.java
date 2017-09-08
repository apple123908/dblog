package com.three.modules.manage.service.impl;

import com.three.modules.manage.dao.TypeMapper;
import com.three.modules.manage.domain.Type;
import com.three.modules.manage.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
