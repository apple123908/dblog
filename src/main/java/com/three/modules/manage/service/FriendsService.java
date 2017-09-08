package com.three.modules.manage.service;

import com.three.modules.manage.domain.Friends;

import java.util.List;

/**
 * Created by three on 2017/9/7.
 */
public interface FriendsService {
    int queryAmount();

    List<Friends> queryAll();

    boolean verifyFriendInfo(Friends friends);

    void edit(Friends friends);

    void deleteById(Integer id);

    void add(Friends friends);
}
