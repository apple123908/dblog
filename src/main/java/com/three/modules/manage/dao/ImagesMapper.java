package com.three.modules.manage.dao;

import com.three.common.dao.base.BaseMapper;
import com.three.modules.manage.domain.BgImages;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by three on 2017/9/9.
 */
@Mapper
public interface ImagesMapper extends BaseMapper<BgImages>{
    void deleteByName(String name);

    //批量删除
    void batchDelete(List<BgImages> list);
}
