package com.pokong.mwzl.data.bean.business;

import com.pokong.mwzl.data.BaseRequestBean;

/**
 * Created on 2018/11/7 13:41
 * User: Zheng
 * E-mail: zhengCH12138@163.com
 */
public class OrderDetailRequestBean implements BaseRequestBean {

    private String appToken;
    private long orderId;

    public String getAppToken() {
        return appToken;
    }

    public void setAppToken(String appToken) {
        this.appToken = appToken;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return "OrderDetailRequestBean{" +
                "appToken='" + appToken + '\'' +
                ", orderId=" + orderId +
                '}';
    }
}
