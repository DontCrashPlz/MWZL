package com.pokong.mwzl.setting.bluetooth;

import com.pokong.mwzl.adapter.DeviceListAdapter;

/**
 * Created on 2018/12/4 18:47
 * User: Zheng
 * E-mail: zhengCH12138@163.com
 */
public interface BluetoothContract {
    interface View{
        /**
         * 显示正在连接蓝牙弹窗
         */
        void showConnectingDialog();

        /**
         * 取消正在连接蓝牙弹窗
         */
        void dismissConnectingDialog();

        /**
         * 显示正在扫描页面
         */
        void showScaning();

        /**
         * 显示扫描完成页面
         */
        void scaningDone();

    }

    interface Presenter{

        /**
         * 改变双份小票开关状态
         */
        void changeDoubleSwitchStatus(boolean isDoublePrint);

        /**
         * 扫描附近蓝牙设备
         */
        void scanBluetoothDevice(DeviceListAdapter deviceAdapter);

    }
}
