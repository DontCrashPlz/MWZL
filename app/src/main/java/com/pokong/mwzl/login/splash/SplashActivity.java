package com.pokong.mwzl.login.splash;

import android.content.Context;

import com.pokong.library.base.BaseActivity;

/**
 * Created on 2018/11/15 16:16
 * User: Zheng
 * E-mail: zhengCH12138@163.com
 */
public class SplashActivity extends BaseActivity<SplashPresenter> {

    @Override
    public Context getRealContext() {
        return this;
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
    protected SplashPresenter getRealPresenter() {
        return new SplashPresenter();
    }

}
