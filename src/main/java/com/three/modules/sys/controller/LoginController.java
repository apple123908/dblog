package com.three.modules.sys.controller;

import com.three.common.annotation.LogAction;
import com.three.common.util.Const;
import com.three.common.util.JsonUtil;
import com.three.modules.manage.domain.Article;
import com.three.modules.manage.service.ArticleService;
import com.three.modules.manage.service.FriendsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
    public String toLogin(HttpServletRequest request){
        HttpSession session = request.getSession();
        Object user = session.getAttribute(Const.CURRENT_USER);
        if(user!=null){
            //重定向
            return "redirect:/manage/toMain";
        }
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

    @RequestMapping(value = "/manage/validateDemo")
    public String validateDemo(){
        return "modules/sys/manage/veeValidateDemo";
    }

}
