package com.pokong.mwzl.data.bean.personal;

import com.pokong.mwzl.data.bean.GoodsEntity;

import java.util.ArrayList;

/**
 * Created on 2018/11/23 18:28
 * User: Zheng
 * E-mail: zhengCH12138@163.com
 */
public class OrderDetailEntity {
    private String real_pay_account;//订单实际支付金额
    private double totalprice;//订单总金额
    private double ship_price;//邮费
    private String receiver_name;//收货人姓名
    private String receiver_address;//收货人地址
    private String paytime;//支付时间
    private int order_status;//订单状态
    private String order_id;//订单编号
    private String msg;//订单备注信息
    private int goods_amount;//订单的商品总数量
    private ArrayList<GoodsEntity> goodlist;//订单商品列表
    private String finishtime;//订单完成时间
    private String create_time;//下单时间

    public String getReal_pay_account() {
        return real_pay_account;
    }

    public void setReal_pay_account(String real_pay_account) {
        this.real_pay_account = real_pay_account;
    }

    public double getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(double totalprice) {
        this.totalprice = totalprice;
    }

    public double getShip_price() {
        return ship_price;
    }

    public void setShip_price(double ship_price) {
        this.ship_price = ship_price;
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

    public String getPaytime() {
        return paytime;
    }

    public void setPaytime(String paytime) {
        this.paytime = paytime;
    }

    public int getOrder_status() {
        return order_status;
    }

    public void setOrder_status(int order_status) {
        this.order_status = order_status;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getGoods_amount() {
        return goods_amount;
    }

    public void setGoods_amount(int goods_amount) {
        this.goods_amount = goods_amount;
    }

    public ArrayList<GoodsEntity> getGoodlist() {
        return goodlist;
    }

    public void setGoodlist(ArrayList<GoodsEntity> goodlist) {
        this.goodlist = goodlist;
    }

    public String getFinishtime() {
        return finishtime;
    }

    public void setFinishtime(String finishtime) {
        this.finishtime = finishtime;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    @Override
    public String toString() {
        return "OrderDetailEntity{" +
                "real_pay_account='" + real_pay_account + '\'' +
                ", totalprice=" + totalprice +
                ", ship_price=" + ship_price +
                ", receiver_name='" + receiver_name + '\'' +
                ", receiver_address='" + receiver_address + '\'' +
                ", paytime='" + paytime + '\'' +
                ", order_status=" + order_status +
                ", order_id='" + order_id + '\'' +
                ", msg='" + msg + '\'' +
                ", goods_amount=" + goods_amount +
                ", goodlist=" + goodlist +
                ", finishtime='" + finishtime + '\'' +
                ", create_time='" + create_time + '\'' +
                '}';
    }
}
//        "ci_id":null,
//        "create_time":"2018-10-19 09:15:11",
//        "ec_id":null,
//        "eval_time":null,
//        "finishtime":null,
//        "goodlist":Array[1],
//        "goods_amount":246,
//        "id":76,
//        "invoice":null,
//        "invoicetype":0,
//        "msg":"",
//        "order_id":"120181019091511",
//        "order_status":30,
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
//        "store_id":2,
//        "totalprice":246,
//        "transport":null,
//        "user_id":1
