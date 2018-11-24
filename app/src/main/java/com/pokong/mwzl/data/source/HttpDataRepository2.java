package com.pokong.mwzl.data.source;

import com.pokong.mwzl.data.BaseExecutorFactory;
import com.pokong.mwzl.data.BaseRequestBean;
import com.pokong.mwzl.data.BaseResponseBean;
import com.pokong.mwzl.data.DataExecutor;
import com.pokong.mwzl.data.DataRequestCallback;
import com.pokong.mwzl.data.DataResponseEntity;
import com.pokong.mwzl.data.DataUtils;
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
    public <T extends BaseResponseBean> Disposable requestHttpData(BaseRequestBean paramsBean, DataRequestCallback<T> callback) {

        DataExecutor<BaseResponseBean> executor = BaseExecutorFactory.create(apiService, paramsBean);
        Observable<DataResponseEntity<BaseResponseBean>> observable = executor.execute();

        return observable.compose(ResponseTransformer.changeThread())
                .compose(ResponseTransformer.handleResult())
                .subscribe(baseResponseBeanDataResponseEntity -> {
                    if (DataUtils.isDataRequestSuccess(baseResponseBeanDataResponseEntity)){
                        BaseResponseBean responseBean = baseResponseBeanDataResponseEntity.getData();
                        callback.onSuccessed((T) responseBean);
                    }else {
                        callback.onFailed(baseResponseBeanDataResponseEntity.getDescription());
                    }
                },throwable -> callback.onFailed(throwable.getMessage()));

    }
}
