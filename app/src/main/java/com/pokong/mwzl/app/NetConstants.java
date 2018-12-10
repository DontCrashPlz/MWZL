package com.pokong.mwzl.app;

/**
 * Created by Zheng on 2018/10/17.
 */

public class NetConstants {

    /**
     * 主机地址
     */
    //public static final String BASEURL = "http://www.base.com/";
    //public static final String BASEURL = "http://192.168.31.20:80/";
    public static final String BASEURL = "http://mytestspace.51vip.biz/";

    /**
     * 登录接口
     */
    public static final String LOGIN = "/api/store/login";

    /**
     * 店铺基本信息接口
     */
    public static final String SHOP_INFO = "/api/store/info";

    /**
     * 订单列表接口
     */
    public static final String ORDER_LIST = "/api/store/order/query";

    /**
     * 订单详情接口
     */
    public static final String ORDER_DETAIL = "/api/store/order/detail";

    /**
     * 备货完成接口
     */
    public static final String ORDER_READY = "/api/store/order/stockUp";

    /**
     * 确认收货接口
     */
    public static final String PICK_CONFIRM = "/api/store/order/pickUp";

    /**
     * 地址校对接口
     */
    public static final String UPLOAD_LOCATION = "/api/storesetLocaltion";

}
