package com.pokong.mwzl.app;

/**
 * Created by Zheng on 2018/10/17.
 */

public class NetConstants {

    /**
     * 主机地址
     */
    //public static final String BASEURL = "http://192.168.31.20:80/";
    //public static final String BASEURL = "http://mytestspace.51vip.biz/";
    public static final String BASEURL = "http://www.dcherish.com/";

    /**
     * 检查更新接口
     */
    public static final String UPDATE = "/api/app/getLastestVersion";

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
     * 未加载的待备货订单数量
     */
    public static final String ORDER_WAIT_STOCK_NUM = "/api/store/order/lastestOrder";

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

    /**
     * 获取会员信息接口
     */
    public static final String SHOP_MEMBER_INFO = "/api/store/user/info";

    /**
     * 会员余额支付接口
     */
    public static final String SHOP_MEMBER_PAY = "/api/store/userPay";

    /**
     * 获取统计页面接口
     */
    public static final String STATISTICS = "api/store/stats";

}
