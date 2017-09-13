package com.three.modules.manage.controller;

import com.github.pagehelper.PageInfo;
import com.three.common.annotation.LogAction;
import com.three.common.domain.R;
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
import java.util.List;

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
     * 去信纸管理
     * @return
     */
    @RequestMapping(value = "/toBgImagesIndex")
    @LogAction(name="访问 信纸管理")
    public String toBgImagesIndex(){

        return "modules/sys/manage/images";
    }

    /**
     * 分页查询
     * @param page
     * @return
     */
    @RequestMapping(value = "/queryBgImagesByPage")
    @ResponseBody
    @LogAction(name="信纸管理 分页查询")
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

    /*@RequestMapping(value = "/upload")
    public String upload(){
       return  "modules/sys/manage/upload";
    }*/


    /**
     * 新增，上传图片
     * @return
     */
    @ResponseBody
    @RequestMapping("/uploadImages")
    @LogAction(name="新增信纸")
    public R addImage(@RequestParam("file") MultipartFile file){
        String nowTime=new Date().getTime()+"";
        try {
            imagesService.addImage(file,nowTime);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return R.error(e.getMessage());
        } catch (CustomException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return R.error(e.getMessage());
        }
        return R.ok(nowTime);
    }

    /**
     * 根据文件name(其实是时间戳)删除图片信息
     * @param bgImages
     * @return
     */
    @RequestMapping(value = "/deleteByName")
    @ResponseBody
    @LogAction(name="删除信纸（在新增页面）")
    public R deleteByName(@RequestBody BgImages bgImages){
        imagesService.deleteByName(bgImages.getName());
        return new R();
    }

    /**
     * 根据id删除图片
     * @param bgImages
     * @return
     */
    @RequestMapping(value = "/deleteById")
    @ResponseBody
    @LogAction(name="删除信纸")
    public R deleteById(@RequestBody BgImages bgImages){
        imagesService.deleteById(bgImages.getId());
        return new R();
    }

    @RequestMapping(value = "/batchDelete")
    @ResponseBody
    @LogAction(name="批量删除信纸")
    public R batchDelete(@RequestBody List<BgImages> list){
        imagesService.batchDelete(list);
        return new R();
    }

}
