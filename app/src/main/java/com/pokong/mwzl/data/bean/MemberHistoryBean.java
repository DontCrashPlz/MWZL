package com.pokong.mwzl.data.bean;

/**
 * 会员消费历史纪录数据
 * Created on 2019/2/22 17:21
 * User: Zheng
 * E-mail: zhengCH12138@163.com
 */
public class MemberHistoryBean {

    private String memberName;
    private String currentTime;
    private String amount;

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
