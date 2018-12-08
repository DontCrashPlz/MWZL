package com.pokong.mwzl.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pokong.bluetooth.MyBtPrintService;
import com.pokong.library.util.SharedPrefUtils;
import com.pokong.library.util.Tools;
import com.pokong.mwzl.R;
import com.pokong.mwzl.setting.bluetooth.BluetoothActivity;
import com.pokong.mwzl.setting.bluetooth.BluetoothPresenter;
import com.qs.helper.printer.Device;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created on 2018/11/27 23:38
 * User: Zheng
 * E-mail: zhengCH12138@163.com
 */
public class DeviceListAdapter extends RecyclerView.Adapter<DeviceListAdapter.DeviceViewHolder> {

    private BluetoothActivity mActivity;

    private Context mContext;

    private ArrayList<Device> mDeviceList;

    public DeviceListAdapter(BluetoothActivity activity){
        mActivity = activity;
        mContext = activity.getRealContext();
        mDeviceList = new ArrayList<>();
    }

    @NonNull
    @Override
    public DeviceViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_item_device, viewGroup, false);
        return new DeviceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DeviceViewHolder deviceViewHolder, int i) {
        Device device = mDeviceList.get(i);
        String deviceName = "未知设备";
        if (!Tools.isBlank(device.deviceName)){
            deviceName = device.deviceName;
        }
        String devicePort = "00:00:00:00:00:00";
        if (!Tools.isBlank(device.deviceAddress)){
            devicePort = device.deviceAddress;
        }
        deviceViewHolder.mDeviceNameTv.setText(deviceName);
        deviceViewHolder.mDevicePortTv.setText(devicePort);

        String finalDeviceName = deviceName;
        String finalDevicePort = devicePort;
        deviceViewHolder.mDevicePanelLly.setOnClickListener(v -> {
            mActivity.showConnectingDialog();
            SharedPrefUtils.put(mContext, BluetoothPresenter.SP_KEY_BLUETOOTH_DEVICE_NAME, finalDeviceName);
            SharedPrefUtils.put(mContext, BluetoothPresenter.SP_KEY_BLUETOOTH_DEVICE_PORT, finalDevicePort);
            MyBtPrintService.getInstance().connect(finalDevicePort);
        });
    }

    @Override
    public int getItemCount() {
        return mDeviceList.size();
    }

    /**
     * 添加一个新设备
     * @param newDevice
     */
    public void addDevice(Device newDevice){
        if (newDevice != null){
            for (Device device : mDeviceList){
                if (newDevice.deviceAddress.equals(device.deviceAddress)){
                    return;
                }
            }
            mDeviceList.add(newDevice);
            notifyItemInserted(mDeviceList.size() - 1);
        }
    }

    /**
     * 添加一个设备列表
     * @param devices
     */
    public void addDeviceList(List<Device> devices){
        if (devices != null && devices.size() > 0){
            Iterator<Device> iterator = devices.iterator();
            while (mDeviceList.size() > 0 && iterator.hasNext()){
                for (Device innerDevice : mDeviceList){
                    if (iterator.next().deviceAddress.equals(innerDevice.deviceAddress)){
                        iterator.remove();
                    }
                }
            }
            if (devices.size() <= 0){
                return;
            }
            mDeviceList.addAll(devices);
            notifyItemRangeInserted(0, devices.size());
        }
    }

    public void clear(){
        if (mDeviceList != null && mDeviceList.size() > 0){
            notifyItemRangeRemoved(0, mDeviceList.size());
            mDeviceList.clear();
        }
    }

    class DeviceViewHolder extends RecyclerView.ViewHolder{

        private LinearLayout mDevicePanelLly;
        private TextView mDeviceNameTv, mDevicePortTv;

        public DeviceViewHolder(@NonNull View itemView) {
            super(itemView);
            mDevicePanelLly = itemView.findViewById(R.id.device_lly_panel);
            mDeviceNameTv = itemView.findViewById(R.id.device_tv_name);
            mDevicePortTv = itemView.findViewById(R.id.device_tv_port);
        }
    }

}
