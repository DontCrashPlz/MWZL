package com.pokong.mwzl.data.bean.business;

import java.io.Serializable;

/**
 * Created on 2018/11/7 13:42
 * User: Zheng
 * E-mail: zhengCH12138@163.com
 */
public class OrderReadyRequestBean implements Serializable {

    private String appToken;
    private String orderId;

    public String getAppToken() {
        return appToken;
    }

    public void setAppToken(String appToken) {
        this.appToken = appToken;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return "OrderReadyRequestBean{" +
                "appToken='" + appToken + '\'' +
                ", orderId='" + orderId + '\'' +
                '}';
    }
}
