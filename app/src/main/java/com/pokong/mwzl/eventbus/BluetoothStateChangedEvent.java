package com.pokong.mwzl.eventbus;

/**
 * Created on 2018/12/6 10:35
 * User: Zheng
 * E-mail: zhengCH12138@163.com
 * 蓝牙状态改变事件
 */
public class BluetoothStateChangedEvent {

    //当前蓝牙状态标识
    private int currentStatus;
    //当前蓝牙状态描述
    private String statusDescription;

    public BluetoothStateChangedEvent(int currentStatus, String statusDescription) {
        this.currentStatus = currentStatus;
        this.statusDescription = statusDescription;
    }

    public int getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(int currentStatus) {
        this.currentStatus = currentStatus;
    }

    public String getStatusDescription() {
        return statusDescription;
    }

    public void setStatusDescription(String statusDescription) {
        this.statusDescription = statusDescription;
    }

    @Override
    public String toString() {
        return "BluetoothStateChangedEvent{" +
                "currentStatus=" + currentStatus +
                ", statusDescription='" + statusDescription + '\'' +
                '}';
    }
}
