package com.three.common.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by three on 2017/9/7.
 */
public class JsonUtil {

    /**
     * 转成json格式字符串
     * @param obj
     * @return
     */
    public static String object2String(Object obj){
        GsonBuilder gb = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss");
        Gson gson = gb.create();
        String str = gson.toJson(obj);
        return str;
    }
}
