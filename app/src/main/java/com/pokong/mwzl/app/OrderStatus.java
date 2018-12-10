package com.pokong.mwzl.app;

/**
 * Created on 2018/11/27 19:46
 * User: Zheng
 * E-mail: zhengCH12138@163.com
 * 全部 0
 * 待付款 10
 * 待备货 20
 * 备货完成 30
 * 已收货 40
 * 已完成 50
 */
public class OrderStatus {

    /**
     * 全部订单
     */
    public static final int ALL = 0;

    /**
     * 已下单待付款订单
     */
    public static final int WAIT_PAY = 10;

    /**
     * 待备货订单
     */
    public static final int WAIT_STOCK = 20;


    /**
     * 待收货订单(待自提、待配送)
     */
    public static final int WAIT_RECEIVE = 30;

    /**
     * 待评价订单
     */
    public static final int WAIT_COMMENT = 40;

    /**
     * 已完成订单
     */
    public static final int COMPLETED = 50;

}
