package com.pokong.mwzl.data.bean.business;

import com.pokong.mwzl.data.bean.GoodsEntity;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created on 2018/11/7 13:41
 * User: Zheng
 * E-mail: zhengCH12138@163.com
 */
public class OrderDetailResponseBean implements Serializable {
    private String create_time;//下单时间
    private String finishtime;//订单完成时间
    private ArrayList<GoodsEntity> goodlist;
    private int goods_count;//订单商品总数
    private long id;//ID
    private String msg;//订单备注信息
    private String order_id;//订单编号
    private int order_status;//订单状态
    private String receiver_name;//收货人姓名
    private String receiver_address;//收货人地址
    private int store_id;//商店ID
    private double totalprice;//订单总金额
    private double real_pay_account;//订单实际支付金额

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getFinishtime() {
        return finishtime;
    }

    public void setFinishtime(String finishtime) {
        this.finishtime = finishtime;
    }

    public ArrayList<GoodsEntity> getGoodlist() {
        return goodlist;
    }

    public void setGoodlist(ArrayList<GoodsEntity> goodlist) {
        this.goodlist = goodlist;
    }

    public int getGoods_count() {
        return goods_count;
    }

    public void setGoods_count(int goods_count) {
        this.goods_count = goods_count;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public int getOrder_status() {
        return order_status;
    }

    public void setOrder_status(int order_status) {
        this.order_status = order_status;
    }

    public String getReceiver_name() {
        return receiver_name;
    }

    public void setReceiver_name(String receiver_name) {
        this.receiver_name = receiver_name;
    }

    public String getReceiver_address() {
        return receiver_address;
    }

    public void setReceiver_address(String receiver_address) {
        this.receiver_address = receiver_address;
    }

    public int getStore_id() {
        return store_id;
    }

    public void setStore_id(int store_id) {
        this.store_id = store_id;
    }

    public double getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(double totalprice) {
        this.totalprice = totalprice;
    }

    public double getReal_pay_account() {
        return real_pay_account;
    }

    public void setReal_pay_account(double real_pay_account) {
        this.real_pay_account = real_pay_account;
    }

    @Override
    public String toString() {
        return "OrderDetailResponseBean{" +
                "create_time='" + create_time + '\'' +
                ", finishtime='" + finishtime + '\'' +
                ", goodlist=" + goodlist +
                ", goods_count=" + goods_count +
                ", id=" + id +
                ", msg='" + msg + '\'' +
                ", order_id='" + order_id + '\'' +
                ", order_status=" + order_status +
                ", receiver_name='" + receiver_name + '\'' +
                ", receiver_address='" + receiver_address + '\'' +
                ", store_id=" + store_id +
                ", totalprice=" + totalprice +
                ", real_pay_account=" + real_pay_account +
                '}';
    }

}
//        "ci_id":null,
//        "create_time":"2018-01-05 10:51:16",
//        "ec_id":null,
//        "eval_time":null,
//        "finishtime":null,
//        "goodlist":Array[1],
//        "goods_amount":0.02,
//        "id":1,
//        "invoice":null,
//        "invoicetype":0,
//        "msg":"",
//        "order_id":"120180105105116",
//        "order_status":10,
//        "pay_msg":null,
//        "payment_id":null,
//        "paytime":null,
//        "receiver_address":null,
//        "receiver_name":null,
//        "recv_time":null,
//        "refund":null,
//        "refund_type":null,
//        "return_content":null,
//        "return_ec_id":null,
//        "return_shipcode":null,
//        "return_shiptime":null,
//        "ship_price":0,
//        "shipcode":null,
//        "shiptime":null,
//        "store_id":8,
//        "totalprice":0.02,
//        "transport":null,
//        "user_id":1
