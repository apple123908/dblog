package com.three.common.service;

import com.three.common.dao.LogMapper;
import com.three.common.domain.SysLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by three on 2017/9/7.
 */
@Service
public class LogServiceImpl implements LogService {

    @Autowired
    LogMapper logMapper;

    //增加操作日志
    @Override
    public void addLog(SysLog log) {
        logMapper.addLog(log);
    }
}
