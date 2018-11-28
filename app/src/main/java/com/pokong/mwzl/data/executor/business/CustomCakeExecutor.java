package com.pokong.mwzl.data.executor.business;

import com.pokong.mwzl.data.DataResponseBean;
import com.pokong.mwzl.data.bean.business.CustomCakeRequestBean;
import com.pokong.mwzl.data.bean.business.CustomCakeResponseBean;
import com.pokong.mwzl.data.BaseExecutor;
import com.pokong.mwzl.http.ApiService;

import io.reactivex.Observable;

/**
 * Created on 2018/11/7 17:23
 * User: Zheng
 * E-mail: zhengCH12138@163.com
 * 网络请求执行类--自定义蛋糕
 */
public class CustomCakeExecutor extends BaseExecutor<CustomCakeRequestBean, CustomCakeResponseBean> {

    public CustomCakeExecutor(ApiService apiService, CustomCakeRequestBean paramsBean) {
        super(apiService, paramsBean);
    }

    @Override
    public Observable<DataResponseBean<CustomCakeResponseBean>> execute() {
        return null;
    }
}
