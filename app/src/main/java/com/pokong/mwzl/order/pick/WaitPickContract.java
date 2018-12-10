package com.pokong.mwzl.order.pick;

import com.pokong.mwzl.data.DataRequestCallback;
import com.pokong.mwzl.data.MultiPageListEntity;
import com.pokong.mwzl.data.bean.OrderListItemEntity;

import java.util.List;

/**
 * Created on 2018/11/16 15:43
 * User: Zheng
 * E-mail: zhengCH12138@163.com
 */
public interface WaitPickContract {
    interface View{

        /**
         * 给订单列表设置新数据(refresh successed)
         * @param newDataList
         * @param isLast
         */
        void setNewData(List<OrderListItemEntity> newDataList, boolean isLast);

        /**
         * 刷新失败(refresh failed)
         */
        void refreshFailed(String failMsg);

        /**
         * 向订单列表添加数据(loadMore successed)
         * @param moreDataList
         * @param isLast
         */
        void addMoreData(List<OrderListItemEntity> moreDataList, boolean isLast);

        /**
         * 加载更多失败(loadMore failed)
         */
        void loadMoreFailed(String failMsg);

        /**
         * 弹出确认已收货弹窗
         * @param orderId
         * @param orderSerial
         */
        void showConfirmDialog(long orderId, String orderSerial, int position);

    }

    interface Presenter{
        /**
         * 初始化参数Bean
         */
        void initParamsBean();

        /**
         * 刷新数据
         */
        void refreshData();

        /**
         * 加载更多数据
         */
        void loadMoreData();

        /**
         * 请求数据的实际方法
         */
        void requestData(DataRequestCallback<MultiPageListEntity<OrderListItemEntity>> callback);

        /**
         * 订单确认收货
         */
        void pickConfirm(long orderId, DataRequestCallback<String> callback);

    }
}
