package com.pokong.mwzl.data.executor.mwzl;

import com.pokong.library.util.Tools;
import com.pokong.mwzl.data.BaseExecutor;
import com.pokong.mwzl.data.DataResponseBean;
import com.pokong.mwzl.data.bean.mwzl.PickConfirmRequestBean;
import com.pokong.mwzl.data.bean.mwzl.PickConfirmResponseBean;
import com.pokong.mwzl.http.ApiService;
import com.pokong.mwzl.http.ErrorDataResponseFactory;

import java.util.HashMap;

import io.reactivex.Observable;

/**
 * Created by Zheng on 2018/10/19.
 * 网络请求执行类--确认收货
 */

public class PickConfirmExecutor extends BaseExecutor<PickConfirmRequestBean, String> {

    public PickConfirmExecutor(ApiService apiService, PickConfirmRequestBean paramsBean) {
        super(apiService, paramsBean);
    }

    @Override
    public Observable<DataResponseBean<String>> execute() {
        String appToken = paramsBean.getAppToken();
        if (Tools.isBlank(appToken)){
            return Observable.create(emitter -> emitter.onNext(ErrorDataResponseFactory.create("appToken无效")));
        }

        String orderId = paramsBean.getOrderId();
        if (Tools.isBlank(orderId)){
            return Observable.create(emitter -> emitter.onNext(ErrorDataResponseFactory.create("orderId无效")));
        }

        return apiService.pickConfirm(appToken, orderId);
    }
}
