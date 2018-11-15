package com.pokong.library.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.pokong.library.util.LogUtils;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by Zheng on 2018/4/14.
 */

public abstract class BaseFragment <T extends BasePresenter> extends Fragment implements IBaseView {

    private final String fragmentTag = this.getClass().getSimpleName();

    public CompositeDisposable compositeDisposable;

    protected T mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LogUtils.d(fragmentTag, fragmentTag + "is Created!");
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }

        mPresenter = getRealPresenter();
        if (mPresenter != null) mPresenter.attachView(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtils.d(fragmentTag, fragmentTag + "is Destroyed!");

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
        }
    }

    /**
     * 设置页面实际的Presenter
     * @return
     */
    protected abstract T getRealPresenter();

}
