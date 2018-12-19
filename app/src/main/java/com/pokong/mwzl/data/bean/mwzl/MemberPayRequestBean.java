package com.pokong.mwzl.data.bean.mwzl;

import java.io.Serializable;

/**
 * Created on 2018/12/18 17:23
 * User: Zheng
 * E-mail: zhengCH12138@163.com
 */
public class MemberPayRequestBean implements Serializable {
    private String appToken;
    private String mobile;
    private double total;
    private String orderType = "shopOrder";
    private String terminalType = "app";
    private String payType = "balance";

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

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getTerminalType() {
        return terminalType;
    }

    public void setTerminalType(String terminalType) {
        this.terminalType = terminalType;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    @Override
    public String toString() {
        return "MemberPayRequestBean{" +
                "appToken='" + appToken + '\'' +
                ", mobile='" + mobile + '\'' +
                ", total=" + total +
                ", orderType='" + orderType + '\'' +
                ", terminalType='" + terminalType + '\'' +
                ", payType='" + payType + '\'' +
                '}';
    }
}
