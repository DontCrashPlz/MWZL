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
     * 待备货订单
     */
    public static final int WAIT_STOCK = 20;


    /**
     * 订单备货完成
     */
    public static final int READY = 30;

//    /**
//     * 待自提订单
//     */
//    public static final int WAIT_PICK = 30;
//
//    /**
//     * 待配送订单
//     */
//    public static final int WAIT_DELIVER = 40;

    /**
     * 已完成订单
     */
    public static final int COMPLETED = 50;

}
