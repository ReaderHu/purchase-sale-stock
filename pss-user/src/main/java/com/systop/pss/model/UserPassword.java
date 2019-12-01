package com.systop.pss.model;

import java.util.Date;

public class UserPassword {
    /**
     * ID
     */
    private Integer pwdId;

    /**
     * 用户ID
     */
    private String uuId;

    /**
     * 密码
     */
    private String pwd;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 更新用户
     */
    private String updateUser;

    public Integer getPwdId() {
        return pwdId;
    }

    public void setPwdId(Integer pwdId) {
        this.pwdId = pwdId;
    }

    public String getUuId() {
        return uuId;
    }

    public void setUuId(String uuId) {
        this.uuId = uuId == null ? null : uuId.trim();
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd == null ? null : pwd.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser == null ? null : updateUser.trim();
    }
}