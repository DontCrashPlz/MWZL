package com.pokong.mwzl.data.bean.mwzl;

import java.io.Serializable;

/**
 * Created on 2018/12/13 13:50
 * User: Zheng
 * E-mail: zhengCH12138@163.com
 */
public class WaitStockNumRequestBean implements Serializable {
    private String appToken;
    private long maxOrderId;

    public String getAppToken() {
        return appToken;
    }

    public void setAppToken(String appToken) {
        this.appToken = appToken;
    }

    public long getMaxOrderId() {
        return maxOrderId;
    }

    public void setMaxOrderId(long maxOrderId) {
        this.maxOrderId = maxOrderId;
    }

    @Override
    public String toString() {
        return "WaitStockNumRequestBean{" +
                "appToken='" + appToken + '\'' +
                ", maxOrderId=" + maxOrderId +
                '}';
    }
}
