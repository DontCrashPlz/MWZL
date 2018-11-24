package com.pokong.mwzl.setting;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pokong.library.base.BaseFragment;
import com.pokong.mwzl.order.OrderPresenter;

/**
 * Created on 2018/11/16 15:53
 * User: Zheng
 * E-mail: zhengCH12138@163.com
 */
public class SettingFragment extends BaseFragment<SettingPresenter> {

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
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected SettingPresenter getRealPresenter() {
        return null;
    }
}
