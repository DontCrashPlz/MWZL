package com.pokong.mwzl.data.source;

import com.pokong.mwzl.data.DataRequestCallback;
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

import io.reactivex.disposables.Disposable;

/**
 * 获取业务相关数据
 * Created by Zheng on 2018/9/26.
 */

public interface BusinessDataSource {

    /** 获取订单列表 */
    Disposable getOrderList(OrderListRequestBean paramsBean,
                            DataRequestCallback<OrderListResponseBean> callback);

    /** 获取订单详情 */
    Disposable getOrderDetail(OrderDetailRequestBean paramsBean,
                              DataRequestCallback<OrderDetailResponseBean> callback);

    /** 订单备货完成 */
    Disposable orderReady(OrderReadyRequestBean paramsBean,
                          DataRequestCallback<String> callback);

    /** 店铺数据统计信息 */
    Disposable getShopInfoList(ShopInfoRequestBean paramsBean,
                               DataRequestCallback<ShopInfoResponseBean> callback);

    /** 根据订单号查询订单 */
    Disposable queryOrderById(QueryOrderRequestBean paramsBean,
                              DataRequestCallback<QueryOrderResponseBean> callback);

    /** 获取评论商品列表 */
    Disposable getCommentGoodsList(CommentGoodsListRequestBean paramsBean,
                                   DataRequestCallback<CommentGoodsListResponseBean> callback);

    /** 获取指定商品评论列表 */
    Disposable getCommentListByGoodsId(GoodsCommentListRequestBean paramsBean,
                                       DataRequestCallback<GoodsCommentListResponseBean> callback);

    /** 定制蛋糕 */
    Disposable customCake(CustomCakeRequestBean paramsBean,
                          DataRequestCallback<CustomCakeResponseBean> callback);

}
