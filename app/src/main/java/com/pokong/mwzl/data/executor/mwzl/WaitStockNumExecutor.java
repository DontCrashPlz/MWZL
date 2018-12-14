package com.pokong.mwzl.data.executor.mwzl;

import com.pokong.library.util.Tools;
import com.pokong.mwzl.data.BaseExecutor;
import com.pokong.mwzl.data.DataResponseBean;
import com.pokong.mwzl.data.bean.mwzl.LocationRequestBean;
import com.pokong.mwzl.data.bean.mwzl.WaitStockNumRequestBean;
import com.pokong.mwzl.http.ApiService;
import com.pokong.mwzl.http.ErrorDataResponseFactory;

import java.util.HashMap;

import io.reactivex.Observable;

/**
 * Created by Zheng on 2018/10/19.
 * 网络请求执行类--地址校对
 */

public class WaitStockNumExecutor extends BaseExecutor<WaitStockNumRequestBean, Long> {

    public WaitStockNumExecutor(ApiService apiService, WaitStockNumRequestBean paramsBean) {
        super(apiService, paramsBean);
    }

    @Override
    public Observable<DataResponseBean<Long>> execute() {

        String appToken = paramsBean.getAppToken();
        if (Tools.isBlank(appToken)){
            return Observable.create(emitter -> emitter.onNext(ErrorDataResponseFactory.create("appToken无效")));
        }

        long maxOrderId = paramsBean.getMaxOrderId();
        //if (maxOrderId == 0) return Observable.create(emitter -> emitter.onNext(ErrorDataResponseFactory.create("xLocation无效")));

        return apiService.getWaitStockNum(appToken, String.valueOf(maxOrderId));
    }
}
