package com.three.modules.sys.service;

import com.three.modules.sys.domain.SysUser;

/**
 * Created by three on 2017/9/4.
 */
public interface UserService {

    SysUser findByUsername(String userName);

    //修改上次登陆信息（时间和ip）
    void editLoginInfo(SysUser user);
}
