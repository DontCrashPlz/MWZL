package com.pokong.mwzl.data.source;

import com.pokong.mwzl.app.NetConstants;
import com.pokong.mwzl.data.DataExecutor;
import com.pokong.mwzl.data.DataRequestCallback;
import com.pokong.mwzl.data.DataResponseEntity;
import com.pokong.mwzl.data.DataUtils;
import com.pokong.mwzl.data.bean.business.CommentGoodsListRequestBean;
import com.pokong.mwzl.data.bean.business.CommentGoodsListResponseBean;
import com.pokong.mwzl.data.bean.business.CustomCakeRequestBean;
import com.pokong.mwzl.data.bean.business.CustomCakeResponseBean;
import com.pokong.mwzl.data.bean.business.GoodsCommentListRequestBean;
import com.pokong.mwzl.data.bean.business.GoodsCommentListResponseBean;
import com.pokong.mwzl.data.bean.business.OrderDetailRequestBean;
import com.pokong.mwzl.data.bean.business.OrderDetailResponseBean;
import com.pokong.mwzl.data.bean.business.OrderListRequestBean;
import com.pokong.mwzl.data.bean.business.OrderListResponseBean;
import com.pokong.mwzl.data.bean.business.OrderReadyRequestBean;
import com.pokong.mwzl.data.bean.business.OrderReadyResponseBean;
import com.pokong.mwzl.data.bean.business.QueryOrderRequestBean;
import com.pokong.mwzl.data.bean.business.QueryOrderResponseBean;
import com.pokong.mwzl.data.bean.business.ShopInfoRequestBean;
import com.pokong.mwzl.data.bean.business.ShopInfoResponseBean;
import com.pokong.mwzl.data.bean.mwzl.LocationRequestBean;
import com.pokong.mwzl.data.bean.mwzl.LocationResponseBean;
import com.pokong.mwzl.data.bean.mwzl.PickConfirmRequestBean;
import com.pokong.mwzl.data.bean.mwzl.PickConfirmResponseBean;
import com.pokong.mwzl.data.bean.personal.LoginRequestBean;
import com.pokong.mwzl.data.bean.personal.LoginResponseBean;
import com.pokong.mwzl.data.bean.personal.NoticeDetailRequestBean;
import com.pokong.mwzl.data.bean.personal.NoticeDetailResponseBean;
import com.pokong.mwzl.data.bean.personal.NoticeListRequestBean;
import com.pokong.mwzl.data.bean.personal.NoticeListResponseBean;
import com.pokong.mwzl.data.executor.business.CommentGoodsListExecutor;
import com.pokong.mwzl.data.executor.business.CustomCakeExecutor;
import com.pokong.mwzl.data.executor.business.GoodsCommentListExecutor;
import com.pokong.mwzl.data.executor.business.OrderDetailExecutor;
import com.pokong.mwzl.data.executor.business.OrderListExecutor;
import com.pokong.mwzl.data.executor.business.OrderReadyExecutor;
import com.pokong.mwzl.data.executor.business.QueryOrderExecutor;
import com.pokong.mwzl.data.executor.business.ShopInfoExecutor;
import com.pokong.mwzl.data.executor.mwzl.LocationExecutor;
import com.pokong.mwzl.data.executor.mwzl.PickConfirmExecutor;
import com.pokong.mwzl.data.executor.personal.LoginExecutor;
import com.pokong.mwzl.data.executor.personal.NoticeDetailExecutor;
import com.pokong.mwzl.data.executor.personal.NoticeListExecutor;
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
        Observable<DataResponseEntity<LoginResponseBean>> observable = executor.execute();
        return observable.compose(ResponseTransformer.changeThread())
                .compose(ResponseTransformer.handleResult())
                .subscribe(loginResponseBeanDataResponseEntity -> {
                    if (DataUtils.isDataRequestSuccess(loginResponseBeanDataResponseEntity)){
                        callback.onSuccessed(loginResponseBeanDataResponseEntity.getData());
                    }else {
                        callback.onFailed(loginResponseBeanDataResponseEntity.getDescription());
                    }
                },throwable -> callback.onFailed(throwable.getMessage()));
    }

    @Override
    public Disposable getShopInfo(ShopInfoRequestBean paramsBean, DataRequestCallback<ShopInfoResponseBean> callback) {
        DataExecutor<ShopInfoResponseBean> executor = new ShopInfoExecutor(apiService, paramsBean);
        Observable<DataResponseEntity<ShopInfoResponseBean>> observable = executor.execute();
        return observable.compose(ResponseTransformer.changeThread())
                .compose(ResponseTransformer.handleResult())
                .subscribe(shopInfoResponseBeanDataResponseEntity -> {
                    if (DataUtils.isDataRequestSuccess(shopInfoResponseBeanDataResponseEntity)){
                        callback.onSuccessed(shopInfoResponseBeanDataResponseEntity.getData());
                    }else {
                        callback.onFailed(shopInfoResponseBeanDataResponseEntity.getDescription());
                    }
                },throwable -> callback.onFailed(throwable.getMessage()));
    }

    @Override
    public Disposable uploadLocation(LocationRequestBean paramsBean, DataRequestCallback<LocationResponseBean> callback) {
        DataExecutor<LocationResponseBean> executor = new LocationExecutor(apiService, paramsBean);
        Observable<DataResponseEntity<LocationResponseBean>> observable = executor.execute();
        return observable.compose(ResponseTransformer.changeThread())
                .compose(ResponseTransformer.handleResult())
                .subscribe(locationResponseBeanDataResponseEntity -> {
                    if (DataUtils.isDataRequestSuccess(locationResponseBeanDataResponseEntity)){
                        callback.onSuccessed(locationResponseBeanDataResponseEntity.getData());
                    }else {
                        callback.onFailed(locationResponseBeanDataResponseEntity.getDescription());
                    }
                },throwable -> callback.onFailed(throwable.getMessage()));
    }

    @Override
    public Disposable getOrderList(OrderListRequestBean paramsBean, DataRequestCallback<OrderListResponseBean> callback) {
        DataExecutor<OrderListResponseBean> executor = new OrderListExecutor(apiService, paramsBean);
        Observable<DataResponseEntity<OrderListResponseBean>> observable = executor.execute();
        return observable.compose(ResponseTransformer.changeThread())
                .compose(ResponseTransformer.handleResult())
                .subscribe(orderListResponseBeanDataResponseEntity -> {
                    if (DataUtils.isDataRequestSuccess(orderListResponseBeanDataResponseEntity)){
                        callback.onSuccessed(orderListResponseBeanDataResponseEntity.getData());
                    }else {
                        callback.onFailed(orderListResponseBeanDataResponseEntity.getDescription());
                    }
                },throwable -> callback.onFailed(throwable.getMessage()));
    }

    @Override
    public Disposable getOrderDetail(OrderDetailRequestBean paramsBean, DataRequestCallback<OrderDetailResponseBean> callback) {
        DataExecutor<OrderDetailResponseBean> executor = new OrderDetailExecutor(apiService, paramsBean);
        Observable<DataResponseEntity<OrderDetailResponseBean>> observable = executor.execute();
        return observable.compose(ResponseTransformer.changeThread())
                .compose(ResponseTransformer.handleResult())
                .subscribe(orderDetailResponseBeanDataResponseEntity -> {
                    if (DataUtils.isDataRequestSuccess(orderDetailResponseBeanDataResponseEntity)){
                        callback.onSuccessed(orderDetailResponseBeanDataResponseEntity.getData());
                    }else {
                        callback.onFailed(orderDetailResponseBeanDataResponseEntity.getDescription());
                    }
                },throwable -> callback.onFailed(throwable.getMessage()));
    }

    @Override
    public Disposable orderReady(OrderReadyRequestBean paramsBean, DataRequestCallback<OrderReadyResponseBean> callback) {
        DataExecutor<OrderReadyResponseBean> executor = new OrderReadyExecutor(apiService, paramsBean);
        Observable<DataResponseEntity<OrderReadyResponseBean>> observable = executor.execute();
        return observable.compose(ResponseTransformer.changeThread())
                .compose(ResponseTransformer.handleResult())
                .subscribe(orderReadyResponseBeanDataResponseEntity -> {
                    if (DataUtils.isDataRequestSuccess(orderReadyResponseBeanDataResponseEntity)){
                        callback.onSuccessed(orderReadyResponseBeanDataResponseEntity.getData());
                    }else {
                        callback.onFailed(orderReadyResponseBeanDataResponseEntity.getDescription());
                    }
                },throwable -> callback.onFailed(throwable.getMessage()));
    }

    @Override
    public Disposable pickConfirm(PickConfirmRequestBean paramsBean, DataRequestCallback<PickConfirmResponseBean> callback) {
        DataExecutor<PickConfirmResponseBean> executor = new PickConfirmExecutor(apiService, paramsBean);
        Observable<DataResponseEntity<PickConfirmResponseBean>> observable = executor.execute();
        return observable.compose(ResponseTransformer.changeThread())
                .compose(ResponseTransformer.handleResult())
                .subscribe(pickConfirmResponseBeanDataResponseEntity -> {
                    if (DataUtils.isDataRequestSuccess(pickConfirmResponseBeanDataResponseEntity)){
                        callback.onSuccessed(pickConfirmResponseBeanDataResponseEntity.getData());
                    }else {
                        callback.onFailed(pickConfirmResponseBeanDataResponseEntity.getDescription());
                    }
                },throwable -> callback.onFailed(throwable.getMessage()));
    }
}
