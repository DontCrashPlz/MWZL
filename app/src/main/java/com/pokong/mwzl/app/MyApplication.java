package com.pokong.mwzl.app;

import com.pokong.library.base.BaseApplication;
import com.pokong.mwzl.data.bean.business.ShopInfoResponseBean;

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

    private String appToken;

    private ShopInfoResponseBean shopInfo = null;

    public String getAppToken() {
        return appToken;
    }

    public void setAppToken(String appToken) {
        this.appToken = appToken;
    }

    public ShopInfoResponseBean getShopInfo() {
        return shopInfo;
    }

    public void setShopInfo(ShopInfoResponseBean shopInfo) {
        this.shopInfo = shopInfo;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mSingleInstance= this;
    }

}
