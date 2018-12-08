package com.pokong.bluetooth;

import com.qs.helper.printer.PrintService;
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

    public NF5804PrintAdapter(BtService btService){
        PrintService.pl = btService;
    }

    @Override
    public void printWorkArea() {
        PrintService.pl.write(new byte[] { 0x1d, 0x0c });
    }

    @Override
    public void nextLine() {
        PrintService.pl.write(PrinterClass.CMD_NEWLINE);
    }

    @Override
    public void printNormalDivideLine() {
        PrintService.pl.write(PrinterClass.CMD_ALIGN_MIDDLE);
        PrintService.pl.write(PrinterClass.CMD_FONTSIZE_NORMAL);
        PrintService.pl.printText("————————————————");
    }

    @Override
    public void printNormalDevideLineWithText(String text) {
        PrintService.pl.write(PrinterClass.CMD_ALIGN_MIDDLE);
        PrintService.pl.write(PrinterClass.CMD_FONTSIZE_NORMAL);
        PrintService.pl.printText("———————" + text + "———————");
    }

    @Override
    public void printEndDivideLine(String serialNum) {
        PrintService.pl.write(PrinterClass.CMD_ALIGN_MIDDLE);

        PrintService.pl.write(PrinterClass.CMD_FONTSIZE_DOUBLE_HIGH);
        PrintService.pl.printText("＊＊＊＊");

        PrintService.pl.write(PrinterClass.CMD_FONTSIZE_DOUBLE);
        PrintService.pl.printText("#" + serialNum + "完");

        PrintService.pl.write(PrinterClass.CMD_FONTSIZE_DOUBLE_HIGH);
        PrintService.pl.printText("＊＊＊＊");
    }

    @Override
    public void printNormalTextLift(String text) {
        PrintService.pl.write(PrinterClass.CMD_ALIGN_LEFT);
        PrintService.pl.write(PrinterClass.CMD_FONTSIZE_NORMAL);
        PrintService.pl.printText(text);
    }

    @Override
    public void printNormalTextCenter(String text) {
        PrintService.pl.write(PrinterClass.CMD_ALIGN_MIDDLE);
        PrintService.pl.write(PrinterClass.CMD_FONTSIZE_NORMAL);
        PrintService.pl.printText(text);
    }

    @Override
    public void printNormalTextRight(String text) {
        PrintService.pl.write(PrinterClass.CMD_ALIGN_RIGHT);
        PrintService.pl.write(PrinterClass.CMD_FONTSIZE_NORMAL);
        PrintService.pl.printText(text);
    }

    @Override
    public void printRiseTextLift(String text) {
        PrintService.pl.write(PrinterClass.CMD_ALIGN_LEFT);
        PrintService.pl.write(PrinterClass.CMD_FONTSIZE_DOUBLE_HIGH);
        PrintService.pl.printText(text);
    }

    @Override
    public void printRiseTextCenter(String text) {
        PrintService.pl.write(PrinterClass.CMD_ALIGN_MIDDLE);
        PrintService.pl.write(PrinterClass.CMD_FONTSIZE_DOUBLE_HIGH);
        PrintService.pl.printText(text);
    }

    @Override
    public void printRiseTextRight(String text) {
        PrintService.pl.write(PrinterClass.CMD_ALIGN_RIGHT);
        PrintService.pl.write(PrinterClass.CMD_FONTSIZE_DOUBLE_HIGH);
        PrintService.pl.printText(text);
    }

    @Override
    public void printLargeTextLift(String text) {
        PrintService.pl.write(PrinterClass.CMD_ALIGN_LEFT);
        PrintService.pl.write(PrinterClass.CMD_FONTSIZE_DOUBLE);
        PrintService.pl.printText(text);
    }

    @Override
    public void printLargeTextCenter(String text) {
        PrintService.pl.write(PrinterClass.CMD_ALIGN_MIDDLE);
        PrintService.pl.write(PrinterClass.CMD_FONTSIZE_DOUBLE);
        PrintService.pl.printText(text);
    }

    @Override
    public void printLargeTextRight(String text) {
        PrintService.pl.write(PrinterClass.CMD_ALIGN_RIGHT);
        PrintService.pl.write(PrinterClass.CMD_FONTSIZE_DOUBLE);
        PrintService.pl.printText(text);
    }

    @Override
    public void scan() {
        PrintService.pl.scan();
    }

    @Override
    public void stopScan() {
        PrintService.pl.stopScan();
    }

    @Override
    public List getCacheDeviceList() {
        return PrintService.pl.getDeviceList();
    }

    @Override
    public void connect(String devicePort) {
        PrintService.pl.connect(devicePort);
    }

    @Override
    public void disconnect() {
        PrintService.pl.disconnect();
    }

    @Override
    public int getPrintState() {
        return PrintService.pl.getState();
    }

    @Override
    public boolean isOpened() {
        return PrintService.pl.IsOpen();
    }

    @Override
    public void openBluetooth() {
        PrintService.pl.open(null);
    }

    @Override
    public void closeBluetooth() {
        PrintService.pl.close(null);
    }

}
