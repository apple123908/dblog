package com.three.modules.sys.controller;

import com.three.common.annotation.LogAction;
import com.three.common.util.JsonUtil;
import com.three.modules.manage.domain.Article;
import com.three.modules.manage.service.ArticleService;
import com.three.modules.manage.service.FriendsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


/**
 * Created by three on 2017/9/4.
 */
@Controller
public class LoginController {


    @Autowired
    ArticleService articleService;

    @Autowired
    FriendsService friendsService;

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
     * 去主页(仪表盘)
     * @return
     */
    @LogAction(name="访问 仪表盘（主页）")
    @RequestMapping(value = "/manage/toMain")
    public String toMain(Model model){
        //查询出发布、博友数量
        int articleAmount = articleService.queryAmount();
        int friendsAmount = friendsService.queryAmount();
        //查询最近5篇文章
        List<Article> articles = articleService.queryLast5Article();
        model.addAttribute("AAmount",articleAmount);
        model.addAttribute("FAmount",friendsAmount);
        model.addAttribute("last5", JsonUtil.object2String(articles));

        return "modules/sys/manage/dashboard";
    }
}
