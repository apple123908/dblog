package com.three.modules.sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by three on 2017/9/5.
 */

@Controller
public class LayOutController {

    @RequestMapping(value = "/manage/footer")
    public String footer(){
        return "modules/sys/layout/footer";
    }

    @RequestMapping(value = "/manage/page")
    public String page(){
        return "modules/sys/manage/layout/layout";
    }

    @RequestMapping(value = "/manage/home2")
    public String ce(){
        return "modules/sys/home2";
    }
}
