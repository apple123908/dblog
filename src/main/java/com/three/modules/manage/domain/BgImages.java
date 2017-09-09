package com.three.modules.manage.domain;


import com.three.common.domain.base.Page;;import java.sql.Timestamp;

/**
 * Created by three on 2017/9/7.
 */
public class BgImages {

    private Integer id;

    private String name;

    private String url;

    private Timestamp createTime;

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
}
