package com.pokong.mwzl.login.splash;

import com.pokong.library.base.BasePresenter;
import com.pokong.library.util.SharedPrefUtils;
import com.pokong.library.util.Tools;
import com.pokong.mwzl.data.bean.UpdateInfoEntity;

/**
 * Created on 2018/11/15 16:51
 * User: Zheng
 * E-mail: zhengCH12138@163.com
 */
public class SplashPresenter extends BasePresenter<SplashActivity> implements SplashContract.Presenter {

    public static final String SP_KEY_AUTOLOGIN = "auto_login";//sharepreference key --- 自动登录
    public static final String SP_KEY_ISREMEMBER = "remember_password";//sharepreference key --- 记住密码
    public static final String SP_KEY_USERNAME = "username";//sharepreference key --- 帐号
    public static final String SP_KEY_LOGINPASS = "login_pass";//sharepreference key --- 密码

    @Override
    public void checkUpdate() {
        String currentVersionName = Tools.getVersionName(getView().getRealContext());
        //todo 获取远程版本号
        UpdateInfoEntity entity = new UpdateInfoEntity();
        entity.setUrl("www.update.com");
        entity.setVersion("1.0");
        String remoteVersionName = entity.getVersion();
        new Thread(() -> {
            try {
                Thread.sleep(1500);
                if (Tools.isBlank(currentVersionName)//不需要更新
                        || Tools.isBlank(remoteVersionName)
                        || currentVersionName.equals(remoteVersionName)){
                    //todo 程序继续执行，判断是否自动登录
                    checkAutoLogin();
                }else {//需要更新
                    //todo 通知页面需要更新
                    getView().showUpdateDialog(entity);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

//        if (Tools.isBlank(currentVersionName)//不需要更新
//                || Tools.isBlank(remoteVersionName)
//                || currentVersionName.equals(remoteVersionName)){
//            //todo 程序继续执行，判断是否自动登录
//            checkAutoLogin();
//        }else {//需要更新
//            //todo 通知页面需要更新
//            getView().showUpdateDialog(entity);
//        }
    }

    @Override
    public void checkAutoLogin() {
        boolean isAutoLogin = false;
        if (SharedPrefUtils.contains(getView().getRealContext(), SP_KEY_AUTOLOGIN)){
            isAutoLogin = (boolean) SharedPrefUtils.get(getView().getRealContext(), SP_KEY_AUTOLOGIN, false);
        }
        if (isAutoLogin){//如果需要自动登录
            //todo 判断保存的账号信息是否有效
            String userName = "";
            String loginPass = "";
            if (SharedPrefUtils.contains(getView().getRealContext(), SP_KEY_USERNAME)){
                userName = (String) SharedPrefUtils.get(getView().getRealContext(), SP_KEY_USERNAME, "");
            }
            if (SharedPrefUtils.contains(getView().getRealContext(), SP_KEY_LOGINPASS)){
                loginPass = (String) SharedPrefUtils.get(getView().getRealContext(), SP_KEY_LOGINPASS, "");
            }
            if (!Tools.isBlank(userName) && !Tools.isBlank(loginPass)){
                //todo 尝试进行登录

            }else {
                //todo 通知页面跳转到登录页面
                getView().skipToLogin();
            }
        }else {//不需要自动登录
            //todo 通知页面跳转到登录页面
            getView().skipToLogin();
        }

    }

    @Override
    public void tryToLogin(String userName, String password) {
        //todo 调用登录接口进行登录
    }

}
