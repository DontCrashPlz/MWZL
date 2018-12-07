package com.pokong.mwzl.shop;

/**
 * Created on 2018/11/16 15:57
 * User: Zheng
 * E-mail: zhengCH12138@163.com
 */
public interface ShopManageContract {
    interface View{

        /**
         * 点击扫描图标
         */
        void clickScanIcon();

        /**
         * 点击系统通知图标
         */
        void clickNoticeIcon();

        /**
         * 点击蓝牙图标
         */
        void clickBluetoothIcon();

        /**
         * 点击统计按钮
         */
        void clickStatistics();

        /**
         * 点击地址校对
         */
        void clickLocation();

        /**
         * 点击订单查询
         */
        void clickOrderQuery();

        /**
         * 点击商品评论
         */
        void clickComment();

        /**
         * 显示蓝牙连接Icon
         */
        void showBluetoothConnectedIcon();

        /**
         * 显示蓝牙断开Icon
         */
        void showBluetoothBreakIcon();

    }

    interface Presenter{

    }
}
