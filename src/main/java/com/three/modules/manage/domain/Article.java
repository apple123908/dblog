package com.three.modules.manage.domain;

import com.three.modules.sys.domain.SysUser;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 文章
 * Created by three on 2017/9/7.
 */
public class Article {

    private Integer id;

    private String title;

    private String content;

    private SysUser author;

    private Type type;//分类

    private Set<Label> labels;//标签

    private Timestamp createTime;

    private boolean status;//状态，已发布-草稿

    private boolean isDiscuss;//是否接受讨论

    private boolean isPublicity;//文章是否公开

    private Integer browseCount;//浏览数

    private BgImages bgImages;//文章对应的信纸

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public SysUser getAuthor() {
        return author;
    }

    public void setAuthor(SysUser author) {
        this.author = author;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Set<Label> getLabels() {
        return labels;
    }

    public void setLabels(Set<Label> labels) {
        this.labels = labels;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean isDiscuss() {
        return isDiscuss;
    }

    public void setDiscuss(boolean discuss) {
        isDiscuss = discuss;
    }

    public boolean isPublicity() {
        return isPublicity;
    }

    public void setPublicity(boolean publicity) {
        isPublicity = publicity;
    }

    public Integer getBrowseCount() {
        return browseCount;
    }

    public void setBrowseCount(Integer browseCount) {
        this.browseCount = browseCount;
    }

    public BgImages getBgImages() {
        return bgImages;
    }

    public void setBgImages(BgImages bgImages) {
        this.bgImages = bgImages;
    }


}
