package com.pokong.mwzl.notice.list;

import android.content.Context;

import com.pokong.library.base.BaseActivity;

/**
 * Created on 2018/11/16 9:48
 * User: Zheng
 * E-mail: zhengCH12138@163.com
 */
public class NoticeListActivity extends BaseActivity<NoticeListPresenter> {
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
    protected NoticeListPresenter getRealPresenter() {
        return null;
    }
}
