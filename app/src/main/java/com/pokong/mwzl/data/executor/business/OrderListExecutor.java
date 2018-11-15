package com.pokong.mwzl.data.executor.business;

import com.pokong.mwzl.data.DataResponseEntity;
import com.pokong.mwzl.data.bean.business.OrderListRequestBean;
import com.pokong.mwzl.data.bean.business.OrderListResponseBean;
import com.pokong.mwzl.data.BaseExecutor;
import com.pokong.mwzl.http.ApiService;

import io.reactivex.Observable;

/**
 * Created on 2018/11/7 16:38
 * User: Zheng
 * E-mail: zhengCH12138@163.com
 * 网络请求执行类--获取订单列表
 */
public class OrderListExecutor extends BaseExecutor<OrderListRequestBean, OrderListResponseBean> {

    public OrderListExecutor(ApiService apiService, OrderListRequestBean paramsBean) {
        super(apiService, paramsBean);
    }

    @Override
    public Observable<DataResponseEntity<OrderListResponseBean>> execute() {
        return null;
    }
}
