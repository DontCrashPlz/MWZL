package com.pokong.mwzl.app;

/**
 * Created on 2018/11/27 19:46
 * User: Zheng
 * E-mail: zhengCH12138@163.com
 */
public class OrderStatus {

    /**
     * 待备货订单
     */
    public static final int WAIT_STOCK = 20;

    /**
     * 待自提订单
     */
    public static final int WAIT_PICK = 30;

    /**
     * 待配送订单
     */
    public static final int WAIT_DELIVER = 40;

    /**
     * 已完成订单
     */
    public static final int COMPLETED = 10;

    /**
     * 全部订单
     */
    public static final int ALL = 0;

}
