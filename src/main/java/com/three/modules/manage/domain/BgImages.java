package com.three.modules.manage.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.three.common.domain.base.Page;;import java.sql.Timestamp;

/**
 * Created by three on 2017/9/7.
 */
public class BgImages {

    private Integer id;

    private String name;

    private String url;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp createTime;

    private boolean isCite;//是否被引用，用于判断是否可以被删除

    public BgImages() {
    }

    public BgImages(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public boolean isCite() {
        return isCite;
    }

    public void setCite(String cite) {
        if(null!=cite&&!"".equals(cite)){
            this.isCite=true;
        }else{
            this.isCite=false;
        }
    }
}
