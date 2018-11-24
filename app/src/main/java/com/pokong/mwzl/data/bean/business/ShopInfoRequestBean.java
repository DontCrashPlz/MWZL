package com.pokong.mwzl.data.bean.business;

import com.pokong.mwzl.data.BaseRequestBean;

/**
 * Created on 2018/11/7 13:45
 * User: Zheng
 * E-mail: zhengCH12138@163.com
 */
public class ShopInfoRequestBean implements BaseRequestBean {

    private String appToken;

    public String getAppToken() {
        return appToken;
    }

    public void setAppToken(String appToken) {
        this.appToken = appToken;
    }

    @Override
    public String toString() {
        return "ShopInfoRequestBean{" +
                "appToken='" + appToken + '\'' +
                '}';
    }
}
