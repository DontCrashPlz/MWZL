package com.pokong.mwzl.http;

import com.pokong.mwzl.data.DataResponseBean;

import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Zheng on 2018/5/17.
 * Update by Zheng on 2018/10/9.
 */

public class ResponseTransformer {

    /**
     * 切换网络请求线程
     * @param <T>
     * @return
     */
    public static <T> ObservableTransformer<DataResponseBean<T>, DataResponseBean<T>> changeThread(){
        return upstream -> upstream.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * ObservableTransformer<upstream,downstream>
     * 用于将上游的流upstream转换为下游的流downstream
     * 处理网络请求错误，当网络请求发生异常时，返回一个特殊数据并中止网络请求
     */
    public static <T> ObservableTransformer<DataResponseBean<T>, DataResponseBean<T>> handleResult() {
        return upstream -> upstream.onErrorReturn(new ErrorReturnFunction<T>());
    }

    /**
     * 网络请求错误时，返回一个特殊的DataResponseEntity
     * 这个DataResponseEntity中携带有关于网络请求错误的详细信息
     * @param <T>
     */
    private static class ErrorReturnFunction <T> implements Function<Throwable, DataResponseBean<T>> {
        @Override
        public DataResponseBean<T> apply(Throwable throwable) throws Exception {
            return CustomExceptionConverter.convertException(throwable);
        }
    }

}
