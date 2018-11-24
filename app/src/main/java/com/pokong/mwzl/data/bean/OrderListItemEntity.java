package com.pokong.mwzl.data.bean;

import java.util.ArrayList;

/**
 * Created on 2018/11/23 18:14
 * User: Zheng
 * E-mail: zhengCH12138@163.com
 * 订单列表条目数据实体
 */
public class OrderListItemEntity {
    private String create_time;//下单时间
    private ArrayList<GoodsEntity> goodlist;//订单商品列表
    private int goods_amount;//订单的商品数量
    private String msg;//订单备注信息
    private String order_id;//订单编号
    private int store_id;//店铺id
    private String store_name;//店铺名称
    private int totalprice;//订单总金额
    private String real_pay_money;//实际支付金额

    private boolean isOpend = false;//当前订单条目是否已经展开

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public ArrayList<GoodsEntity> getGoodlist() {
        return goodlist;
    }

    public void setGoodlist(ArrayList<GoodsEntity> goodlist) {
        this.goodlist = goodlist;
    }

    public int getGoods_amount() {
        return goods_amount;
    }

    public void setGoods_amount(int goods_amount) {
        this.goods_amount = goods_amount;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public int getStore_id() {
        return store_id;
    }

    public void setStore_id(int store_id) {
        this.store_id = store_id;
    }

    public String getStore_name() {
        return store_name;
    }

    public void setStore_name(String store_name) {
        this.store_name = store_name;
    }

    public int getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(int totalprice) {
        this.totalprice = totalprice;
    }

    public String getReal_pay_money() {
        return real_pay_money;
    }

    public void setReal_pay_money(String real_pay_money) {
        this.real_pay_money = real_pay_money;
    }

    public boolean isOpend() {
        return isOpend;
    }

    public void setOpend(boolean opend) {
        isOpend = opend;
    }

    @Override
    public String toString() {
        return "OrderListItemEntity{" +
                "create_time='" + create_time + '\'' +
                ", goodlist=" + goodlist +
                ", goods_amount=" + goods_amount +
                ", msg='" + msg + '\'' +
                ", order_id='" + order_id + '\'' +
                ", store_id=" + store_id +
                ", store_name='" + store_name + '\'' +
                ", totalprice=" + totalprice +
                ", real_pay_money='" + real_pay_money + '\'' +
                ", isOpend=" + isOpend +
                '}';
    }
}
//        "addr_id":32771,
//        "addtime":"2018-10-19 09:15:11",
//        "create_time":"2018-10-19 09:15:11",
//        "goodlist":Array[1],
//        "goods_amount":246,
//        "id":76,
//        "invoice":null,
//        "invoicetype":0,
//        "msg":"",
//        "order_id":"120181019091511",
//        "order_status":80,
//        "out_order_id":null,
//        "shipcode":null,
//        "store_id":2,
//        "store_name":"美味兹乐测试店",
//        "totalprice":246,
//        "transport":null,
//        "user_id":1
