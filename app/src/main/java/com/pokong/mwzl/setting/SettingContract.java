package com.pokong.mwzl.setting;

/**
 * Created on 2018/11/16 15:54
 * User: Zheng
 * E-mail: zhengCH12138@163.com
 */
public interface SettingContract {
    interface View{
        /**
         * "蓝牙打印设置" 按钮点击事件
         */
        void clickBluetooth();

        /**
         * "关于我们" 按钮点击事件
         */
        void clickAboutUs();
    }

    interface Presenter{

    }
}
