package com.three.modules.sys.service.impl;

import com.three.modules.sys.dao.UserMapper;
import com.three.modules.sys.domain.SysUser;
import com.three.modules.sys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by three on 2017/9/4.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public SysUser findByUsername(String userName) {
        SysUser byUsername = userMapper.findByUsername(userName);
        return byUsername;
    }
}
