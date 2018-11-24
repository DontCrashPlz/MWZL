package com.pokong.mwzl.data.bean;

/**
 * Created on 2018/11/23 16:03
 * User: Zheng
 * E-mail: zhengCH12138@163.com
 * 商品数据实体类
 */
public class GoodsEntity {
    private int count;//商品数量
    private int goods_id;
    private String goods_brief;//商品简介
    private String goods_name;//商品名称
    private String imgurl;//商品的预览图片
    private String spec_info;//规格信息

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(int goods_id) {
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

    public String getSpec_info() {
        return spec_info;
    }

    public void setSpec_info(String spec_info) {
        this.spec_info = spec_info;
    }

    @Override
    public String toString() {
        return "GoodsEntity{" +
                "count=" + count +
                ", goods_id=" + goods_id +
                ", goods_brief='" + goods_brief + '\'' +
                ", goods_name='" + goods_name + '\'' +
                ", imgurl='" + imgurl + '\'' +
                ", spec_info='" + spec_info + '\'' +
                '}';
    }
}
//{
//        "count":1,
//        "coupon":0,
//        "good_spec_id":"[1,8]",
//        "goods_brief":null,
//        "goods_id":7,
//        "goods_name":"纯甄",
//        "imgid":432586,
//        "imgtypecode":"imgtype_temp",
//        "imgurl":"http://localhost/upload/temp/46d4104dc31f4eb0ada1ac6240e8e013.jpg",
//        "integral":0,
//        "price":300,
//        "price_type":"",
//        "spec_info":""
//        }
