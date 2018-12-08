package com.pokong.btprint;

import java.util.List;

/**
 * Created on 2018/12/7 15:31
 * User: Zheng
 * E-mail: zhengCH12138@163.com
 * 蓝牙打印服务
 */
public class MyBtPrintService implements Bluetoothable, Printable {

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
        if (mPrintAdapter == null)
            throw new NullPointerException("Class MyBtPrintService, Method isOpened(), Lines 2");
        return mPrintAdapter.isOpened();
    }

    @Override
    public void openBluetooth() {
        if (mPrintAdapter == null)
            throw new NullPointerException("Class MyBtPrintService, Method openBluetooth(), Lines 2");
        mPrintAdapter.openBluetooth();
    }

    @Override
    public void closeBluetooth() {
        if (mPrintAdapter == null)
            throw new NullPointerException("Class MyBtPrintService, Method closeBluetooth(), Lines 2");
        mPrintAdapter.closeBluetooth();
    }

    @Override
    public void nextLine() {
        if (mPrintAdapter == null)
            throw new NullPointerException("Class MyBtPrintService, Method nextLine(), Lines 2");
        mPrintAdapter.nextLine();
    }

    @Override
    public void printNormalDivideLine() {
        if (mPrintAdapter == null)
            throw new NullPointerException("Class MyBtPrintService, Method printNormalDivideLine(), Lines 2");
        mPrintAdapter.printNormalDivideLine();
    }

    @Override
    public void printNormalDevideLineWithText(String text) {
        if (mPrintAdapter == null)
            throw new NullPointerException("Class MyBtPrintService, Method printNormalDevideLineWithText(String text), Lines 2");
        mPrintAdapter.printNormalDevideLineWithText(text);
    }

    @Override
    public void printEndDivideLine(String serialNum) {
        if (mPrintAdapter == null)
            throw new NullPointerException("Class MyBtPrintService, Method printEndDivideLine(String serialNum), Lines 2");
        mPrintAdapter.printEndDivideLine(serialNum);
    }

    @Override
    public void printNormalTextLift(String text) {
        if (mPrintAdapter == null)
            throw new NullPointerException("Class MyBtPrintService, Method printNormalTextLift(String text), Lines 2");
        mPrintAdapter.printNormalTextLift(text);
    }

    @Override
    public void printNormalTextCenter(String text) {
        if (mPrintAdapter == null)
            throw new NullPointerException("Class MyBtPrintService, Method printNormalTextCenter(String text), Lines 2");
        mPrintAdapter.printNormalTextCenter(text);
    }

    @Override
    public void printNormalTextRight(String text) {
        if (mPrintAdapter == null)
            throw new NullPointerException("Class MyBtPrintService, Method printNormalTextRight(String text), Lines 2");
        mPrintAdapter.printNormalTextRight(text);
    }

    @Override
    public void printRiseTextLift(String text) {
        if (mPrintAdapter == null)
            throw new NullPointerException("Class MyBtPrintService, Method printRiseTextLift(String text), Lines 2");
        mPrintAdapter.printRiseTextLift(text);
    }

    @Override
    public void printRiseTextCenter(String text) {
        if (mPrintAdapter == null)
            throw new NullPointerException("Class MyBtPrintService, Method printRiseTextCenter(String text), Lines 2");
        mPrintAdapter.printRiseTextCenter(text);
    }

    @Override
    public void printRiseTextRight(String text) {
        if (mPrintAdapter == null)
            throw new NullPointerException("Class MyBtPrintService, Method printRiseTextRight(String text), Lines 2");
        mPrintAdapter.printRiseTextRight(text);
    }

    @Override
    public void printLargeTextLift(String text) {
        if (mPrintAdapter == null)
            throw new NullPointerException("Class MyBtPrintService, Method printLargeTextLift(String text), Lines 2");
        mPrintAdapter.printLargeTextLift(text);
    }

    @Override
    public void printLargeTextCenter(String text) {
        if (mPrintAdapter == null)
            throw new NullPointerException("Class MyBtPrintService, Method printLargeTextCenter(String text), Lines 2");
        mPrintAdapter.printLargeTextCenter(text);
    }

    @Override
    public void printLargeTextRight(String text) {
        if (mPrintAdapter == null)
            throw new NullPointerException("Class MyBtPrintService, Method printLargeTextRight(String text), Lines 2");
        mPrintAdapter.printLargeTextRight(text);
    }

    public abstract static class Adapter implements Bluetoothable, Printable{}

}
