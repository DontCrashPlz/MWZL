package com.pokong.library.base;

import android.app.Service;

import com.pokong.library.app.ServiceManager;

/**
 * Created on 2018/11/15 14:24
 * User: Zheng
 * E-mail: zhengCH12138@163.com
 */
public abstract class BaseService extends Service {

    @Override
    public void onCreate() {
        super.onCreate();
        ServiceManager.getInstance().addService(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ServiceManager.getInstance().removeService(this);
    }

}
