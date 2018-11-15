package com.pokong.mwzl.data.executor.personal;

import com.pokong.mwzl.data.DataResponseEntity;
import com.pokong.mwzl.data.bean.personal.LoginRequestBean;
import com.pokong.mwzl.data.bean.personal.LoginResponseBean;
import com.pokong.mwzl.data.BaseExecutor;
import com.pokong.mwzl.http.ApiService;

import java.util.HashMap;

import io.reactivex.Observable;

/**
 * Created by Zheng on 2018/10/19.
 * 网络请求执行类--登录
 */

public class LoginExecutor extends BaseExecutor<LoginRequestBean, LoginResponseBean> {

    private final String PARAMKEY_USERNAME = "userName";
    private final String PARAMKEY_PASSWORD = "password";

    public LoginExecutor(ApiService apiService, LoginRequestBean paramsBean) {
        super(apiService, paramsBean);
    }

    @Override
    public Observable<DataResponseEntity<LoginResponseBean>> execute() {
        return apiService.doLogin(new HashMap<>());
    }
}
