package com.three.dao;

import com.three.domain.Friends;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by three on 2017/9/2.
 */
@Mapper
public interface LogTestMapper {

    Friends findById(int id);

    List<Friends> findAll();

}
