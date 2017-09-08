package com.three.common.domain;

import java.util.HashMap;

/**
 * result =>1表示通过
 * result =>0表示未通过
 * Created by three on 2017/9/7.
 */
public class R extends HashMap<String,Object>{
    private static final long serialVersionUID = 1L;


    public R() {
        put("result", 1);
    }

    public static R ok(String msg) {
        R r = new R();
        r.put("result", 1);
        r.put("msg", msg);
        return r;
    }

    public static R ok2Obj(Object obj){
        R r = new R();
        r.put("result", 1);
        r.put("msg", obj);
        return r;
    }

    public static R error(String msg){
        R r = new R();
        r.put("result", 0);
        r.put("msg", msg);
        return r;
    }
}
