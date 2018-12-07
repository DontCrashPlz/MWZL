package com.pokong.bluetooth;

import java.util.List;

/**
 * Created on 2018/12/7 17:19
 * User: Zheng
 * E-mail: zhengCH12138@163.com
 * 蓝牙相关的行为
 */
public interface Bluetoothable {

    /**
     * 扫描设备
     */
    void scan();

    /**
     * 停止扫描设备
     */
    void stopScan();

    /**
     * 获取缓存设备列表
     * @return
     */
    List getCacheDeviceList();

    /**
     * 连接设备
     * @param devicePort
     */
    void connect(String devicePort);

    /**
     * 断开连接
     */
    void disconnect();

    /**
     * 获取打印机当前状态
     * @return
     */
    int getPrintState();

    /**
     * 获取蓝牙是否已开启
     * @return
     */
    boolean isOpened();

    /**
     * 开启蓝牙
     * @return
     */
    void openBluetooth();

    /**
     * 关闭蓝牙
     * @return
     */
    void closeBluetooth();

}
