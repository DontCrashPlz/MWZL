package com.pokong.mwzl;

import android.content.Context;

import com.pokong.library.base.BaseActivity;
import com.pokong.library.base.BasePresenter;

/**
 * Created on 2018/11/15 14:09
 * User: Zheng
 * E-mail: zhengCH12138@163.com
 */
public class HomeActivity extends BaseActivity<HomePresenter> {

    @Override
    public Context getRealContext() {
        return null;
    }

    @Override
    public int getContentViewRes() {
        return 0;
    }

    @Override
    public void initToolbar() {
    }

    @Override
    public void initUI() {
    }

    @Override
    protected HomePresenter getRealPresenter() {
        return null;
    }
}
