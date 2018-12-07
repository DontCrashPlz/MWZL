package com.pokong.mwzl.setting.bluetooth;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;

import com.pokong.bluetooth.MyBtPrintService;
import com.pokong.library.base.BaseActivity;
import com.pokong.library.util.DialogFactory;
import com.pokong.library.util.LogUtils;
import com.pokong.library.util.SharedPrefUtils;
import com.pokong.library.util.ToastUtils;
import com.pokong.mwzl.R;
import com.pokong.mwzl.adapter.DeviceListAdapter;
import com.pokong.mwzl.eventbus.BluetoothNewDeviceEvent;
import com.pokong.mwzl.eventbus.BluetoothStateChangedEvent;
import com.qs.helper.printer.Device;
import com.qs.helper.printer.PrintService;
import com.qs.helper.printer.PrinterClass;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

/**
 * Created on 2018/12/4 17:01
 * User: Zheng
 * E-mail: zhengCH12138@163.com
 */
public class BluetoothActivity extends BaseActivity<BluetoothPresenter> implements BluetoothContract.View {

    private Switch mDoubleSwitch;//打印双份开关
    private TextView mCurrentDeviceTv;//当前保存设备
    private LinearLayout mSearchDeviceLly;//搜索附近设备按钮
    private ProgressBar mSearchingProgressBar;//正在搜索加载框
    private RecyclerView mDeviceListRecycler;//附近设备列表
    private DeviceListAdapter mDeviceAdapter;//附近设备adapter

    private AlertDialog mConnectingDialog;

    private boolean isScaning = false;
    private Disposable mScanDisposable;

    @Override
    public Context getRealContext() {
        return this;
    }

    @Override
    public int getContentViewRes() {
        return R.layout.activity_bluetooth;
    }

    @Override
    public void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("蓝牙打印设置");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public void initUI() {
        EventBus.getDefault().register(this);

        initDoubleSwitch();

        initCurrentDeviceTv();

        initSearchBtn();

        initDeviceRecycler();
    }

    @Override
    protected BluetoothPresenter getRealPresenter() {
        return new BluetoothPresenter();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()== android.R.id.home)
            finish();
        return true;
    }

    @Override
    public void showConnectingDialog() {
        if (mConnectingDialog == null){
            mConnectingDialog = DialogFactory.createLoadingDialog(getRealContext(), "正在连接设备...");
            mConnectingDialog.setCanceledOnTouchOutside(false);
        }
        if (!mConnectingDialog.isShowing())
            mConnectingDialog.show();
        refreshCurrentDeviceTv();
    }

    @Override
    public void dismissConnectingDialog() {
        if (mConnectingDialog != null && mConnectingDialog.isShowing())
            mConnectingDialog.dismiss();
    }

    @Override
    public void showScaning() {
        mSearchDeviceLly.setClickable(false);
        if (!mSearchingProgressBar.isShown()){
            mSearchingProgressBar.setVisibility(View.VISIBLE);
        }
        isScaning = true;
    }

    @Override
    public void scaningDone() {
        mSearchDeviceLly.setClickable(true);
        if (mSearchingProgressBar.isShown()){
            mSearchingProgressBar.setVisibility(View.INVISIBLE);
        }
        if (!mScanDisposable.isDisposed())
            mScanDisposable.dispose();
        isScaning = false;
    }

    /**
     * 初始化双份打印开关
     */
    private void initDoubleSwitch() {
        mDoubleSwitch = findViewById(R.id.bluetooth_switch_double);
        boolean isDoulePrint = (boolean) SharedPrefUtils.get(getRealContext(), BluetoothPresenter.SP_KEY_IS_DOUBLE_PRINT, false);
        mDoubleSwitch.setChecked(isDoulePrint);
        mDoubleSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> mPresenter.changeDoubleSwitchStatus(isChecked));
    }

    /**
     * 初始化当前保存设备信息
     */
    private void initCurrentDeviceTv() {
        mCurrentDeviceTv = findViewById(R.id.bluetooth_tv_current);
        refreshCurrentDeviceTv();
    }

    /**
     * 刷新当前保存设备信息
     */
    private void refreshCurrentDeviceTv(){
        String currentDeviceName = (String) SharedPrefUtils.get(getRealContext(), BluetoothPresenter.SP_KEY_BLUETOOTH_DEVICE_NAME, "未知设备");
        String currentDevicePort = (String) SharedPrefUtils.get(getRealContext(), BluetoothPresenter.SP_KEY_BLUETOOTH_DEVICE_PORT, "00:00:00:00:00:00");
        String currentDeviceInfo = currentDeviceName + " (" + currentDevicePort + ")";
        mCurrentDeviceTv.setText(currentDeviceInfo);
    }

    /**
     * 初始化扫描按钮
     */
    private void initSearchBtn() {
        mSearchDeviceLly = findViewById(R.id.bluetooth_lly_search);
        mSearchingProgressBar = findViewById(R.id.bluetooth_progress);

        if (mSearchingProgressBar.isShown())
            mSearchingProgressBar.setVisibility(View.INVISIBLE);
        mSearchDeviceLly.setClickable(true);
        mSearchDeviceLly.setOnClickListener(v -> {
            mDeviceAdapter.clear();
            mSearchDeviceLly.setClickable(false);
            mPresenter.scanBluetoothDevice(mDeviceAdapter);
            mScanDisposable = Observable.interval(500, 200, TimeUnit.MILLISECONDS)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(aLong -> {
                        if (MyBtPrintService.getInstance().getPrintState() == PrinterClass.STATE_SCANING){
                            if (!isScaning){
                                showScaning();
                            }
                        }else {
                            scaningDone();
                        }
                    }, throwable -> {
                        scaningDone();
                        MyBtPrintService.getInstance().stopScan();
                    });
        });
    }

    /**
     * 初始化设备列表
     */
    private void initDeviceRecycler() {
        mDeviceListRecycler = findViewById(R.id.bluetooth_recycler);
        mDeviceListRecycler.setLayoutManager(new LinearLayoutManager(getRealContext()));
        mDeviceAdapter = new DeviceListAdapter(this);
        mDeviceListRecycler.setAdapter(mDeviceAdapter);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onBluetoothStateChangedEvent(BluetoothStateChangedEvent event){
        int bluetoothState = event.getCurrentStatus();
        switch (bluetoothState){
            case PrinterClass.STATE_NONE:
                break;
            case PrinterClass.STATE_LISTEN:
                break;
            case PrinterClass.STATE_CONNECTING:
                break;
            case PrinterClass.STATE_CONNECTED:
                break;
            case PrinterClass.LOSE_CONNECT:
                break;
            case PrinterClass.FAILED_CONNECT:
                dismissConnectingDialog();
                //ToastUtils.showShortToast(getRealContext(), "蓝牙连接失败");
                break;
            case PrinterClass.SUCCESS_CONNECT:
                dismissConnectingDialog();
                //ToastUtils.showShortToast(getRealContext(), "蓝牙连接成功");
                //todo 通知Presenter保存设备信息
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onBluetoothNewDeviceEvent(BluetoothNewDeviceEvent event){
        Device device = event.getNewDevice();
        if (mDeviceAdapter != null)
            mDeviceAdapter.addDevice(device);
    }

}
