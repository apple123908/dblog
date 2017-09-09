package com.three.modules.manage.controller;

import com.github.pagehelper.PageInfo;
import com.three.common.domain.base.Page;
import com.three.common.exception.CustomException;
import com.three.common.util.JsonUtil;
import com.three.modules.manage.domain.BgImages;
import com.three.modules.manage.service.ImagesService;
import com.three.modules.manage.utils.ImagesUtil;
import com.three.modules.manage.utils.QiNiu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;

/**
 * Created by three on 2017/9/8.
 */
@Controller
@RequestMapping(value = "/manage/images")
public class ImagesController {
    private Logger logger = LoggerFactory.getLogger(ImagesController.class);

    @Autowired
    QiNiu qiNiu;


    @Autowired
    ImagesService imagesService;


    /**
     * 去layer现实信纸页面
     * @return
     */
    @RequestMapping(value = "/showBgImages")
    public String showBgImages(){

        return "modules/sys/manage/showBgImages";
    }

    /**
     * 分页查询
     * @param page
     * @return
     */
    @RequestMapping(value = "/queryBgImagesByPage")
    @ResponseBody
    public String queryBgImagesByPage(@RequestBody Page<BgImages> page){
        PageInfo<BgImages> bgImagesPageInfo = imagesService.queryByPage(page);
        String cunrrentList = JsonUtil.object2String(bgImagesPageInfo);
        return cunrrentList;
    }

    /**
     * 富文本编辑器上传文件
     * @param request
     * @param file
     * @param funcNumber
     * @return
     */
    @RequestMapping("/ckeditor-upload-image")
    @ResponseBody
    public String ckeditorUploadImage(HttpServletRequest request, @RequestParam("upload") MultipartFile file,
                                      @RequestParam("CKEditorFuncNum")String funcNumber) throws IOException {
        //判断上传格式是否为图片格式
        boolean imageFormat = ImagesUtil.isImageFormat(file.getOriginalFilename());
        if(!imageFormat){
            return String.format("<script>alert('图片格式不支持');</script>");
        }

        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));//文件后缀
        String fileName=new Date().getTime()+suffix;
        String returnImageURL=null;
        try {
            //图片上传到七牛服务器
            returnImageURL = qiNiu.uploadImage(file.getInputStream(), fileName);
        } catch (CustomException e) {
            e.printStackTrace();
            logger.error(e.getMessage()+"upload image to niuqi Server error!");
            return String.format("<script>alert('图片上传服务器失败');</script>");
        }


        return String.format("<script>window.parent.CKEDITOR.tools.callFunction(%s, '%s')</script>", funcNumber, returnImageURL);
    }
}
