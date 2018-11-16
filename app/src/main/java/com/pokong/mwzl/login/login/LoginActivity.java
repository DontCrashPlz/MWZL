package com.pokong.mwzl.login.login;

import android.content.Context;

import com.pokong.library.base.BaseActivity;

/**
 * Created on 2018/11/16 9:43
 * User: Zheng
 * E-mail: zhengCH12138@163.com
 */
public class LoginActivity extends BaseActivity<LoginPresenter> {
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
    protected LoginPresenter getRealPresenter() {
        return null;
    }
}
