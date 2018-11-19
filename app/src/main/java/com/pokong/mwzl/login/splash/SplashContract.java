package com.pokong.mwzl.login.splash;

import com.pokong.mwzl.data.bean.UpdateInfoEntity;

/**
 * Created on 2018/11/16 9:08
 * User: Zheng
 * E-mail: zhengCH12138@163.com
 */
public interface SplashContract {

    interface View{
        /**
         * 跳转到登录页面
         */
        void skipToLogin();

        /**
         * 自动登录成功 ，跳转到主界面
         */
        void skipToHome();

        /**
         * 需要更新，显示更新弹窗
         */
        void showUpdateDialog(UpdateInfoEntity updateInfoEntity);

    }

    interface Presenter{
        /**
         * 检查更新
         */
        void checkUpdate();

        /**
         * 检查自动登录
         */
        void checkAutoLogin();

        /**
         * 尝试进行自动登录
         * @param userName
         * @param password
         */
        void tryToLogin(String userName, String password);
    }

}
