package com.pokong.mwzl.data;

import com.pokong.mwzl.http.ApiService;

/**
 * Created on 2018/11/8 8:57
 * User: Zheng
 * E-mail: zhengCH12138@163.com
 * 定义网络执行类模版
 * 一个Executor用于请求一个数据接口，对于一个确定的接口，请求参数和响应数据格式是确定的
 * BaseExecutor没有实现DataExecutor接口的execute()方法，将执行数据请求的具体方法交由子类实现
 */
public abstract class BaseExecutor<R, T> implements DataExecutor<T> {

    protected ApiService apiService;

    protected R paramsBean;

    public BaseExecutor(ApiService apiService, R paramsBean) {
        this.apiService = apiService;
        this.paramsBean = paramsBean;
    }

}
