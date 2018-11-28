package com.pokong.mwzl.data.source;

import com.pokong.mwzl.data.BaseExecutorFactory;
import com.pokong.mwzl.data.DataExecutor;
import com.pokong.mwzl.data.DataRequestCallback;
import com.pokong.mwzl.data.DataResponseBean;
import com.pokong.mwzl.http.ApiService;
import com.pokong.mwzl.app.NetConstants;
import com.pokong.mwzl.http.ResponseTransformer;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Zheng on 2018/9/29.
 */

public class HttpDataRepository2 implements HttpDataSource2 {

    private static HttpDataRepository2 INSTANCE = null;

    private ApiService apiService;

    private Retrofit mRetrofit;

    private OkHttpClient mOkHttpClient;

    private HttpDataRepository2() {
        if (apiService == null) {
            if (mOkHttpClient == null){
                HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
                logging.setLevel(HttpLoggingInterceptor.Level.BODY);
                mOkHttpClient= new OkHttpClient.Builder()
//                        .cookieJar(new NovateCookieManger(MyApplication.getInstance()))
                        .connectTimeout(15, TimeUnit.SECONDS)
                        .writeTimeout(15, TimeUnit.SECONDS)
                        .readTimeout(15,TimeUnit.SECONDS)
                        .addInterceptor(logging)
                        .build();
            }
            if (mRetrofit == null){
                mRetrofit = new Retrofit.Builder()
                        .client(mOkHttpClient)
                        .baseUrl(NetConstants.BASEURL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .build();
            }
            apiService = mRetrofit.create(ApiService.class);
        }
    }

    public static HttpDataRepository2 getInstance(){
        if (INSTANCE == null) {
            synchronized (HttpDataRepository2.class){
                if (INSTANCE == null){
                    INSTANCE = new HttpDataRepository2();
                }
            }
        }
        return INSTANCE;
    }


    @Override
    public <T,R> Disposable requestHttpData(T paramsBean, DataRequestCallback<R> callback) {

        DataExecutor<R> executor = BaseExecutorFactory.create(apiService, paramsBean);
        Observable<DataResponseBean<R>> observable = executor.execute();

        return observable.compose(ResponseTransformer.changeThread())
                .compose(ResponseTransformer.handleResult())
                .subscribe(baseResponseBeanDataResponseBean -> {
                    if (baseResponseBeanDataResponseBean.isSuccess()){
                        callback.onSuccessed(baseResponseBeanDataResponseBean.getData());
                    }else {
                        callback.onFailed(baseResponseBeanDataResponseBean.getDescription());
                    }
                },throwable -> callback.onFailed(throwable.getMessage()));
    }
}
