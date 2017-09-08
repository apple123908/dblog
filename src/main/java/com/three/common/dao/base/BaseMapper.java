package com.three.common.dao.base;

import com.three.modules.manage.domain.Friends;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by three on 2017/9/8.
 */
@Mapper
public interface BaseMapper<T> {

    void edit(T t);

    void add(T t);

    void deleteById(Integer id);

    List<T> queryAll();

    //查询总记录数
    int queryAmount();
}
