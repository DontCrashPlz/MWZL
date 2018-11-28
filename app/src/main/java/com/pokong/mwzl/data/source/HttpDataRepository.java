package com.pokong.mwzl.data.source;

import com.pokong.mwzl.data.DataExecutor;
import com.pokong.mwzl.data.DataRequestCallback;
import com.pokong.mwzl.data.DataResponseBean;
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
import com.pokong.mwzl.data.executor.business.OrderReadyExecutor;
import com.pokong.mwzl.data.executor.business.QueryOrderExecutor;
import com.pokong.mwzl.data.executor.business.ShopInfoExecutor;
import com.pokong.mwzl.data.executor.personal.LoginExecutor;
import com.pokong.mwzl.data.executor.personal.NoticeDetailExecutor;
import com.pokong.mwzl.data.executor.personal.NoticeListExecutor;
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

public class HttpDataRepository implements HttpDataSource {

    private static HttpDataRepository INSTANCE = null;

    private ApiService apiService;

    private Retrofit mRetrofit;

    private OkHttpClient mOkHttpClient;

    private HttpDataRepository() {
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

    public static HttpDataRepository getInstance(){
        if (INSTANCE == null) {
            synchronized (HttpDataRepository.class){
                if (INSTANCE == null){
                    INSTANCE = new HttpDataRepository();
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
    public Disposable getSystemNoticeList(NoticeListRequestBean paramsBean, DataRequestCallback<NoticeListResponseBean> callback) {
        DataExecutor<NoticeListResponseBean> executor = new NoticeListExecutor(apiService, paramsBean);
        Observable<DataResponseBean<NoticeListResponseBean>> observable = executor.execute();
        return observable.compose(ResponseTransformer.changeThread())
                .compose(ResponseTransformer.handleResult())
                .subscribe(noticeListResponseBeanDataResponseBean -> {
                    if (noticeListResponseBeanDataResponseBean.isSuccess()){
                        callback.onSuccessed(noticeListResponseBeanDataResponseBean.getData());
                    }else {
                        callback.onFailed(noticeListResponseBeanDataResponseBean.getDescription());
                    }
                },throwable -> callback.onFailed(throwable.getMessage()));
    }

    @Override
    public Disposable getSystemNoticeDetail(NoticeDetailRequestBean paramsBean, DataRequestCallback<NoticeDetailResponseBean> callback) {
        DataExecutor<NoticeDetailResponseBean> executor = new NoticeDetailExecutor(apiService, paramsBean);
        Observable<DataResponseBean<NoticeDetailResponseBean>> observable = executor.execute();
        return observable.compose(ResponseTransformer.changeThread())
                .compose(ResponseTransformer.handleResult())
                .subscribe(noticeDetailResponseBeanDataResponseBean -> {
                    if (noticeDetailResponseBeanDataResponseBean.isSuccess()){
                        callback.onSuccessed(noticeDetailResponseBeanDataResponseBean.getData());
                    }else {
                        callback.onFailed(noticeDetailResponseBeanDataResponseBean.getDescription());
                    }
                },throwable -> callback.onFailed(throwable.getMessage()));
    }

    @Override
    public Disposable getOrderList(OrderListRequestBean paramsBean, DataRequestCallback<OrderListResponseBean> callback) {
//        DataExecutor<OrderListResponseBean> executor = new OrderListExecutor(apiService, paramsBean);
//        Observable<DataResponseBean<OrderListResponseBean>> observable = executor.execute();
//        return observable.compose(ResponseTransformer.changeThread())
//                .compose(ResponseTransformer.handleResult())
//                .subscribe(orderListResponseBeanDataResponseBean -> {
//                    if (orderListResponseBeanDataResponseBean.isSuccess()){
//                        callback.onSuccessed(orderListResponseBeanDataResponseBean.getData());
//                    }else {
//                        callback.onFailed(orderListResponseBeanDataResponseBean.getDescription());
//                    }
//                },throwable -> callback.onFailed(throwable.getMessage()));
        return null;
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
                        callback.onSuccessed(orderReadyResponseBeanDataResponseBean.getData());
                    }else {
                        callback.onFailed(orderReadyResponseBeanDataResponseBean.getDescription());
                    }
                },throwable -> callback.onFailed(throwable.getMessage()));
    }

    @Override
    public Disposable getShopInfoList(ShopInfoRequestBean paramsBean, DataRequestCallback<ShopInfoResponseBean> callback) {
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
    public Disposable queryOrderById(QueryOrderRequestBean paramsBean, DataRequestCallback<QueryOrderResponseBean> callback) {
        DataExecutor<QueryOrderResponseBean> executor = new QueryOrderExecutor(apiService, paramsBean);
        Observable<DataResponseBean<QueryOrderResponseBean>> observable = executor.execute();
        return observable.compose(ResponseTransformer.changeThread())
                .compose(ResponseTransformer.handleResult())
                .subscribe(queryOrderResponseBeanDataResponseBean -> {
                    if (queryOrderResponseBeanDataResponseBean.isSuccess()){
                        callback.onSuccessed(queryOrderResponseBeanDataResponseBean.getData());
                    }else {
                        callback.onFailed(queryOrderResponseBeanDataResponseBean.getDescription());
                    }
                },throwable -> callback.onFailed(throwable.getMessage()));
    }

    @Override
    public Disposable getCommentGoodsList(CommentGoodsListRequestBean paramsBean, DataRequestCallback<CommentGoodsListResponseBean> callback) {
        DataExecutor<CommentGoodsListResponseBean> executor = new CommentGoodsListExecutor(apiService, paramsBean);
        Observable<DataResponseBean<CommentGoodsListResponseBean>> observable = executor.execute();
        return observable.compose(ResponseTransformer.changeThread())
                .compose(ResponseTransformer.handleResult())
                .subscribe(commentGoodsListResponseBeanDataResponseBean -> {
                    if (commentGoodsListResponseBeanDataResponseBean.isSuccess()){
                        callback.onSuccessed(commentGoodsListResponseBeanDataResponseBean.getData());
                    }else {
                        callback.onFailed(commentGoodsListResponseBeanDataResponseBean.getDescription());
                    }
                },throwable -> callback.onFailed(throwable.getMessage()));
    }

    @Override
    public Disposable getCommentListByGoodsId(GoodsCommentListRequestBean paramsBean, DataRequestCallback<GoodsCommentListResponseBean> callback) {
        DataExecutor<GoodsCommentListResponseBean> executor = new GoodsCommentListExecutor(apiService, paramsBean);
        Observable<DataResponseBean<GoodsCommentListResponseBean>> observable = executor.execute();
        return observable.compose(ResponseTransformer.changeThread())
                .compose(ResponseTransformer.handleResult())
                .subscribe(goodsCommentListResponseBeanDataResponseBean -> {
                    if (goodsCommentListResponseBeanDataResponseBean.isSuccess()){
                        callback.onSuccessed(goodsCommentListResponseBeanDataResponseBean.getData());
                    }else {
                        callback.onFailed(goodsCommentListResponseBeanDataResponseBean.getDescription());
                    }
                },throwable -> callback.onFailed(throwable.getMessage()));
    }

    @Override
    public Disposable customCake(CustomCakeRequestBean paramsBean, DataRequestCallback<CustomCakeResponseBean> callback) {
        DataExecutor<CustomCakeResponseBean> executor = new CustomCakeExecutor(apiService, paramsBean);
        Observable<DataResponseBean<CustomCakeResponseBean>> observable = executor.execute();
        return observable.compose(ResponseTransformer.changeThread())
                .compose(ResponseTransformer.handleResult())
                .subscribe(customCakeResponseBeanDataResponseBean -> {
                    if (customCakeResponseBeanDataResponseBean.isSuccess()){
                        callback.onSuccessed(customCakeResponseBeanDataResponseBean.getData());
                    }else {
                        callback.onFailed(customCakeResponseBeanDataResponseBean.getDescription());
                    }
                },throwable -> callback.onFailed(throwable.getMessage()));
    }
}
