package com.pokong.mwzl.shop;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pokong.library.base.BaseFragment;

/**
 * Created on 2018/11/16 15:56
 * User: Zheng
 * E-mail: zhengCH12138@163.com
 */
public class ShopManageFragment extends BaseFragment<ShopManagePresenter> {

    public static ShopManageFragment newInstance(int tag){
        ShopManageFragment instance = new ShopManageFragment();
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
    protected ShopManagePresenter getRealPresenter() {
        return null;
    }
}
