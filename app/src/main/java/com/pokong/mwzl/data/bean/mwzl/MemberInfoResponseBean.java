package com.pokong.mwzl.data.bean.mwzl;

import java.io.Serializable;

/**
 * Created on 2018/12/18 16:45
 * User: Zheng
 * E-mail: zhengCH12138@163.com
 */
public class MemberInfoResponseBean implements Serializable {
    private String nickname;//用户名
    private String mobile;//电话号码
    private double availablebalance;//有效余额
    private double integral;//积分

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public double getAvailablebalance() {
        return availablebalance;
    }

    public void setAvailablebalance(double availablebalance) {
        this.availablebalance = availablebalance;
    }

    public double getIntegral() {
        return integral;
    }

    public void setIntegral(double integral) {
        this.integral = integral;
    }

    @Override
    public String toString() {
        return "MemberInfoResponseBean{" +
                "nickname='" + nickname + '\'' +
                ", mobile='" + mobile + '\'' +
                ", availablebalance=" + availablebalance +
                ", integral=" + integral +
                '}';
    }
}