package com.pokong.mwzl.data.bean.mwzl;

import com.pokong.mwzl.data.BaseRequestBean;

/**
 * Created on 2018/11/23 14:20
 * User: Zheng
 * E-mail: zhengCH12138@163.com
 */
public class LocationRequestBean implements BaseRequestBean {
    private String appToken;
    private double xLocation;
    private double yLocation;

    public String getAppToken() {
        return appToken;
    }

    public void setAppToken(String appToken) {
        this.appToken = appToken;
    }

    public double getxLocation() {
        return xLocation;
    }

    public void setxLocation(double xLocation) {
        this.xLocation = xLocation;
    }

    public double getyLocation() {
        return yLocation;
    }

    public void setyLocation(double yLocation) {
        this.yLocation = yLocation;
    }

    @Override
    public String toString() {
        return "LocationRequestBean{" +
                "appToken='" + appToken + '\'' +
                ", xLocation=" + xLocation +
                ", yLocation=" + yLocation +
                '}';
    }
}
