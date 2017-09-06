package com.three.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.three.dao.LogTestMapper;
import com.three.domain.Friends;
import com.three.service.LogTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Created by three on 2017/9/2.
 */
@Service
public class LogTestServiceImpl implements LogTestService{

    @Autowired
    LogTestMapper logTestMapper;

    @Override
    public Friends queryOne(int i) {
        Friends byId = logTestMapper.findById(i);
        return byId;
    }

    @Override
    public PageInfo<Friends> findAll() {
        Page<Friends> page= PageHelper.startPage(1,1);
        logTestMapper.findAll();
        return page.toPageInfo();
    }
}
