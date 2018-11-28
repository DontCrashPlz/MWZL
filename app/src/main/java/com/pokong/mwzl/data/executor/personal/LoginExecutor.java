package com.pokong.mwzl.data.executor.personal;

import com.pokong.library.util.Tools;
import com.pokong.mwzl.data.DataResponseBean;
import com.pokong.mwzl.data.bean.personal.LoginRequestBean;
import com.pokong.mwzl.data.bean.personal.LoginResponseBean;
import com.pokong.mwzl.data.BaseExecutor;
import com.pokong.mwzl.http.ApiService;
import com.pokong.mwzl.http.ErrorDataResponseFactory;

import java.util.HashMap;

import io.reactivex.Observable;

/**
 * Created by Zheng on 2018/10/19.
 * 网络请求执行类--登录
 */

public class LoginExecutor extends BaseExecutor<LoginRequestBean, LoginResponseBean> {

    private final String PARAMKEY_USERNAME = "userName";
    private final String PARAMKEY_PASSWORD = "password";
    private final String PARAMKEY_LOGINTYPE = "loginType";
    private final String PARAMKEY_SYSCODE = "sysCode";
    private final String PARAMKEY_TEMINALTYPE = "teminalType";

    private HashMap<String, String> paramsMap;

    public LoginExecutor(ApiService apiService, LoginRequestBean paramsBean) {
        super(apiService, paramsBean);
        paramsMap = new HashMap<>();
    }

    @Override
    public Observable<DataResponseBean<LoginResponseBean>> execute() {
        String userName = paramsBean.getUserName();
        if (Tools.isBlank(userName)){
            return Observable.create(emitter -> emitter.onNext(ErrorDataResponseFactory.create("用户名无效")));
        }
        paramsMap.put(PARAMKEY_USERNAME, userName);

        String password = paramsBean.getPassword();
        if (Tools.isBlank(password)){
            return Observable.create(emitter -> emitter.onNext(ErrorDataResponseFactory.create("密码无效")));
        }
        paramsMap.put(PARAMKEY_PASSWORD, password);

        paramsMap.put(PARAMKEY_LOGINTYPE, paramsBean.getLoginType());
        paramsMap.put(PARAMKEY_SYSCODE, paramsBean.getSysCode());
        paramsMap.put(PARAMKEY_TEMINALTYPE, paramsBean.getTeminalType());

        return apiService.doLogin(paramsMap);
    }
}
