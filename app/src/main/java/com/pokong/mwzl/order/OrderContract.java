package com.pokong.mwzl.order;

/**
 * Created on 2018/11/16 15:54
 * User: Zheng
 * E-mail: zhengCH12138@163.com
 */
public interface OrderContract {
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
