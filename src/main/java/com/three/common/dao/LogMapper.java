package com.three.common.dao;

import com.three.common.domain.SysLog;
import com.three.modules.sys.domain.SysUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by three on 2017/9/7.
 */
@Mapper
public interface LogMapper {

    void addLog(SysLog log);
}
