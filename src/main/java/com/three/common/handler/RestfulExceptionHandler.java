package com.three.common.handler;

import com.three.modules.manage.controller.ImagesController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * restful异常处理器
 * Created by three on 2017/9/13.
 */
@RestControllerAdvice
public class RestfulExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(RestfulExceptionHandler.class);

    @org.springframework.web.bind.annotation.ExceptionHandler(DataIntegrityViolationException.class)
    public Map<String,Object> handleDataIntegrityViolationException(DataIntegrityViolationException e){
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("result","0");
        map.put("msg","记录重复");
        return map;
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public Map<String,Object> handleException(Exception e){
        logger.error(e.getMessage());
        e.printStackTrace();
        Class<? extends Exception> aClass = e.getClass();
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("result","0");
        map.put("msg","未知错误:"+e.getMessage());
        return map;
    }

}
