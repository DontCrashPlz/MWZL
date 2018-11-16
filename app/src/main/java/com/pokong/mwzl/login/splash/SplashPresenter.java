package com.pokong.mwzl.login.splash;

import com.pokong.library.base.BasePresenter;
import com.pokong.library.util.Tools;

/**
 * Created on 2018/11/15 16:51
 * User: Zheng
 * E-mail: zhengCH12138@163.com
 */
public class SplashPresenter extends BasePresenter<SplashActivity> implements SplashContract.Presenter {

    @Override
    public void checkUpdate() {
        String currentVersionName = Tools.getVersionName(getView().getRealContext());

    }

}
