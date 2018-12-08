package com.pokong.btprint;

import com.qs.helper.printer.PrinterClass;
import com.qs.helper.printer.bt.BtService;

import java.util.List;

/**
 * Created on 2018/12/7 17:13
 * User: Zheng
 * E-mail: zhengCH12138@163.com
 * 爱思泰克NF5804热敏打印机
 * 南方鸿志提供技术支持
 * http://www.nanfang001.com/Product/280561421.html
 */
public class NF5804PrintAdapter extends MyBtPrintService.Adapter {

    private PrinterClass printer;

    public NF5804PrintAdapter(BtService btService){
        printer = btService;
    }

    @Override
    public void nextLine() {
        printer.write(PrinterClass.CMD_NEWLINE);
    }

    @Override
    public void printNormalDivideLine() {
        printer.write(PrinterClass.CMD_ALIGN_MIDDLE);
        printer.write(PrinterClass.CMD_FONTSIZE_NORMAL);
        printer.printText("----------------");
    }

    @Override
    public void printNormalDevideLineWithText(String text) {
        printer.write(PrinterClass.CMD_ALIGN_MIDDLE);
        printer.write(PrinterClass.CMD_FONTSIZE_NORMAL);
        printer.printText("-------" + text + "-------");
    }

    @Override
    public void printEndDivideLine(String serialNum) {
        printer.write(PrinterClass.CMD_ALIGN_MIDDLE);

        printer.write(PrinterClass.CMD_FONTSIZE_DOUBLE_HIGH);
        printer.printText("＊＊＊＊＊＊");

        printer.write(PrinterClass.CMD_FONTSIZE_DOUBLE);
        printer.printText("#" + serialNum + "完");

        printer.write(PrinterClass.CMD_FONTSIZE_DOUBLE_HIGH);
        printer.printText("＊＊＊＊＊＊");
    }

    @Override
    public void printNormalTextLift(String text) {
        printer.write(PrinterClass.CMD_ALIGN_LEFT);
        printer.write(PrinterClass.CMD_FONTSIZE_NORMAL);
        printer.printText(text);
    }

    @Override
    public void printNormalTextCenter(String text) {
        printer.write(PrinterClass.CMD_ALIGN_MIDDLE);
        printer.write(PrinterClass.CMD_FONTSIZE_NORMAL);
        printer.printText(text);
    }

    @Override
    public void printNormalTextRight(String text) {
        printer.write(PrinterClass.CMD_ALIGN_RIGHT);
        printer.write(PrinterClass.CMD_FONTSIZE_NORMAL);
        printer.printText(text);
    }

    @Override
    public void printRiseTextLift(String text) {
        printer.write(PrinterClass.CMD_ALIGN_LEFT);
        printer.write(PrinterClass.CMD_FONTSIZE_DOUBLE_HIGH);
        printer.printText(text);
    }

    @Override
    public void printRiseTextCenter(String text) {
        printer.write(PrinterClass.CMD_ALIGN_MIDDLE);
        printer.write(PrinterClass.CMD_FONTSIZE_DOUBLE_HIGH);
        printer.printText(text);
    }

    @Override
    public void printRiseTextRight(String text) {
        printer.write(PrinterClass.CMD_ALIGN_RIGHT);
        printer.write(PrinterClass.CMD_FONTSIZE_DOUBLE_HIGH);
        printer.printText(text);
    }

    @Override
    public void printLargeTextLift(String text) {
        printer.write(PrinterClass.CMD_ALIGN_LEFT);
        printer.write(PrinterClass.CMD_FONTSIZE_DOUBLE);
        printer.printText(text);
    }

    @Override
    public void printLargeTextCenter(String text) {
        printer.write(PrinterClass.CMD_ALIGN_MIDDLE);
        printer.write(PrinterClass.CMD_FONTSIZE_DOUBLE);
        printer.printText(text);
    }

    @Override
    public void printLargeTextRight(String text) {
        printer.write(PrinterClass.CMD_ALIGN_RIGHT);
        printer.write(PrinterClass.CMD_FONTSIZE_DOUBLE);
        printer.printText(text);
    }

    @Override
    public void scan() {
        printer.scan();
    }

    @Override
    public void stopScan() {
        printer.stopScan();
    }

    @Override
    public List getCacheDeviceList() {
        return printer.getDeviceList();
    }

    @Override
    public void connect(String devicePort) {
        printer.connect(devicePort);
    }

    @Override
    public void disconnect() {
        printer.disconnect();
    }

    @Override
    public int getPrintState() {
        return printer.getState();
    }

    @Override
    public boolean isOpened() {
        return printer.IsOpen();
    }

    @Override
    public void openBluetooth() {
        printer.open(null);
    }

    @Override
    public void closeBluetooth() {
        printer.close(null);
    }
}
