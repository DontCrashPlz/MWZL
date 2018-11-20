package com.pokong.mwzl.login.login;

import com.pokong.library.base.BasePresenter;
import com.pokong.library.util.SharedPrefUtils;
import com.pokong.library.util.ToastUtils;
import com.pokong.library.util.Tools;
import com.pokong.mwzl.login.splash.SplashPresenter;

/**
 * Created on 2018/11/16 9:43
 * User: Zheng
 * E-mail: zhengCH12138@163.com
 */
public class LoginPresenter extends BasePresenter<LoginActivity> implements LoginContract.Presenter {

    @Override
    public String getSharedUserName() {
        if (SharedPrefUtils.contains(getView().getRealContext(), SplashPresenter.SP_KEY_USERNAME))
            return (String) SharedPrefUtils.get(getView().getRealContext(), SplashPresenter.SP_KEY_USERNAME, "");
        return null;
    }

    @Override
    public String getSharedPassword() {
        if (SharedPrefUtils.contains(getView().getRealContext(), SplashPresenter.SP_KEY_LOGINPASS))
            return (String) SharedPrefUtils.get(getView().getRealContext(), SplashPresenter.SP_KEY_LOGINPASS, "");
        return null;
    }

    @Override
    public boolean getSharedRememberStatus() {
        if (SharedPrefUtils.contains(getView().getRealContext(), SplashPresenter.SP_KEY_ISREMEMBER))
            return (boolean) SharedPrefUtils.get(getView().getRealContext(), SplashPresenter.SP_KEY_ISREMEMBER, "");
        return false;
    }

    @Override
    public void tryToLogin() {
        String userName = getView().getUserNameInput();
        if (Tools.isBlank(userName)){
            ToastUtils.showShortToast(getView().getRealContext(), "请输入有效用户名");
            return;
        }
        String password = getView().getPasswordInput();
        if (Tools.isBlank(password)){
            ToastUtils.showShortToast(getView().getRealContext(), "请输入有效密码");
            return;
        }
        //todo 请求登录接口
        getView().skipToHome();
    }
}
