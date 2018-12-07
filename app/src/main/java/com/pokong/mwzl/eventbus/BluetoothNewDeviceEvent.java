package com.pokong.mwzl.eventbus;

import com.qs.helper.printer.Device;

/**
 * Created on 2018/12/6 10:43
 * User: Zheng
 * E-mail: zhengCH12138@163.com
 * 蓝牙扫描到新设备事件
 */
public class BluetoothNewDeviceEvent {

    private Device newDevice;

    public BluetoothNewDeviceEvent(Device newDevice) {
        this.newDevice = newDevice;
    }

    public Device getNewDevice() {
        return newDevice;
    }

    public void setNewDevice(Device newDevice) {
        this.newDevice = newDevice;
    }

    @Override
    public String toString() {
        return "BluetoothNewDeviceEvent{" +
                "newDevice=" + newDevice +
                '}';
    }
}
