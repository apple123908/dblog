package com.three.modules.manage.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.three.common.domain.base.Page;
import com.three.modules.manage.dao.ImagesMapper;
import com.three.modules.manage.domain.BgImages;
import com.three.modules.manage.service.ImagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by three on 2017/9/8.
 */
@Service
public class ImagesServiceImpl implements ImagesService {

    @Autowired
    ImagesMapper imagesMapper;

    /**
     * 分页查询
     * @param page
     * @return
     */
    @Override
    public PageInfo<BgImages> queryByPage(Page<BgImages> page) {

        com.github.pagehelper.Page<BgImages> myPage = PageHelper.startPage(page.getCurrentPage(), page.getPageSize(),page.getSort());
        imagesMapper.queryAll();
        return myPage.toPageInfo();
    }
}
