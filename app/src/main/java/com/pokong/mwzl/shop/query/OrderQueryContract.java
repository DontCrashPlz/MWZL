package com.pokong.mwzl.shop.query;

import com.pokong.mwzl.data.DataRequestCallback;
import com.pokong.mwzl.data.MultiPageListEntity;
import com.pokong.mwzl.data.bean.OrderListItemEntity;

import java.util.List;

/**
 * Created on 2018/12/10 18:27
 * User: Zheng
 * E-mail: zhengCH12138@163.com
 */
public interface OrderQueryContract {
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
         * 弹出确认打印弹窗
         * @param currentItemEntity
         */
        void showPrintDialog(OrderListItemEntity currentItemEntity);

        /**
         * 弹出确认备货完成弹窗
         * @param orderId
         * @param orderSerial
         */
        void showStockReadyDialog(long orderId, String orderSerial, int position);

        /**
         * 弹出确认收货弹窗
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
         * 更换筛选条件参数 -> 订单状态
         * @param stateParams
         */
        void updateStateParams(int stateParams);

        /**
         * 更换筛选条件参数 -> 配送方式
         * @param typeParams
         */
        void updateTypeParams(String typeParams);

        /**
         * 更换筛选条件参数 -> 起始时间
         * @param beginTime
         */
        void updateBeginTimeParams(String beginTime);

        /**
         * 更换筛选条件参数 -> 结束时间
         * @param endTime
         */
        void updateEndTimeParams(String endTime);

        /**
         * 更换筛选条件参数 -> 订单编号
         * @param orderNo
         */
        void updateOrderNoParams(String orderNo);

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
         * 订单备货完成
         */
        void orderReady(long orderId, DataRequestCallback<String> callback);

        /**
         * 订单确认收货
         */
        void pickConfirm(long orderId, DataRequestCallback<String> callback);

    }
}
