package com.pokong.mwzl.data.source;

import com.pokong.mwzl.data.DataRequestCallback;
import com.pokong.mwzl.data.MultiPageListEntity;
import com.pokong.mwzl.data.bean.OrderListItemEntity;
import com.pokong.mwzl.data.bean.business.OrderDetailRequestBean;
import com.pokong.mwzl.data.bean.business.OrderDetailResponseBean;
import com.pokong.mwzl.data.bean.business.OrderListRequestBean;
import com.pokong.mwzl.data.bean.business.OrderListResponseBean;
import com.pokong.mwzl.data.bean.business.OrderReadyRequestBean;
import com.pokong.mwzl.data.bean.business.OrderReadyResponseBean;
import com.pokong.mwzl.data.bean.business.ShopInfoRequestBean;
import com.pokong.mwzl.data.bean.business.ShopInfoResponseBean;
import com.pokong.mwzl.data.bean.mwzl.LocationRequestBean;
import com.pokong.mwzl.data.bean.mwzl.LocationResponseBean;
import com.pokong.mwzl.data.bean.mwzl.MemberInfoRequestBean;
import com.pokong.mwzl.data.bean.mwzl.MemberInfoResponseBean;
import com.pokong.mwzl.data.bean.mwzl.MemberPayRequestBean;
import com.pokong.mwzl.data.bean.mwzl.PickConfirmRequestBean;
import com.pokong.mwzl.data.bean.mwzl.PickConfirmResponseBean;
import com.pokong.mwzl.data.bean.mwzl.WaitStockNumRequestBean;
import com.pokong.mwzl.data.bean.personal.LoginRequestBean;
import com.pokong.mwzl.data.bean.personal.LoginResponseBean;
import com.pokong.mwzl.data.bean.personal.UpdateResponseBean;

import io.reactivex.disposables.Disposable;

/**
 * Created on 2018/11/23 13:49
 * User: Zheng
 * E-mail: zhengCH12138@163.com
 */
public interface MWZLDataSource {

    /**
     * 更新
     */
    Disposable getUpdateInfo(DataRequestCallback<UpdateResponseBean> callback);

    /**
     * 登录
     */
    Disposable doLogin(LoginRequestBean paramsBean,
                       DataRequestCallback<LoginResponseBean> callback);

    /**
     * 获取店铺信息
     */
    Disposable getShopInfo(ShopInfoRequestBean paramsBean,
                           DataRequestCallback<ShopInfoResponseBean> callback);

    /**
     * 获取店铺信息
     */
    Disposable uploadLocation(LocationRequestBean paramsBean,
                              DataRequestCallback<String> callback);

    /**
     * 获取订单列表
     */
    Disposable getOrderList(OrderListRequestBean paramsBean,
                            DataRequestCallback<MultiPageListEntity<OrderListItemEntity>> callback);

    /**
     * 获取未备货订单数量
     */
    Disposable getWaitStockNum(WaitStockNumRequestBean paramsBean,
                               DataRequestCallback<Long> callback);

    /**
     * 获取订单详情
     */
    Disposable getOrderDetail(OrderDetailRequestBean paramsBean,
                              DataRequestCallback<OrderDetailResponseBean> callback);

    /**
     * 订单备货完成
     */
    Disposable orderReady(OrderReadyRequestBean paramsBean,
                          DataRequestCallback<String> callback);

    /**
     * 订单已取货
     */
    Disposable pickConfirm(PickConfirmRequestBean paramsBean,
                           DataRequestCallback<String> callback);

    /**
     * 获取会员信息
     */
    Disposable getMemberInfo(MemberInfoRequestBean paramsBean,
                             DataRequestCallback<MemberInfoResponseBean> callback);

    /**
     * 会员余额支付
     */
    Disposable memberPay(MemberPayRequestBean paramsBean,
                         DataRequestCallback<String> callback);

}
