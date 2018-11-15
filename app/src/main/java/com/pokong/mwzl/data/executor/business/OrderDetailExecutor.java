package com.pokong.mwzl.data.executor.business;

import com.pokong.mwzl.data.DataResponseEntity;
import com.pokong.mwzl.data.bean.business.OrderDetailRequestBean;
import com.pokong.mwzl.data.bean.business.OrderDetailResponseBean;
import com.pokong.mwzl.data.BaseExecutor;
import com.pokong.mwzl.http.ApiService;

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
    public Observable<DataResponseEntity<OrderDetailResponseBean>> execute() {
        return null;
    }
}
