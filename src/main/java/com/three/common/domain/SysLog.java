package com.three.common.domain;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 日志
 * Created by three on 2017/9/7.
 */
public class SysLog implements Serializable {

    private Integer id;
    private String operator;//操作人（指用户名）
    private String action;//动作
    private Timestamp startTime;
    private String ip;
    private String parameterBefore;//参数前

    public SysLog() {
    }

    public SysLog(String operator, String action, Timestamp startTime, String ip) {
        this.operator = operator;
        this.action = action;
        this.startTime = startTime;
        this.ip = ip;
    }

    public SysLog(String operator, String action, Timestamp startTime, String ip, String parameterBefore) {
        this.operator = operator;
        this.action = action;
        this.startTime = startTime;
        this.ip = ip;
        this.parameterBefore = parameterBefore;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getParameterBefore() {
        return parameterBefore;
    }

    public void setParameterBefore(String parameterBefore) {
        this.parameterBefore = parameterBefore;
    }
}
