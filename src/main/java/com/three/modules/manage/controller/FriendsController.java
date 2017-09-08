package com.three.modules.manage.controller;

import com.three.common.annotation.LogAction;
import com.three.common.domain.R;
import com.three.modules.manage.domain.Friends;
import com.three.modules.manage.service.FriendsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by three on 2017/9/7.
 */
@Controller
@RequestMapping(value = "/manage/friendShip")
public class FriendsController {

    @Autowired
    FriendsService friendsService;

    @RequestMapping(value = "/toIndex")
    @LogAction(name="访问 博友链接管理")
    public String toFriendsIndex(Model model){
        //查询出所有的博友信息
        List<Friends> friends = friendsService.queryAll();
        model.addAttribute("friends",friends);

        return "modules/sys/manage/friends";
    }

    @RequestMapping(value = "/add")
    @ResponseBody
    public R add(@RequestBody Friends friends){
        friendsService.addReturnId(friends);
        Integer id = friends.getId();
        return R.ok(id+"");
    }

    /**
     * 修改
     * @param friends
     * @return
     */
    @RequestMapping(value = "/edit")
    @ResponseBody
    public R edit(@RequestBody Friends friends){
        friendsService.edit(friends);
        return new R();
    }

    /**
     * 删除
     * @param friends
     * @return
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public R delete(@RequestBody Friends friends){
        friendsService.deleteById(friends.getId());
        return new R();
    }


    /**
     * 在增加和修改的博友资料前进行校验内容是否存在
     * @param friends
     * @return
     */
    @RequestMapping(value = "/verify")
    @ResponseBody
    public R verify(@RequestBody Friends friends){
        boolean isExist=friendsService.verifyFriendInfo(friends);
        if(!isExist){
            return R.error("内容存在，不允许添加");
        }
        return new R();
    }




}
