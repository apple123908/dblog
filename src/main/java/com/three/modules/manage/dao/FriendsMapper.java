package com.three.modules.manage.dao;

import com.three.common.dao.base.BaseMapper;
import org.apache.ibatis.annotations.Mapper;


/**
 * Created by three on 2017/9/7.
 */
@Mapper
public interface FriendsMapper<Friends> extends BaseMapper<Friends>{


    //校验信息是否重复
    Integer verifyFriendInfo(Friends friends);




}
