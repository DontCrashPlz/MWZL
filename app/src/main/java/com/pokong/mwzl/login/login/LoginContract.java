package com.pokong.mwzl.login.login;

/**
 * Created on 2018/11/16 9:44
 * User: Zheng
 * E-mail: zhengCH12138@163.com
 */
public interface LoginContract {
    interface View{

        /**
         * 获取username输入框内容
         * @return
         */
        String getUserNameInput();

        /**
         * 获取password输入框内容
         * @return
         */
        String getPasswordInput();

        /**
         * 获取是否记住密码复选框状态
         * @return
         */
        boolean getRememberCheckboxStatus();

        /**
         * 跳转到主界面
         */
        void skipToHome();
    }
    interface Presenter{
        /**
         * 提取SharedPreference中保存的username
         * @return
         */
        String getSharedUserName();

        /**
         * 提取SharedPreference中保存的password
         * @return
         */
        String getSharedPassword();

        /**
         * 提取SharedPreference中保存的记住密码状态
         * @return
         */
        boolean getSharedRememberStatus();

        /**
         * 尝试进行登录
         */
        void tryToLogin();
    }
}
