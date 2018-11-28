package com.pokong.mwzl.data.executor.business;

import com.pokong.library.util.Tools;
import com.pokong.mwzl.data.DataResponseBean;
import com.pokong.mwzl.data.bean.business.OrderDetailRequestBean;
import com.pokong.mwzl.data.bean.business.OrderDetailResponseBean;
import com.pokong.mwzl.data.BaseExecutor;
import com.pokong.mwzl.http.ApiService;
import com.pokong.mwzl.http.ErrorDataResponseFactory;

import io.reactivex.Observable;

/**
 * Created on 2018/11/7 16:55
 * User: Zheng
 * E-mail: zhengCH12138@163.com
 * 网络请求执行类--获取订单详情
 */
public class OrderDetailExecutor extends BaseExecutor<OrderDetailRequestBean, OrderDetailResponseBean> {

    public OrderDetailExecutor(ApiService apiService, OrderDetailRequestBean paramsBean) {
        super(apiService, paramsBean);
    }

    @Override
    public Observable<DataResponseBean<OrderDetailResponseBean>> execute() {
        String appToken = paramsBean.getAppToken();
        if (Tools.isBlank(appToken)){
            return Observable.create(emitter -> emitter.onNext(ErrorDataResponseFactory.create("appToken无效")));
        }

        long orderId = paramsBean.getOrderId();
        if (orderId == 0){
            return Observable.create(emitter -> emitter.onNext(ErrorDataResponseFactory.create("orderId无效")));
        }

        return apiService.getOrderDetail(appToken, String.valueOf(orderId));
    }
}
