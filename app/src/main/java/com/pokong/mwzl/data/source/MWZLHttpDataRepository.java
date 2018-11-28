package com.pokong.mwzl.data.source;

import com.pokong.mwzl.app.NetConstants;
import com.pokong.mwzl.data.DataExecutor;
import com.pokong.mwzl.data.DataRequestCallback;
import com.pokong.mwzl.data.DataResponseBean;
import com.pokong.mwzl.data.MultiPageListEntity;
import com.pokong.mwzl.data.bean.OrderListItemEntity;
import com.pokong.mwzl.data.bean.business.OrderDetailRequestBean;
import com.pokong.mwzl.data.bean.business.OrderDetailResponseBean;
import com.pokong.mwzl.data.bean.business.OrderListRequestBean;
import com.pokong.mwzl.data.bean.business.OrderReadyRequestBean;
import com.pokong.mwzl.data.bean.business.OrderReadyResponseBean;
import com.pokong.mwzl.data.bean.business.ShopInfoRequestBean;
import com.pokong.mwzl.data.bean.business.ShopInfoResponseBean;
import com.pokong.mwzl.data.bean.mwzl.LocationRequestBean;
import com.pokong.mwzl.data.bean.mwzl.LocationResponseBean;
import com.pokong.mwzl.data.bean.mwzl.PickConfirmRequestBean;
import com.pokong.mwzl.data.bean.mwzl.PickConfirmResponseBean;
import com.pokong.mwzl.data.bean.personal.LoginRequestBean;
import com.pokong.mwzl.data.bean.personal.LoginResponseBean;
import com.pokong.mwzl.data.executor.business.OrderDetailExecutor;
import com.pokong.mwzl.data.executor.business.OrderListExecutor;
import com.pokong.mwzl.data.executor.business.OrderReadyExecutor;
import com.pokong.mwzl.data.executor.business.ShopInfoExecutor;
import com.pokong.mwzl.data.executor.mwzl.LocationExecutor;
import com.pokong.mwzl.data.executor.mwzl.PickConfirmExecutor;
import com.pokong.mwzl.data.executor.personal.LoginExecutor;
import com.pokong.mwzl.http.ApiService;
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

public class MWZLHttpDataRepository implements MWZLDataSource {

    private static MWZLHttpDataRepository INSTANCE = null;

    private ApiService apiService;

    private Retrofit mRetrofit;

    private OkHttpClient mOkHttpClient;

    private MWZLHttpDataRepository() {
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

    public static MWZLHttpDataRepository getInstance(){
        if (INSTANCE == null) {
            synchronized (MWZLHttpDataRepository.class){
                if (INSTANCE == null){
                    INSTANCE = new MWZLHttpDataRepository();
                }
            }
        }
        return INSTANCE;
    }


    @Override
    public Disposable doLogin(LoginRequestBean paramsBean, DataRequestCallback<LoginResponseBean> callback) {
        DataExecutor<LoginResponseBean> executor = new LoginExecutor(apiService, paramsBean);
        Observable<DataResponseBean<LoginResponseBean>> observable = executor.execute();
        return observable.compose(ResponseTransformer.changeThread())
                .compose(ResponseTransformer.handleResult())
                .subscribe(loginResponseBeanDataResponseBean -> {
                    if (loginResponseBeanDataResponseBean.isSuccess()){
                        callback.onSuccessed(loginResponseBeanDataResponseBean.getData());
                    }else {
                        callback.onFailed(loginResponseBeanDataResponseBean.getDescription());
                    }
                },throwable -> callback.onFailed(throwable.getMessage()));
    }

    @Override
    public Disposable getShopInfo(ShopInfoRequestBean paramsBean, DataRequestCallback<ShopInfoResponseBean> callback) {
        DataExecutor<ShopInfoResponseBean> executor = new ShopInfoExecutor(apiService, paramsBean);
        Observable<DataResponseBean<ShopInfoResponseBean>> observable = executor.execute();
        return observable.compose(ResponseTransformer.changeThread())
                .compose(ResponseTransformer.handleResult())
                .subscribe(shopInfoResponseBeanDataResponseBean -> {
                    if (shopInfoResponseBeanDataResponseBean.isSuccess()){
                        callback.onSuccessed(shopInfoResponseBeanDataResponseBean.getData());
                    }else {
                        callback.onFailed(shopInfoResponseBeanDataResponseBean.getDescription());
                    }
                },throwable -> callback.onFailed(throwable.getMessage()));
    }

    @Override
    public Disposable uploadLocation(LocationRequestBean paramsBean, DataRequestCallback<String> callback) {
        DataExecutor<String> executor = new LocationExecutor(apiService, paramsBean);
        Observable<DataResponseBean<String>> observable = executor.execute();
        return observable.compose(ResponseTransformer.changeThread())
                .compose(ResponseTransformer.handleResult())
                .subscribe(locationResponseBeanDataResponseBean -> {
                    if (locationResponseBeanDataResponseBean.isSuccess()){
                        callback.onSuccessed("位置信息上传成功");
                    }else {
                        callback.onFailed(locationResponseBeanDataResponseBean.getDescription());
                    }
                },throwable -> callback.onFailed(throwable.getMessage()));
    }

    @Override
    public Disposable getOrderList(OrderListRequestBean paramsBean, DataRequestCallback<MultiPageListEntity<OrderListItemEntity>> callback) {
        DataExecutor<MultiPageListEntity<OrderListItemEntity>> executor = new OrderListExecutor(apiService, paramsBean);
        Observable<DataResponseBean<MultiPageListEntity<OrderListItemEntity>>> observable = executor.execute();
        return observable.compose(ResponseTransformer.changeThread())
                .compose(ResponseTransformer.handleResult())
                .subscribe(orderListResponseBeanDataResponseBean -> {
                    if (orderListResponseBeanDataResponseBean.isSuccess()){
                        callback.onSuccessed(orderListResponseBeanDataResponseBean.getData());
                    }else {
                        callback.onFailed(orderListResponseBeanDataResponseBean.getDescription());
                    }
                },throwable -> callback.onFailed(throwable.getMessage()));
    }

    @Override
    public Disposable getOrderDetail(OrderDetailRequestBean paramsBean, DataRequestCallback<OrderDetailResponseBean> callback) {
        DataExecutor<OrderDetailResponseBean> executor = new OrderDetailExecutor(apiService, paramsBean);
        Observable<DataResponseBean<OrderDetailResponseBean>> observable = executor.execute();
        return observable.compose(ResponseTransformer.changeThread())
                .compose(ResponseTransformer.handleResult())
                .subscribe(orderDetailResponseBeanDataResponseBean -> {
                    if (orderDetailResponseBeanDataResponseBean.isSuccess()){
                        callback.onSuccessed(orderDetailResponseBeanDataResponseBean.getData());
                    }else {
                        callback.onFailed(orderDetailResponseBeanDataResponseBean.getDescription());
                    }
                },throwable -> callback.onFailed(throwable.getMessage()));
    }

    @Override
    public Disposable orderReady(OrderReadyRequestBean paramsBean, DataRequestCallback<String> callback) {
        DataExecutor<String> executor = new OrderReadyExecutor(apiService, paramsBean);
        Observable<DataResponseBean<String>> observable = executor.execute();
        return observable.compose(ResponseTransformer.changeThread())
                .compose(ResponseTransformer.handleResult())
                .subscribe(orderReadyResponseBeanDataResponseBean -> {
                    if (orderReadyResponseBeanDataResponseBean.isSuccess()){
                        callback.onSuccessed("已确认备货");
                    }else {
                        callback.onFailed(orderReadyResponseBeanDataResponseBean.getDescription());
                    }
                },throwable -> callback.onFailed(throwable.getMessage()));
    }

    @Override
    public Disposable pickConfirm(PickConfirmRequestBean paramsBean, DataRequestCallback<String> callback) {
        DataExecutor<String> executor = new PickConfirmExecutor(apiService, paramsBean);
        Observable<DataResponseBean<String>> observable = executor.execute();
        return observable.compose(ResponseTransformer.changeThread())
                .compose(ResponseTransformer.handleResult())
                .subscribe(pickConfirmResponseBeanDataResponseBean -> {
                    if (pickConfirmResponseBeanDataResponseBean.isSuccess()){
                        callback.onSuccessed("已确认收货");
                    }else {
                        callback.onFailed(pickConfirmResponseBeanDataResponseBean.getDescription());
                    }
                },throwable -> callback.onFailed(throwable.getMessage()));
    }
}
