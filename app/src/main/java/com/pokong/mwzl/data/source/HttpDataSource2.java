package com.pokong.mwzl.data.source;

import com.pokong.mwzl.data.DataRequestCallback;

import io.reactivex.disposables.Disposable;

/**
 * Created by Zheng on 2018/10/17.
 */

public interface HttpDataSource2 {

    /**
     * Model层向外暴露的唯一方法
     * @param paramsBean 传入的参数实体，BaseExecutorFactory根据paramsBean的实际类型判断调用哪一个网络执行器
     * @param callback 传入的回调函数，必须和参数对应同一个网络请求
     * @param <T>
     * @return
     */
    <T,R> Disposable requestHttpData(T paramsBean, DataRequestCallback<R> callback);

}
