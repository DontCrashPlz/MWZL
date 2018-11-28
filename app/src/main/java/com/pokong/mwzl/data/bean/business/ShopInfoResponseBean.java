package com.pokong.mwzl.data.bean.business;

import java.io.Serializable;

/**
 * Created on 2018/11/7 9:55
 * User: Zheng
 * E-mail: zhengCH12138@163.com
 * 登录响应实体类
 */
public class ShopInfoResponseBean implements Serializable {
    private String area_full_name;//店铺所在区域全名
    private int area_id;
    private String contact;//联系人
    private int favorite_count;//收藏数
    private int id;//ID
    private String imgurl;
    private int status;//店铺状态
    private String store_address;//店铺地址
    private String store_info;//店铺信息
    private String store_name;//店铺名称
    private String store_qq;//店铺QQ
    private String store_telephone;//店铺联系电话
    private int user_id;

    public String getArea_full_name() {
        return area_full_name;
    }

    public void setArea_full_name(String area_full_name) {
        this.area_full_name = area_full_name;
    }

    public int getArea_id() {
        return area_id;
    }

    public void setArea_id(int area_id) {
        this.area_id = area_id;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public int getFavorite_count() {
        return favorite_count;
    }

    public void setFavorite_count(int favorite_count) {
        this.favorite_count = favorite_count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getStore_address() {
        return store_address;
    }

    public void setStore_address(String store_address) {
        this.store_address = store_address;
    }

    public String getStore_info() {
        return store_info;
    }

    public void setStore_info(String store_info) {
        this.store_info = store_info;
    }

    public String getStore_name() {
        return store_name;
    }

    public void setStore_name(String store_name) {
        this.store_name = store_name;
    }

    public String getStore_qq() {
        return store_qq;
    }

    public void setStore_qq(String store_qq) {
        this.store_qq = store_qq;
    }

    public String getStore_telephone() {
        return store_telephone;
    }

    public void setStore_telephone(String store_telephone) {
        this.store_telephone = store_telephone;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "ShopInfoResponseBean{" +
                "area_full_name='" + area_full_name + '\'' +
                ", area_id=" + area_id +
                ", contact='" + contact + '\'' +
                ", favorite_count=" + favorite_count +
                ", id=" + id +
                ", imgurl='" + imgurl + '\'' +
                ", status=" + status +
                ", store_address='" + store_address + '\'' +
                ", store_info='" + store_info + '\'' +
                ", store_name='" + store_name + '\'' +
                ", store_qq='" + store_qq + '\'' +
                ", store_telephone='" + store_telephone + '\'' +
                ", user_id=" + user_id +
                '}';
    }

}
//        "area_full_name":"河南省郑州市",
//        "area_id":4523656,
//        "contact":"赵三",
//        "favorite_count":0,
//        "id":2,
//        "imgurl":"",
//        "status":1,
//        "store_address":null,
//        "store_info":null,
//        "store_name":"美味兹乐测试店",
//        "store_ower":null,
//        "store_qq":"12345678",
//        "store_telephone":"0374-3568987",
//        "template":null,
//        "user_id":35

