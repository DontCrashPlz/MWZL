package com.pokong.mwzl.app;

import com.pokong.library.base.BaseApplication;

/**
 * Created on 2018/11/16 17:03
 * User: Zheng
 * E-mail: zhengCH12138@163.com
 */
public class MyApplication extends BaseApplication {

    private static MyApplication mSingleInstance;

    public static MyApplication getInstance(){
        return mSingleInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mSingleInstance= this;
    }

}
