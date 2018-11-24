package com.pokong.mwzl.data.executor.mwzl;

import com.pokong.mwzl.data.BaseExecutor;
import com.pokong.mwzl.data.DataResponseEntity;
import com.pokong.mwzl.data.bean.mwzl.PickConfirmRequestBean;
import com.pokong.mwzl.data.bean.mwzl.PickConfirmResponseBean;
import com.pokong.mwzl.data.bean.personal.LoginRequestBean;
import com.pokong.mwzl.data.bean.personal.LoginResponseBean;
import com.pokong.mwzl.http.ApiService;

import java.util.HashMap;

import io.reactivex.Observable;

/**
 * Created by Zheng on 2018/10/19.
 * 网络请求执行类--确认收货
 */

public class PickConfirmExecutor extends BaseExecutor<PickConfirmRequestBean, PickConfirmResponseBean> {

    private final String PARAMKEY_USERNAME = "userName";
    private final String PARAMKEY_PASSWORD = "password";

    public PickConfirmExecutor(ApiService apiService, PickConfirmRequestBean paramsBean) {
        super(apiService, paramsBean);
    }

    @Override
    public Observable<DataResponseEntity<PickConfirmResponseBean>> execute() {
        return apiService.pickConfirm(new HashMap<>());
    }
}
