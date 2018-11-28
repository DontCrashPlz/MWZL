package com.pokong.mwzl.data.executor.business;

import com.pokong.library.util.Tools;
import com.pokong.mwzl.data.DataResponseBean;
import com.pokong.mwzl.data.bean.business.OrderReadyRequestBean;
import com.pokong.mwzl.data.bean.business.OrderReadyResponseBean;
import com.pokong.mwzl.data.BaseExecutor;
import com.pokong.mwzl.http.ApiService;
import com.pokong.mwzl.http.ErrorDataResponseFactory;

import io.reactivex.Observable;

/**
 * Created on 2018/11/7 17:20
 * User: Zheng
 * E-mail: zhengCH12138@163.com
 * 网络请求执行类--订单备货完成
 */
public class OrderReadyExecutor extends BaseExecutor<OrderReadyRequestBean, String> {

    public OrderReadyExecutor(ApiService apiService, OrderReadyRequestBean paramsBean) {
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

        return apiService.orderReady(appToken, orderId);
    }
}
