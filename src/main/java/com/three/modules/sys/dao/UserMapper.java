package com.three.modules.sys.dao;

import com.three.modules.sys.domain.SysUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by three on 2017/9/4.
 */

@Mapper
public interface UserMapper {

    SysUser findByUsername(String userName);
}
