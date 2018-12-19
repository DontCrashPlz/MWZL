package com.pokong.mwzl.data.bean.mwzl;

import java.io.Serializable;

/**
 * Created on 2018/12/18 16:45
 * User: Zheng
 * E-mail: zhengCH12138@163.com
 */
public class MemberInfoRequestBean implements Serializable {
    private String appToken;
    private String mobile;

    public String getAppToken() {
        return appToken;
    }

    public void setAppToken(String appToken) {
        this.appToken = appToken;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Override
    public String toString() {
        return "MemberInfoRequestBean{" +
                "appToken='" + appToken + '\'' +
                ", mobile='" + mobile + '\'' +
                '}';
    }
}
