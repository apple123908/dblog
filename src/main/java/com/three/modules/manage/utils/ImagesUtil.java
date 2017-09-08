package com.three.modules.manage.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by three on 2017/9/8.
 */
public class ImagesUtil {

    /**
     * 根据文件名判断是否为图片格式
     * @param fileName
     * @return
     */
    public static boolean isImageFormat(String fileName){
        String reg = ".+(.JPEG|.jpeg|.JPG|.jpg|.GIF|.gif|.BMP|.bmp|.PNG|.png)$";
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(fileName.toLowerCase());
        return matcher.find();
    }
}
