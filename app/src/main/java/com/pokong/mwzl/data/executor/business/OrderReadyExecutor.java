package com.pokong.mwzl.data.executor.business;

import com.pokong.mwzl.data.DataResponseEntity;
import com.pokong.mwzl.data.bean.business.OrderReadyRequestBean;
import com.pokong.mwzl.data.bean.business.OrderReadyResponseBean;
import com.pokong.mwzl.data.BaseExecutor;
import com.pokong.mwzl.http.ApiService;

import io.reactivex.Observable;

/**
 * Created on 2018/11/7 17:20
 * User: Zheng
 * E-mail: zhengCH12138@163.com
 * 网络请求执行类--订单备货完成
 */
public class OrderReadyExecutor extends BaseExecutor<OrderReadyRequestBean, OrderReadyResponseBean> {

    public OrderReadyExecutor(ApiService apiService, OrderReadyRequestBean paramsBean) {
        super(apiService, paramsBean);
    }

    @Override
    public Observable<DataResponseEntity<OrderReadyResponseBean>> execute() {
        return null;
    }
}
