package com.three.modules.manage.controller;

import com.three.common.util.HtmlUtil;
import com.three.common.util.JsonUtil;
import com.three.modules.manage.domain.Article;
import com.three.modules.manage.domain.Friends;
import com.three.modules.manage.service.ArticleService;
import com.three.modules.manage.service.FriendsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by lixiao on 2018/6/26.
 */
@Controller
public class MainController {

    @Autowired
    ArticleService articleService;

    @Autowired
    FriendsService friendsService;

    /**
     * 主页
     * @param model
     * @return
     */
    @RequestMapping(value = "/")
    public String toMain(Model model){
        //最近5篇
        List<Article> articlesLast5 = articleService.queryLast5ArticleIncludeImageType();
        //删除html标签，并限制首页内容太多
        for (Article a: articlesLast5){
            String describe = HtmlUtil.getTextFromHtml(a.getContent());
            if(describe!=null){
                int length=describe.length()<=50?describe.length():50;
                describe = describe.substring(0, length);
                a.setContent(describe+"...");
            }
        }

        //所有博友信息
        List<Friends> friendss = friendsService.queryAll();

        model.addAttribute("articles", JsonUtil.object2String(articlesLast5));
        model.addAttribute("allFriendJson",friendss);

        return "modules/sys/show/index";
    }
}
