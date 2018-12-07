package com.pokong.mwzl.setting.bluetooth;

import com.pokong.bluetooth.MyBtPrintService;
import com.pokong.library.base.BasePresenter;
import com.pokong.library.util.LogUtils;
import com.pokong.library.util.SharedPrefUtils;
import com.pokong.mwzl.adapter.DeviceListAdapter;
import com.qs.helper.printer.Device;

import java.util.List;

/**
 * Created on 2018/12/4 18:48
 * User: Zheng
 * E-mail: zhengCH12138@163.com
 */
public class BluetoothPresenter extends BasePresenter<BluetoothActivity> implements BluetoothContract.Presenter {

    public static final String SP_KEY_IS_DOUBLE_PRINT = "is_double_print";
    public static final String SP_KEY_BLUETOOTH_DEVICE_NAME = "bluetooth_device_name";
    public static final String SP_KEY_BLUETOOTH_DEVICE_PORT = "bluetooth_device_port";

    @Override
    public void changeDoubleSwitchStatus(boolean isDoublePrint) {
        if (isDoublePrint){
            SharedPrefUtils.put(getView().getRealContext(), SP_KEY_IS_DOUBLE_PRINT, true);
        }else {
            SharedPrefUtils.put(getView().getRealContext(), SP_KEY_IS_DOUBLE_PRINT, false);
        }
    }

    @Override
    public void scanBluetoothDevice(DeviceListAdapter deviceAdapter) {
        MyBtPrintService.getInstance().scan();
        if (deviceAdapter != null){
            List<Device> devices = MyBtPrintService.getInstance().getCacheDeviceList();
            if (devices != null && devices.size() > 0){
                deviceAdapter.addDeviceList(devices);
            }
        }


    }

}
