package com.pokong.mwzl.data.bean;

/**
 * Created by Zheng on 2018/10/13.
 */

public class UserBean {

    private String userName;//用户名
    private String nickName;//昵称
    private int sex;//性别
    private String mobile;
    private String birthday;
    private boolean isAuthentication;
    private String realName;
    private double balance;
    private double integral;

    private String appToken;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public boolean isAuthentication() {
        return isAuthentication;
    }

    public void setAuthentication(boolean authentication) {
        isAuthentication = authentication;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getIntegral() {
        return integral;
    }

    public void setIntegral(double integral) {
        this.integral = integral;
    }

    @Override
    public String toString() {
        return "UserBean{" +
                "userName='" + userName + '\'' +
                ", nickName='" + nickName + '\'' +
                ", sex=" + sex +
                ", mobile='" + mobile + '\'' +
                ", birthday='" + birthday + '\'' +
                ", isAuthentication=" + isAuthentication +
                ", realName='" + realName + '\'' +
                ", balance=" + balance +
                ", integral=" + integral +
                '}';
    }

}
