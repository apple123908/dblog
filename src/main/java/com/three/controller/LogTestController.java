package com.three.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.three.common.annotation.LogAction;
import com.three.dao.LogTestMapper;
import com.three.domain.Friends;
import com.three.service.LogTestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by three on 2017/9/2.
 */
@RestController
public class LogTestController {

    private final static Logger logger = LoggerFactory.getLogger(LogTestController.class);

    @Autowired
    LogTestService logTestService;



    @LogAction(name="add")
    @RequestMapping(value = "/add")
    public String add(HttpServletRequest request){
        request.setAttribute("cs","cs");

        return "add";
    }

    @LogAction(name="save")
    @RequestMapping(value = "/save")
    public String save(){

        return "save";
    }

    @RequestMapping(value = "/mybatisDruidTest")
    public String mybatisDruidTest(){
        Friends friends = logTestService.queryOne(1);
        System.out.println(friends.getUrl());
        logger.debug("Test -> 记录debug日志");
        logger.info("Test -> 访问了index方法");
        logger.error("Test -> 记录error错误日志");
        System.out.println("1");
        return "2";
    }


    @RequestMapping(value = "/page")
    public String page(){
        Page<Friends> page= PageHelper.startPage(1,1);
        PageInfo<Friends> all = logTestService.findAll();
        List<Friends> list = all.getList();
        int pages = all.getPages();
        long total = all.getTotal();
        System.out.println(pages);
        System.out.println(total);


        return "page";

    }

}
