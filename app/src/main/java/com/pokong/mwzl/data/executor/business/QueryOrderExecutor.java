package com.pokong.mwzl.data.executor.business;

import com.pokong.mwzl.data.DataResponseBean;
import com.pokong.mwzl.data.bean.business.QueryOrderRequestBean;
import com.pokong.mwzl.data.bean.business.QueryOrderResponseBean;
import com.pokong.mwzl.data.BaseExecutor;
import com.pokong.mwzl.http.ApiService;

import io.reactivex.Observable;

/**
 * Created on 2018/11/7 17:21
 * User: Zheng
 * E-mail: zhengCH12138@163.com
 * 网络请求执行类--根据订单号查询订单详情
 */
public class QueryOrderExecutor extends BaseExecutor<QueryOrderRequestBean, QueryOrderResponseBean> {

    public QueryOrderExecutor(ApiService apiService, QueryOrderRequestBean paramsBean) {
        super(apiService, paramsBean);
    }

    @Override
    public Observable<DataResponseBean<QueryOrderResponseBean>> execute() {
        return null;
    }
}
