package com.pokong.mwzl.login.login;

import com.pokong.library.base.BasePresenter;
import com.pokong.library.util.EncryptUtils;
import com.pokong.library.util.SharedPrefUtils;
import com.pokong.library.util.ToastUtils;
import com.pokong.library.util.Tools;
import com.pokong.mwzl.app.MyApplication;
import com.pokong.mwzl.data.DataRequestCallback;
import com.pokong.mwzl.data.bean.personal.LoginRequestBean;
import com.pokong.mwzl.data.bean.personal.LoginResponseBean;
import com.pokong.mwzl.data.source.MWZLHttpDataRepository;
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
            return (boolean) SharedPrefUtils.get(getView().getRealContext(), SplashPresenter.SP_KEY_ISREMEMBER, false);
        return false;
    }

    @Override
    public void tryToLogin() {

        String userName = getView().getUserNameInput();
        if (!Tools.isValidUserName(userName)){
            ToastUtils.showShortToast(getView().getRealContext(), "请输入有效用户名");
            return;
        }
        String password = getView().getPasswordInput();
        if (!Tools.isValidPassword(password)){
            ToastUtils.showShortToast(getView().getRealContext(), "请输入有效密码");
            return;
        }
        //todo 请求登录接口
        LoginRequestBean paramsBean = new LoginRequestBean();
        paramsBean.setUserName(userName);
        paramsBean.setPassword(EncryptUtils.enctyptLoginPass(password));

        getView().showLoadingDialog();
        getView().addNetWork(MWZLHttpDataRepository.getInstance().doLogin(paramsBean, new DataRequestCallback<LoginResponseBean>() {
            @Override
            public void onSuccessed(LoginResponseBean loginResponseBean) {
                String remoteAppToken = loginResponseBean.getApptoken();
                if (!Tools.isBlank(remoteAppToken)){
                    MyApplication.getInstance().setAppToken(remoteAppToken);
                    boolean isRemember = getView().getRememberCheckboxStatus();
                    if (isRemember){
                        SharedPrefUtils.put(getView().getRealContext(),
                                SplashPresenter.SP_KEY_USERNAME,
                                userName);
                        SharedPrefUtils.put(getView().getRealContext(),
                                SplashPresenter.SP_KEY_LOGINPASS,
                                password);
                        SharedPrefUtils.put(getView().getRealContext(),
                                SplashPresenter.SP_KEY_ISREMEMBER,
                                true);
                    }else {
                        SharedPrefUtils.put(getView().getRealContext(),
                                SplashPresenter.SP_KEY_USERNAME,
                                userName);
                        SharedPrefUtils.put(getView().getRealContext(),
                                SplashPresenter.SP_KEY_LOGINPASS,
                                "");
                        SharedPrefUtils.put(getView().getRealContext(),
                                SplashPresenter.SP_KEY_ISREMEMBER,
                                false);
                    }
                    getView().skipToHome();
                }else {
                    ToastUtils.showLongToast(getView().getRealContext(), "登录接口异常--登录凭证无效");
                }
                getView().dismissLoadingDialog();
            }

            @Override
            public void onFailed(String errorMsg) {
                ToastUtils.showLongToast(getView().getRealContext(), errorMsg);
                getView().dismissLoadingDialog();
            }
        }));
    }
}
