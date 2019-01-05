package com.pokong.mwzl.data.executor.personal;

import com.pokong.mwzl.data.BaseExecutor;
import com.pokong.mwzl.data.DataResponseBean;
import com.pokong.mwzl.data.bean.personal.UpdateRequestBean;
import com.pokong.mwzl.data.bean.personal.UpdateResponseBean;
import com.pokong.mwzl.http.ApiService;

import io.reactivex.Observable;

/**
 * Created by Zheng on 2018/10/19.
 * 网络请求执行类--登录
 */

public class UpdateExecutor extends BaseExecutor<UpdateRequestBean, UpdateResponseBean> {

    private final String PARAMKEY_APPTYPE = "appType";
    private final String PARAMKEY_USERTYPE = "useType";

    public UpdateExecutor(ApiService apiService, UpdateRequestBean paramsBean) {
        super(apiService, paramsBean);
    }

    @Override
    public Observable<DataResponseBean<UpdateResponseBean>> execute() {
        return apiService.getUpdateInfo(paramsBean.getAppType(), paramsBean.getUseType());
    }
}
