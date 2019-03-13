package com.pokong.mwzl.data.bean;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created on 2018/11/23 18:14
 * User: Zheng
 * E-mail: zhengCH12138@163.com
 * 订单列表条目数据实体
 */
public class OrderListItemEntity implements Serializable {

    private String create_time;//下单时间
    private ArrayList<GoodsEntity> goodlist;//订单商品列表
    private int goods_count;//订单的商品总数量
    private long id;//ID
    private String msg = "";//订单备注信息
    private String order_id;//订单编号
    private int order_status;//订单状态
    private int store_id;//店铺id
    private String store_name;//店铺名称
    private double totalprice;//订单总金额
    private String real_pay_money;//实际支付金额
    private String order_serial_num = "0000";//订单序号
    private String delivery_type = "self_mention";//自提:self_mention,配送:delivery
    private String receiver_name;//收货人姓名
    private String receiver_mobile;//收货人电话
    private String receiver_address;//收货人地址
    private int person_num;//用餐人数(餐具数量)
    private int person_age;//顾客年龄(蜡烛数量)
    private String order_type;//商品类型("cake"表示蛋糕，其它不是蛋糕)

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
        return goods_count;
    }

    public void setGoods_amount(int goods_amount) {
        this.goods_count = goods_amount;
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

    public double getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(double totalprice) {
        this.totalprice = totalprice;
    }

    public String getReal_pay_money() {
        return real_pay_money;
    }

    public void setReal_pay_money(String real_pay_money) {
        this.real_pay_money = real_pay_money;
    }

    public int getGoods_count() {
        return goods_count;
    }

    public void setGoods_count(int goods_count) {
        this.goods_count = goods_count;
    }

    public String getOrder_serial_num() {
        return order_serial_num;
    }

    public void setOrder_serial_num(String order_serial_num) {
        this.order_serial_num = order_serial_num;
    }

    public String getDelivery_type() {
        return delivery_type;
    }

    public void setDelivery_type(String delivery_type_name) {
        this.delivery_type = delivery_type_name;
    }

    public String getReceiver_name() {
        return receiver_name;
    }

    public void setReceiver_name(String receiver_name) {
        this.receiver_name = receiver_name;
    }

    public String getReceiver_mobile() {
        return receiver_mobile;
    }

    public void setReceiver_mobile(String receiver_mobile) {
        this.receiver_mobile = receiver_mobile;
    }

    public String getReceiver_address() {
        return receiver_address;
    }

    public void setReceiver_address(String receiver_address) {
        this.receiver_address = receiver_address;
    }

    public int getPerson_num() {
        return person_num;
    }

    public void setPerson_num(int person_num) {
        this.person_num = person_num;
    }

    public int getPerson_age() {
        return person_age;
    }

    public void setPerson_age(int person_age) {
        this.person_age = person_age;
    }

    public String getOrder_type() {
        return order_type;
    }

    public void setOrder_type(String order_type) {
        this.order_type = order_type;
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
                ", goods_count=" + goods_count +
                ", id=" + id +
                ", msg='" + msg + '\'' +
                ", order_id='" + order_id + '\'' +
                ", order_status=" + order_status +
                ", store_id=" + store_id +
                ", store_name='" + store_name + '\'' +
                ", totalprice=" + totalprice +
                ", real_pay_money='" + real_pay_money + '\'' +
                ", order_serial_num='" + order_serial_num + '\'' +
                ", delivery_type='" + delivery_type + '\'' +
                ", receiver_name='" + receiver_name + '\'' +
                ", receiver_mobile='" + receiver_mobile + '\'' +
                ", receiver_address='" + receiver_address + '\'' +
                ", person_num=" + person_num +
                ", person_age=" + person_age +
                ", order_type='" + order_type + '\'' +
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
