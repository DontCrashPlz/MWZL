package com.pokong.mwzl.data.bean.personal;

import java.io.Serializable;

/**
 * Created on 2018/11/7 11:03
 * User: Zheng
 * E-mail: zhengCH12138@163.com
 */
public class UpdateRequestBean implements Serializable {

    private String appType = "androind";
    private String useType = "store_app";

    public String getAppType() {
        return appType;
    }

    public void setAppType(String appType) {
        this.appType = appType;
    }

    public String getUseType() {
        return useType;
    }

    public void setUseType(String useType) {
        this.useType = useType;
    }

    @Override
    public String toString() {
        return "UpdateRequestBean{" +
                "appType='" + appType + '\'' +
                ", useType='" + useType + '\'' +
                '}';
    }
}
