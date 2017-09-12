package com.three.modules.manage.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.three.common.domain.base.Page;
import com.three.common.exception.CustomException;
import com.three.modules.manage.dao.ImagesMapper;
import com.three.modules.manage.domain.BgImages;
import com.three.modules.manage.service.ImagesService;
import com.three.modules.manage.utils.QiNiu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * Created by three on 2017/9/8.
 */
@Service
public class ImagesServiceImpl implements ImagesService {

    @Autowired
    ImagesMapper imagesMapper;

    @Autowired
    QiNiu qiNiu;

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

    @Override
    @Transactional
    public void addImage(MultipartFile file, String nowTime) throws IOException, CustomException {
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));//文件后缀
        String fileName=nowTime+suffix;//文件名称
        String url = qiNiu.uploadImage(file.getInputStream(), fileName);//文件上传到七牛
        //增加数据库数据
        imagesMapper.add(new BgImages(nowTime,url));
    }

    @Override
    public void deleteByName(String name) {
        imagesMapper.deleteByName(name);
    }

    @Override
    public void deleteById(Integer id) {
        imagesMapper.deleteById(id);
    }

    @Override
    public void batchDelete(List<BgImages> list) {
        imagesMapper.batchDelete(list);
    }
}
