package com.three.modules.sys.controller;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;

/**
 * 处理404这种错误的增强器
 * Created by three on 2017/9/16.
 */
@Controller
public class MainsiteErrorController implements ErrorController {
    private static final String ERROR_PATH = "/error";

    @RequestMapping(value=ERROR_PATH)
    public String handleError(HttpServletResponse response){
        int status = response.getStatus();
        if(status==404){
            return "modules/sys/404";
        }
        return "modules/sys/500";
    }

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }
}
