package com.three.modules.manage.service;

import com.github.pagehelper.PageInfo;
import com.three.common.domain.base.Page;
import com.three.modules.manage.domain.BgImages;

/**
 * Created by three on 2017/9/8.
 */
public interface ImagesService {
    PageInfo<BgImages> queryByPage(Page<BgImages> page);
}
