package com.pokong.mwzl.data.bean;

import java.io.Serializable;

/**
 * Created on 2018/11/23 16:03
 * User: Zheng
 * E-mail: zhengCH12138@163.com
 * 商品数据实体类
 */
public class GoodsEntity implements Serializable {
    private int count;//商品数量
    private long goods_id;//商品id
    private String goods_brief;//商品简介
    private String goods_name;//商品名称
    private String imgurl;//商品的预览图片
    private double price;//商品单价
    private String spec_info;//规格信息
    private double total_price;//商品总价

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public long getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(long goods_id) {
        this.goods_id = goods_id;
    }

    public String getGoods_brief() {
        return goods_brief;
    }

    public void setGoods_brief(String goods_brief) {
        this.goods_brief = goods_brief;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getSpec_info() {
        return spec_info;
    }

    public void setSpec_info(String spec_info) {
        this.spec_info = spec_info;
    }

    public double getTotal_price() {
        return total_price;
    }

    public void setTotal_price(double total_price) {
        this.total_price = total_price;
    }

    @Override
    public String toString() {
        return "GoodsEntity{" +
                "count=" + count +
                ", goods_id=" + goods_id +
                ", goods_brief='" + goods_brief + '\'' +
                ", goods_name='" + goods_name + '\'' +
                ", imgurl='" + imgurl + '\'' +
                ", price=" + price +
                ", spec_info='" + spec_info + '\'' +
                '}';
    }

}
//        "count":1,
//        "coupon":0,
//        "good_spec_id":"[1,5]",
//        "goods_brief":null,
//        "goods_id":28,
//        "goods_name":"粉玫瑰",
//        "imgid":431633,
//        "imgtypecode":"imgtype_temp",
//        "imgurl":"http://localhost/upload/temp/fe1ed5da2bf347e4bd6c8ffd5caa7eaf.jpg",
//        "integral":0,
//        "price":0.02,
//        "price_type":"1",
//        "spec_info":"çº¢è²;5.2è±å¯¸;"
