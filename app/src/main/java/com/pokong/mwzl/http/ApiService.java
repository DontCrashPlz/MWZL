package com.pokong.mwzl.http;

import com.pokong.mwzl.data.DataResponseEntity;
import com.pokong.mwzl.data.MultiPageListEntity;
import com.pokong.mwzl.data.bean.OrderListItemEntity;
import com.pokong.mwzl.data.bean.business.OrderDetailResponseBean;
import com.pokong.mwzl.data.bean.business.OrderListResponseBean;
import com.pokong.mwzl.data.bean.business.OrderReadyResponseBean;
import com.pokong.mwzl.data.bean.business.ShopInfoResponseBean;
import com.pokong.mwzl.data.bean.mwzl.LocationResponseBean;
import com.pokong.mwzl.data.bean.mwzl.PickConfirmResponseBean;
import com.pokong.mwzl.data.bean.personal.LoginResponseBean;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by Zheng on 2018/4/22.
 */

public interface ApiService {

    @GET("/api/store/login")
    Observable<DataResponseEntity<LoginResponseBean>> doLogin(@QueryMap Map<String, String> params);

    @GET("/api/store/info")
    Observable<DataResponseEntity<ShopInfoResponseBean>> getShopInfo(@Query("appToken") String appToken);

    @GET("/api/store/order/query")
    Observable<DataResponseEntity<MultiPageListEntity<OrderListItemEntity>>> getOrderList(@QueryMap Map<String, String> params);

    @GET("/api/store/order/detail")
    Observable<DataResponseEntity<OrderDetailResponseBean>> getOrderDetail(@Query("appToken") String appToken, @Query("orderId") String orderId);

    @GET("/api/store/order/stockUp")
    Observable<DataResponseEntity<OrderReadyResponseBean>> orderReady(@QueryMap Map<String, String> params);

    @GET("/api/store/order/pickUp")
    Observable<DataResponseEntity<PickConfirmResponseBean>> pickConfirm(@QueryMap Map<String, String> params);

    @GET("/api/storesetLocaltion")
    Observable<DataResponseEntity<LocationResponseBean>> uploadLocation(@QueryMap Map<String, String> params);

}
