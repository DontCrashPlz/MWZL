package com.pokong.mwzl.login.splash;

/**
 * Created on 2018/11/16 9:08
 * User: Zheng
 * E-mail: zhengCH12138@163.com
 */
public interface SplashContract {

    interface View{
        //进入登录页面
        void gotoLogin();
        //自动登录
        void autoLogin();

    }

    interface Presenter{
        void checkUpdate();
    }

}
