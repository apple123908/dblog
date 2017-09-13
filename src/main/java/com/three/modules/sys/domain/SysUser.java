package com.three.modules.sys.domain;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 用户
 * Created by three on 2017/9/4.
 */
public class SysUser implements Serializable {

    private Integer id;
    private String username;
    private String password;

    private String salt;

    private String lastIp;
    private Timestamp lastTime;

    public SysUser() {
    }

    public SysUser(Integer id, String lastIp, Timestamp lastTime) {
        this.id = id;
        this.lastIp = lastIp;
        this.lastTime = lastTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getLastIp() {
        return lastIp;
    }

    public void setLastIp(String lastIp) {
        this.lastIp = lastIp;
    }

    public Timestamp getLastTime() {
        return lastTime;
    }

    public void setLastTime(Timestamp lastTime) {
        this.lastTime = lastTime;
    }
}
