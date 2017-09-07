package com.three.modules.sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by three on 2017/9/4.
 */
@Controller
public class LoginController {

    /**
     * 通往登陆页面
     * @return
     */
    @RequestMapping(value = "/manage/toLogin")
    public String toLogin(){
        System.out.println("1");
        return "login";
    }

    /**
     * 去主页
     * @return
     */
    @RequestMapping(value = "/manage/toMain")
    public String toMain(){


        return "modules/sys/manage/home";
    }
}
