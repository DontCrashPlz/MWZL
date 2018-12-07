package com.pokong.bluetooth;

import java.util.List;

/**
 * Created on 2018/12/7 15:31
 * User: Zheng
 * E-mail: zhengCH12138@163.com
 * 蓝牙打印服务
 */
public class MyBtPrintService implements Bluetoothable {

    private static MyBtPrintService mInstance = null;

    private MyBtPrintService(){}

    public static MyBtPrintService getInstance() {
        if (mInstance == null) {
            synchronized (MyBtPrintService.class) {
                if (mInstance == null) {
                    mInstance = new MyBtPrintService();
                }
            }
        }
        return mInstance;
    }

    private Adapter mPrintAdapter;

    public void setAdapter(Adapter adapter){
        if (adapter == null)
            throw new NullPointerException("Class MyBtPrintService, Method setAdapter(), Lines 2");
        mPrintAdapter = adapter;
    }

    @Override
    public void scan() {
        if (mPrintAdapter == null)
            throw new NullPointerException("Class MyBtPrintService, Method scan(), Lines 2");
        mPrintAdapter.scan();
    }

    @Override
    public void stopScan() {
        if (mPrintAdapter == null)
            throw new NullPointerException("Class MyBtPrintService, Method stopScan(), Lines 2");
        mPrintAdapter.stopScan();
    }

    @Override
    public List getCacheDeviceList() {
        if (mPrintAdapter == null)
            throw new NullPointerException("Class MyBtPrintService, Method getCacheDeviceList(), Lines 2");
        return mPrintAdapter.getCacheDeviceList();
    }

    @Override
    public void connect(String devicePort) {
        if (mPrintAdapter == null)
            throw new NullPointerException("Class MyBtPrintService, Method connect(), Lines 2");
        mPrintAdapter.connect(devicePort);
    }

    @Override
    public void disconnect() {
        if (mPrintAdapter == null)
            throw new NullPointerException("Class MyBtPrintService, Method disconnect(), Lines 2");
        mPrintAdapter.disconnect();
    }

    @Override
    public int getPrintState() {
        if (mPrintAdapter == null)
            throw new NullPointerException("Class MyBtPrintService, Method getPrintState(), Lines 2");
        return mPrintAdapter.getPrintState();
    }

    @Override
    public boolean isOpened() {
        return mPrintAdapter.isOpened();
    }

    @Override
    public void openBluetooth() {
        mPrintAdapter.openBluetooth();
    }

    @Override
    public void closeBluetooth() {
        mPrintAdapter.closeBluetooth();
    }

    public abstract static class Adapter implements Bluetoothable, Printable{}

}
