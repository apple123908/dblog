package com.three.modules.manage.service.impl;

import com.three.modules.manage.dao.FriendsMapper;
import com.three.modules.manage.domain.Friends;
import com.three.modules.manage.service.FriendsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by three on 2017/9/7.
 */
@Service
public class FriendsServiceImpl implements FriendsService {

    @Autowired
    FriendsMapper friendsMapper;

    @Override
    public int queryAmount() {
        return friendsMapper.queryAmount();
    }

    @Override
    public List<Friends> queryAll() {
        return friendsMapper.queryAll();
    }

    @Override
    public boolean verifyFriendInfo(Friends friends) {
        Integer integer = friendsMapper.verifyFriendInfo(friends);
        //存在返回false,不允许操作
        if(integer!=null){
            return false;
        }
        return true;
    }

    @Override
    public void edit(Friends friends) {
        friendsMapper.edit(friends);
    }

    @Override
    public void deleteById(Integer id) {
        friendsMapper.deleteById(id);
    }

    @Override
    public void add(Friends friends) {
        friendsMapper.add(friends);
    }
}
