package com.three.modules.manage.utils;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.three.common.exception.CustomException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.InputStream;

/**
 *
 * Created by three on 2017/9/8.
 */
@Component
public class QiNiu {

    private Logger logger = LoggerFactory.getLogger(QiNiu.class);

    @Value("${qiniu.ak}")
    private  String accessKey;
    @Value("${qiniu.sk}")
    private  String secretKey;
    @Value("${qiniu.content.bucket}")
    private  String bucket;
    @Value("${qiniu.url}")
    private  String url;

    /**
     * 图片上传到七牛服务器
     * @param ips
     * @param fileName 时间戳.图片类型后缀
     * @return
     */
    public  String uploadImage(InputStream ips, String fileName) throws CustomException {
        Configuration cfg = new Configuration(Zone.zone2());
        UploadManager uploadManager = new UploadManager(cfg);

        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);
        try {
            Response response = uploadManager.put(ips, fileName, upToken,null,null);
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            String key = putRet.key;
            return url+key;
        } catch (QiniuException e) {
            e.printStackTrace();
            logger.error("upload image to niuqi Server error!");
            throw new CustomException("上传到七牛错误"+e.getMessage());
        }
    }
}
