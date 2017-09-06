package com.three.service;

import com.github.pagehelper.PageInfo;
import com.three.domain.Friends;


/**
 * Created by three on 2017/9/2.
 */
public interface LogTestService {

    Friends queryOne(int i);

    PageInfo<Friends> findAll();
}
