package com.pokong.library.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.pokong.library.app.ActivityManager;
import com.pokong.library.util.LogUtils;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by Zheng on 2017/10/16.
 */

public abstract class BaseActivity <T extends BasePresenter> extends AppCompatActivity implements IBaseView {

    protected final String ACTIVITY_TAG = this.getClass().getSimpleName();

    protected CompositeDisposable compositeDisposable;

    protected T mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityManager.getInstance().addActivity(this);
        LogUtils.d(ACTIVITY_TAG, ACTIVITY_TAG + " was Created.");

        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }

        if (getContentViewRes() != 0) setContentView(getContentViewRes());
        //ButterKnife.bind(this);

        mPresenter = getRealPresenter();
        if (mPresenter != null) mPresenter.attachView(this);

        initToolbar();
        initUI();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        ActivityManager.getInstance().removeActivity(this);
        LogUtils.d(ACTIVITY_TAG, ACTIVITY_TAG + " was Destroyed.");

        clearNetWork();

        if (mPresenter != null ) mPresenter.detachView();
    }

    public void addNetWork(Disposable disposable) {
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }
        compositeDisposable.add(disposable);
    }

    public void clearNetWork() {
        if (compositeDisposable != null) {
            compositeDisposable.clear();
            compositeDisposable = null;
        }
    }

    /**
     * 获取页面实际的Context对象
     * @return
     */
    public abstract Context getRealContext();

    /**
     * 设置页面实际的layoutResID
     * setContentView(int layoutResID)
     * @return
     */
    public abstract int getContentViewRes();

    /**
     * 初始化页面标题头
     */
    public abstract void initToolbar();

    /**
     * 执行onCreate()方法中初始化页面相关的操作
     */
    public abstract void initUI();

    /**
     * 设置页面实际的Presenter
     * @return
     */
    protected abstract T getRealPresenter();

}
