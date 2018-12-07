package com.pokong.mwzl.setting;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pokong.library.base.BaseFragment;
import com.pokong.mwzl.R;
import com.pokong.mwzl.order.OrderPresenter;
import com.pokong.mwzl.setting.bluetooth.BluetoothActivity;

/**
 * Created on 2018/11/16 15:53
 * User: Zheng
 * E-mail: zhengCH12138@163.com
 */
public class SettingFragment extends BaseFragment<SettingPresenter> implements SettingContract.View {

    private TextView mBluetoothTv, mAboutUsTv;

    public static SettingFragment newInstance(int tag){
        SettingFragment instance = new SettingFragment();
        Bundle args = new Bundle();
        args.putInt("tag", tag);
        instance.setArguments(args);
        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_setting, container, false);

        initView(view);

        return view;
    }

    private void initView(View view) {
        mBluetoothTv = view.findViewById(R.id.homesetting_tv_bluetooth);
        mBluetoothTv.setOnClickListener(v -> clickBluetooth());
        mAboutUsTv = view.findViewById(R.id.homesetting_tv_about);
        mAboutUsTv.setOnClickListener(v -> clickAboutUs());
    }

    @Override
    protected SettingPresenter getRealPresenter() {
        return new SettingPresenter();
    }

    @Override
    public void clickBluetooth() {
        Intent intent = new Intent(getContext(), BluetoothActivity.class);
        startActivity(intent);
    }

    @Override
    public void clickAboutUs() {

    }
}
