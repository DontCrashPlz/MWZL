package com.pokong.mwzl.data.bean.personal;

import java.io.Serializable;

/**
 * Created on 2018/11/7 11:03
 * User: Zheng
 * E-mail: zhengCH12138@163.com
 */
public class LoginRequestBean implements Serializable {

    private String userName;
    private String password;
    private String loginType = "mobile";
    private String sysCode = "shop_store";
    private String teminalType = "app";

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLoginType() {
        return loginType;
    }

    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }

    public String getSysCode() {
        return sysCode;
    }

    public void setSysCode(String sysCode) {
        this.sysCode = sysCode;
    }

    public String getTeminalType() {
        return teminalType;
    }

    public void setTeminalType(String teminalType) {
        this.teminalType = teminalType;
    }

    @Override
    public String toString() {
        return "LoginRequestBean{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", loginType='" + loginType + '\'' +
                ", sysCode='" + sysCode + '\'' +
                ", teminalType='" + teminalType + '\'' +
                '}';
    }
}
