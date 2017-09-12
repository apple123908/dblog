package com.three.modules.manage.service;

import com.github.pagehelper.PageInfo;
import com.three.common.domain.base.Page;
import com.three.common.exception.CustomException;
import com.three.modules.manage.domain.BgImages;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * Created by three on 2017/9/8.
 */
public interface ImagesService {
    PageInfo<BgImages> queryByPage(Page<BgImages> page);

    void addImage(MultipartFile file, String nowTime) throws IOException, CustomException;

    void deleteByName(String name);

    void deleteById(Integer id);

    void batchDelete(List<BgImages> list);

}
