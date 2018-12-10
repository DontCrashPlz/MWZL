package com.pokong.mwzl;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioButton;

import com.pokong.bluetooth.MyBtPrintService;
import com.pokong.bluetooth.NF5804PrintAdapter;
import com.pokong.library.base.BaseActivity;
import com.pokong.library.util.LogUtils;
import com.pokong.library.util.SharedPrefUtils;
import com.pokong.library.util.ToastUtils;
import com.pokong.library.util.Tools;
import com.pokong.mwzl.app.MyApplication;
import com.pokong.mwzl.data.DataRequestCallback;
import com.pokong.mwzl.data.bean.GoodsEntity;
import com.pokong.mwzl.data.bean.OrderListItemEntity;
import com.pokong.mwzl.data.bean.business.ShopInfoRequestBean;
import com.pokong.mwzl.data.bean.business.ShopInfoResponseBean;
import com.pokong.mwzl.data.source.MWZLHttpDataRepository;
import com.pokong.mwzl.eventbus.BluetoothNewDeviceEvent;
import com.pokong.mwzl.eventbus.BluetoothStateChangedEvent;
import com.pokong.mwzl.order.OrderFragment;
import com.pokong.mwzl.setting.SettingFragment;
import com.pokong.mwzl.setting.bluetooth.BluetoothPresenter;
import com.pokong.mwzl.shop.ShopManageFragment;
import com.qs.helper.printer.Device;
import com.qs.helper.printer.PrintService;
import com.qs.helper.printer.PrinterClass;
import com.qs.helper.printer.bt.BtService;

import org.greenrobot.eventbus.EventBus;

import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created on 2018/11/15 14:09
 * User: Zheng
 * E-mail: zhengCH12138@163.com
 */
public class HomeActivity extends BaseActivity<HomePresenter> implements CompoundButton.OnCheckedChangeListener {

    private RadioButton mOrderRbtn;
    private RadioButton mShopRbtn;
    private RadioButton mSettingRbtn;

    private FragmentManager fragmentManager;

    private Handler mBluetoothStatusHandler = null;
    private Handler mBluetoothScanHandler = null;

    @Override
    public Context getRealContext() {
        return this;
    }

    @Override
    public int getContentViewRes() {
        return R.layout.activity_home;
    }

    @Override
    public void initToolbar() {
        View decorView = getWindow().getDecorView();
        int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
        decorView.setSystemUiVisibility(option);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
    }

    @Override
    public void initUI() {

        fragmentManager = getSupportFragmentManager();

        mOrderRbtn = findViewById(R.id.home_rbtn_order);
        mOrderRbtn.setOnCheckedChangeListener(this);
        mShopRbtn = findViewById(R.id.home_rbtn_shop);
        mShopRbtn.setOnCheckedChangeListener(this);
        mSettingRbtn = findViewById(R.id.home_rbtn_setting);
        mSettingRbtn.setOnCheckedChangeListener(this);

        //默认选中订单管理页
        mOrderRbtn.setChecked(true);

        //加载并通知页面刷新店铺信息
        ShopInfoRequestBean paramsBean = new ShopInfoRequestBean();
        paramsBean.setAppToken(MyApplication.getInstance().getAppToken());
        addNetWork(MWZLHttpDataRepository.getInstance()
                .getShopInfo(paramsBean, new DataRequestCallback<ShopInfoResponseBean>() {
                    @Override
                    public void onSuccessed(ShopInfoResponseBean shopInfoResponseBean) {
                        MyApplication.getInstance().setShopInfo(shopInfoResponseBean);
                        Fragment currentFragment = fragmentManager.findFragmentById(R.id.home_fly_fragment);
                        if (currentFragment instanceof OrderFragment) {
                            ((OrderFragment) currentFragment).refreshShopInfo();
                        } else if (currentFragment instanceof ShopManageFragment) {
                            ((ShopManageFragment) currentFragment).refreshShopInfo();
                        }
                    }

                    @Override
                    public void onFailed(String errorMsg) {
                        ToastUtils.showShortToast(getRealContext(), "店铺信息获取失败");
                    }
                }));

        //初始化蓝牙打印机服务
        initBluetoothService();
    }

    @Override
    protected HomePresenter getRealPresenter() {
        return null;
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        int vId = buttonView.getId();
        switch (vId) {
            case R.id.home_rbtn_order: {
                if (isChecked) {
                    fragmentManager.beginTransaction()
                            .replace(R.id.home_fly_fragment, OrderFragment.newInstance(0))
                            .commit();
                }
                break;
            }
            case R.id.home_rbtn_shop: {
                if (isChecked) {
                    fragmentManager.beginTransaction()
                            .replace(R.id.home_fly_fragment, ShopManageFragment.newInstance(0))
                            .commit();
                }
                break;
            }
            case R.id.home_rbtn_setting: {
                if (isChecked) {
                    fragmentManager.beginTransaction()
                            .replace(R.id.home_fly_fragment, SettingFragment.newInstance(0))
                            .commit();
                }
                break;
            }
        }
    }

    private void initBluetoothService() {
        mBluetoothStatusHandler = new BluetoothStatusHandler(this);
        mBluetoothScanHandler = new BluetoothScanHandler(this);

        BtService btService = new BtService(getRealContext(), mBluetoothStatusHandler, mBluetoothScanHandler);
        MyBtPrintService.Adapter adapter = new NF5804PrintAdapter(btService);
        MyBtPrintService.getInstance().setAdapter(adapter);

        if (MyBtPrintService.getInstance().isOpened()){
            String savedDevicePort = (String) SharedPrefUtils.get(getRealContext(), BluetoothPresenter.SP_KEY_BLUETOOTH_DEVICE_PORT, "00:00:00:00:00:00");
            if (!"00:00:00:00:00:00".equals(savedDevicePort))
                MyBtPrintService.getInstance().connect(savedDevicePort);
        }else {
            ToastUtils.showLongToast(getRealContext(), "检测到蓝牙未开启，请开启蓝牙后到蓝牙设置页面连接设备");
        }
    }

    /**
     * 蓝牙状态Handler
     */
    static class BluetoothStatusHandler extends Handler {
        private WeakReference<HomeActivity> mActivityReference;

        public BluetoothStatusHandler(HomeActivity activity) {
            mActivityReference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            HomeActivity activityReference = mActivityReference.get();
            switch (msg.what) {
                case PrinterClass.MESSAGE_STATE_CHANGE:
                    switch (msg.arg1) {
                        case PrinterClass.STATE_NONE:
                            break;
                        case PrinterClass.STATE_LISTEN:
                            break;
                        case PrinterClass.STATE_CONNECTING:
                            break;
                        case PrinterClass.STATE_CONNECTED:
                            break;
                        case PrinterClass.LOSE_CONNECT:
                            ToastUtils.showShortToast(activityReference.getRealContext(), "蓝牙连接已断开");
                            break;
                        case PrinterClass.FAILED_CONNECT:
                            ToastUtils.showShortToast(activityReference.getRealContext(), "蓝牙连接失败");
                            EventBus.getDefault()
                                    .post(new BluetoothStateChangedEvent(PrinterClass.FAILED_CONNECT,
                                            "蓝牙连接失败"));
                            Fragment failedFragment = activityReference.fragmentManager.findFragmentById(R.id.home_fly_fragment);
                            if (failedFragment instanceof OrderFragment) {
                                ((OrderFragment) failedFragment).showBluetoothBreakIcon();
                            } else if (failedFragment instanceof ShopManageFragment) {
                                ((ShopManageFragment) failedFragment).showBluetoothBreakIcon();
                            }
                            break;
                        case PrinterClass.SUCCESS_CONNECT:
                            ToastUtils.showShortToast(activityReference.getRealContext(), "蓝牙连接成功");
                            EventBus.getDefault()
                                    .post(new BluetoothStateChangedEvent(PrinterClass.SUCCESS_CONNECT,
                                            "蓝牙连接成功"));
                            Fragment succeedFragment = activityReference.fragmentManager.findFragmentById(R.id.home_fly_fragment);
                            if (succeedFragment instanceof OrderFragment) {
                                ((OrderFragment) succeedFragment).showBluetoothConnectedIcon();
                            } else if (succeedFragment instanceof ShopManageFragment) {
                                ((ShopManageFragment) succeedFragment).showBluetoothConnectedIcon();
                            }
                            break;
                    }
                    break;
                case PrinterClass.MESSAGE_READ:
                    byte[] readBuf = (byte[]) msg.obj;
                    LogUtils.e("BluetoothStatusHandler", "readBuf:" + readBuf[0]);
                    if (readBuf[0] == 0x13) {
                        PrintService.isFUll = true;
                        ToastUtils.showShortToast(activityReference.getRealContext(),
                                activityReference.getResources().getString(R.string.str_printer_state) + ":" + activityReference.getResources().getString(R.string.str_printer_bufferfull));
                    } else if (readBuf[0] == 0x11) {
                        PrintService.isFUll = false;
                        ToastUtils.showShortToast(activityReference.getRealContext(),
                                activityReference.getResources().getString(R.string.str_printer_state) + ":" + activityReference.getResources().getString(R.string.str_printer_buffernull));
                    } else if (readBuf[0] == 0x08) {
                        ToastUtils.showShortToast(activityReference.getRealContext(),
                                activityReference.getResources().getString(R.string.str_printer_state) + ":" + activityReference.getResources().getString(R.string.str_printer_nopaper));
                    } else if (readBuf[0] == 0x01) {
                        ToastUtils.showShortToast(activityReference.getRealContext(),
                                activityReference.getResources().getString(R.string.str_printer_state) + ":" + activityReference.getResources().getString(R.string.str_printer_printing));
                    } else if (readBuf[0] == 0x04) {
                        ToastUtils.showShortToast(activityReference.getRealContext(),
                                activityReference.getResources().getString(R.string.str_printer_state) + ":" + activityReference.getResources().getString(R.string.str_printer_hightemperature));
                    } else if (readBuf[0] == 0x02) {
                        ToastUtils.showShortToast(activityReference.getRealContext(),
                                activityReference.getResources().getString(R.string.str_printer_state) + ":" + activityReference.getResources().getString(R.string.str_printer_lowpower));
                    } else {
                        String readMessage = new String(readBuf, 0, msg.arg1);
                        LogUtils.e("BluetoothStatusHandler", "readMessage=" + readMessage);
                        if (readMessage.contains("800")) {// 80mm paper
                            PrintService.imageWidth = 72;
                            ToastUtils.showShortToast(activityReference.getRealContext(), "80mm");
                        } else if (readMessage.contains("580")) {// 58mm paper
                            PrintService.imageWidth = 48;
                            ToastUtils.showShortToast(activityReference.getRealContext(), "58mm");
                        }
                    }
                    break;
                case PrinterClass.MESSAGE_WRITE:
                    break;
            }
        }
    }

    /**
     * 蓝牙扫描Handler
     */
    static class BluetoothScanHandler extends Handler {
        private WeakReference<HomeActivity> mActivityReference;

        public BluetoothScanHandler(HomeActivity activity) {
            mActivityReference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            HomeActivity activityReference = mActivityReference.get();
            switch (msg.what) {
                case 0:
                    break;
                case 1:
                    Device d = (Device) msg.obj;
                    if (d != null) {
                        //todo 将搜索到的设备以广播的方式发送出去，在蓝牙设置页面接收
                        EventBus.getDefault()
                                .post(new BluetoothNewDeviceEvent(d));
                    }
                    break;
                case 2:
                    break;
            }
        }
    }

    /**
     * 打印订单
     *
     * @param order
     */
    public static void printOrder(OrderListItemEntity order) {
        MyBtPrintService.getInstance().nextLine();
        MyBtPrintService.getInstance().printNormalDivideLine();

        MyBtPrintService.getInstance().nextLine();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日HH:mm:ss");
        String currentTimeStr = dateFormat.format(new Date());
        MyBtPrintService.getInstance().printNormalTextLift("打印时间:" + currentTimeStr);

        MyBtPrintService.getInstance().nextLine();
        MyBtPrintService.getInstance().printNormalDivideLine();

        MyBtPrintService.getInstance().nextLine();
        MyBtPrintService.getInstance().printLargeTextCenter("#" + order.getOrder_serial_num());

        MyBtPrintService.getInstance().nextLine();
        MyBtPrintService.getInstance().printNormalTextCenter(order.getStore_name());

        MyBtPrintService.getInstance().nextLine();
        MyBtPrintService.getInstance().printLargeTextCenter("在线支付(已支付)");

        MyBtPrintService.getInstance().nextLine();
        MyBtPrintService.getInstance().printNormalTextCenter("订单号:" + order.getOrder_id());

        MyBtPrintService.getInstance().nextLine();
        MyBtPrintService.getInstance().printNormalTextCenter("下单时间:" + order.getCreate_time());

        MyBtPrintService.getInstance().nextLine();
        MyBtPrintService.getInstance().printNormalDevideLineWithText("商品");

        for (GoodsEntity entity : order.getGoodlist()) {
            printGoodsInfo(entity);
        }

        MyBtPrintService.getInstance().nextLine();
        MyBtPrintService.getInstance().printNormalDivideLine();

        MyBtPrintService.getInstance().nextLine();
        MyBtPrintService.getInstance().printLargeTextLift("总计:" + Tools.formatRmbStr(order.getTotalprice()));

        MyBtPrintService.getInstance().nextLine();
        MyBtPrintService.getInstance().printLargeTextLift("备注:" + order.getMsg());

        MyBtPrintService.getInstance().nextLine();
        MyBtPrintService.getInstance().printEndDivideLine(order.getOrder_serial_num());

        MyBtPrintService.getInstance().nextLine();
        MyBtPrintService.getInstance().nextLine();
        MyBtPrintService.getInstance().nextLine();
        MyBtPrintService.getInstance().nextLine();
        MyBtPrintService.getInstance().nextLine();
        MyBtPrintService.getInstance().printWorkArea();
    }

    static void printGoodsInfo(GoodsEntity goods) {
        String goodsName = goods.getGoods_name();
        if (goodsName.length() <= 5) {//todo 打印一行
            int nameSpaceNum = 10 - (goodsName.length() * 2);
            while (nameSpaceNum > 0) {
                goodsName = goodsName + " ";
                nameSpaceNum--;
            }

            String specStr = goods.getSpec_info();
            if ("".equals(specStr) || "null".equals(specStr)) {
                specStr = "    ";
            } else {
                if (specStr.length() == 2) {
                    specStr = specStr + " ";
                }
            }

            String countStr = Tools.formatNumStr(goods.getCount());
            if (countStr.length() == 2) {
                countStr = countStr + " ";
            }

            String priceStr = Tools.formatRmbStr(goods.getTotal_price());

            String goodsStr = goodsName + "  " + specStr + "  " + countStr + "  " + priceStr;

            MyBtPrintService.getInstance().nextLine();
            MyBtPrintService.getInstance().printNormalTextLift(goodsStr);
        } else {//todo 打印两行
            String specStr = goods.getSpec_info();
            if ("".equals(specStr) || "null".equals(specStr)) {
                specStr = "    ";
            } else {
                if (specStr.length() == 2) {
                    specStr = specStr + " ";
                }
            }

            String countStr = Tools.formatNumStr(goods.getCount());
            if (countStr.length() == 2) {
                countStr = countStr + " ";
            }

            String priceStr = Tools.formatRmbStr(goods.getTotal_price());

            String secondLineStr = "            " + specStr + "  " + countStr + "  " + priceStr;

            MyBtPrintService.getInstance().nextLine();
            MyBtPrintService.getInstance().printNormalTextLift(goodsName);
            MyBtPrintService.getInstance().nextLine();
            MyBtPrintService.getInstance().printNormalTextLift(secondLineStr);
        }
    }

}
