package com.pokong.mwzl.http;

import com.pokong.mwzl.app.NetConstants;
import com.pokong.mwzl.data.DataResponseBean;
import com.pokong.mwzl.data.MultiPageListEntity;
import com.pokong.mwzl.data.bean.OrderListItemEntity;
import com.pokong.mwzl.data.bean.business.OrderDetailResponseBean;
import com.pokong.mwzl.data.bean.business.ShopInfoResponseBean;
import com.pokong.mwzl.data.bean.mwzl.MemberInfoResponseBean;
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

    @GET(NetConstants.LOGIN)
    Observable<DataResponseBean<LoginResponseBean>> doLogin(@QueryMap Map<String, String> params);

    @GET(NetConstants.SHOP_INFO)
    Observable<DataResponseBean<ShopInfoResponseBean>> getShopInfo(@Query("appToken") String appToken);

    @GET(NetConstants.ORDER_LIST)
    Observable<DataResponseBean<MultiPageListEntity<OrderListItemEntity>>> getOrderList(@QueryMap Map<String, String> params);

    @GET(NetConstants.ORDER_WAIT_STOCK_NUM)
    Observable<DataResponseBean<Long>> getWaitStockNum(@Query("appToken") String appToken, @Query("maxOrderId") String maxOrderId);

    @GET(NetConstants.ORDER_DETAIL)
    Observable<DataResponseBean<OrderDetailResponseBean>> getOrderDetail(@Query("appToken") String appToken, @Query("orderId") String orderId);

    @GET(NetConstants.ORDER_READY)
    Observable<DataResponseBean<String>> orderReady(@Query("appToken") String appToken, @Query("orderId") String orderId);

    @GET(NetConstants.PICK_CONFIRM)
    Observable<DataResponseBean<String>> pickConfirm(@Query("appToken") String appToken, @Query("orderId") String orderId);

    @GET(NetConstants.UPLOAD_LOCATION)
    Observable<DataResponseBean<String>> uploadLocation(@QueryMap Map<String, String> params);

    @GET(NetConstants.SHOP_MEMBER_INFO)
    Observable<DataResponseBean<MemberInfoResponseBean>> getMemberInfo(@Query("appToken") String appToken, @Query("mobile") String mobile);

    @GET(NetConstants.SHOP_MEMBER_PAY)
    Observable<DataResponseBean<String>> memberPay(@QueryMap Map<String, String> params);

}
