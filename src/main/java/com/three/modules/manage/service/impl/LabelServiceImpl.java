package com.three.modules.manage.service.impl;

import com.three.modules.manage.dao.LabelMapper;
import com.three.modules.manage.domain.Label;
import com.three.modules.manage.service.LabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
