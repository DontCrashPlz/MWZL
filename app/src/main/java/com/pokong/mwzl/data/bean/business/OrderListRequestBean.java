package com.pokong.mwzl.data.bean.business;

import java.io.Serializable;

/**
 * Created on 2018/11/7 13:40
 * User: Zheng
 * E-mail: zhengCH12138@163.com
 */
public class OrderListRequestBean implements Serializable {

    private String appToken;//appToken
    private String orderColunm = "o.addTime";//排序依据，默认根据订单生成时间排序
    private String orderMode = "asc";//排序方式，desc降序，asc升序
    private int pageSize = 5;//每页数据个数
    private int pageNumber = 1;//页码，固定请求第1页
    private long startId;//其实订单ID，从这个订单ID开始往后查询
    private int splitpage = 1;//是否分页，默认值1
    private int order_status;//订单状态，20=待备货，不传此值获得所有订单列表

    public String getAppToken() {
        return appToken;
    }

    public void setAppToken(String appToken) {
        this.appToken = appToken;
    }

    public String getOrderColunm() {
        return orderColunm;
    }

    public void setOrderColunm(String orderColunm) {
        this.orderColunm = orderColunm;
    }

    public String getOrderMode() {
        return orderMode;
    }

    public void setOrderMode(String orderMode) {
        this.orderMode = orderMode;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public long getStartId() {
        return startId;
    }

    public void setStartId(long startId) {
        this.startId = startId;
    }

    public int getSplitpage() {
        return splitpage;
    }

    public void setSplitpage(int splitpage) {
        this.splitpage = splitpage;
    }

    public int getOrder_status() {
        return order_status;
    }

    public void setOrder_status(int order_status) {
        this.order_status = order_status;
    }

    @Override
    public String toString() {
        return "OrderListRequestBean{" +
                "appToken='" + appToken + '\'' +
                ", orderColunm='" + orderColunm + '\'' +
                ", orderMode='" + orderMode + '\'' +
                ", pageSize=" + pageSize +
                ", pageNumber=" + pageNumber +
                ", startId=" + startId +
                ", splitpage=" + splitpage +
                ", order_status=" + order_status +
                '}';
    }
}
