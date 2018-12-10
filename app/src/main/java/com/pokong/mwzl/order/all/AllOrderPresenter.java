package com.pokong.mwzl.order.all;

import com.pokong.library.base.BasePresenter;
import com.pokong.library.util.ToastUtils;
import com.pokong.library.util.Tools;
import com.pokong.mwzl.app.MyApplication;
import com.pokong.mwzl.app.OrderStatus;
import com.pokong.mwzl.data.DataRequestCallback;
import com.pokong.mwzl.data.MultiPageListEntity;
import com.pokong.mwzl.data.bean.OrderListItemEntity;
import com.pokong.mwzl.data.bean.business.OrderListRequestBean;
import com.pokong.mwzl.data.bean.business.OrderReadyRequestBean;
import com.pokong.mwzl.data.bean.mwzl.PickConfirmRequestBean;
import com.pokong.mwzl.data.source.MWZLHttpDataRepository;

import java.util.ArrayList;

/**
 * Created on 2018/11/16 15:47
 * User: Zheng
 * E-mail: zhengCH12138@163.com
 */
public class AllOrderPresenter extends BasePresenter<AllOrderFragment> implements AllOrderContract.Presenter {

    private OrderListRequestBean paramsBean;//请求参数
    private int currentPageNumber = 1;

    @Override
    public void initParamsBean() {
        paramsBean = new OrderListRequestBean();
        paramsBean.setAppToken(MyApplication.getInstance().getAppToken());
        paramsBean.setOrder_status(OrderStatus.ALL);
        paramsBean.setPageNumber(1);
        paramsBean.setOrderMode("desc");
    }

    @Override
    public void refreshData() {
        if (paramsBean == null) initParamsBean();
        currentPageNumber = 1;
        requestData(new DataRequestCallback<MultiPageListEntity<OrderListItemEntity>>() {
            @Override
            public void onSuccessed(MultiPageListEntity<OrderListItemEntity> orderListItemEntityMultiPageListEntity) {
                ArrayList<OrderListItemEntity> dataList = orderListItemEntityMultiPageListEntity.getList();
                if (dataList != null && dataList.size() > 0){
                    if (orderListItemEntityMultiPageListEntity.isLast()){
                        getView().setNewData(dataList, true);
                    }else {
                        getView().setNewData(dataList, false);
                    }
                }else {
                    getView().refreshFailed("暂时没有数据了");
                }
            }

            @Override
            public void onFailed(String errorMsg) {
                getView().refreshFailed(errorMsg);
            }
        });
    }

    @Override
    public void loadMoreData() {
        if (paramsBean == null) initParamsBean();
        currentPageNumber += 1;
        requestData(new DataRequestCallback<MultiPageListEntity<OrderListItemEntity>>() {
            @Override
            public void onSuccessed(MultiPageListEntity<OrderListItemEntity> orderListItemEntityMultiPageListEntity) {
                ArrayList<OrderListItemEntity> dataList = orderListItemEntityMultiPageListEntity.getList();
                if (dataList != null && dataList.size() > 0){
                    if (orderListItemEntityMultiPageListEntity.isLast()){
                        getView().addMoreData(dataList, true);
                    }else {
                        getView().addMoreData(dataList, false);
                    }
                }else {
                    getView().loadMoreFailed("暂时没有数据了");
                }
            }

            @Override
            public void onFailed(String errorMsg) {
                getView().loadMoreFailed(errorMsg);
            }
        });
    }

    @Override
    public void requestData(DataRequestCallback<MultiPageListEntity<OrderListItemEntity>> callback) {
        paramsBean.setPageNumber(currentPageNumber);
        getView().addNetWork(MWZLHttpDataRepository.getInstance().getOrderList(paramsBean,callback));
    }

    @Override
    public void orderReady(long orderId, DataRequestCallback<String> callback) {
        OrderReadyRequestBean requestBean = new OrderReadyRequestBean();
        String appToken = MyApplication.getInstance().getAppToken();
        if (Tools.isBlank(appToken)) {
            ToastUtils.showShortToast(getView().getContext(), "\"备货完成\"失败->登录令牌无效");
            return;
        }
        requestBean.setAppToken(appToken);
        if (orderId == 0){
            ToastUtils.showShortToast(getView().getContext(), "\"备货完成\"失败->订单id无效");
            return;
        }
        requestBean.setOrderId(String.valueOf(orderId));
        getView().addNetWork(MWZLHttpDataRepository.getInstance().orderReady(requestBean, callback));
    }

    @Override
    public void pickConfirm(long orderId, DataRequestCallback<String> callback) {
        PickConfirmRequestBean requestBean = new PickConfirmRequestBean();
        String appToken = MyApplication.getInstance().getAppToken();
        if (Tools.isBlank(appToken)) {
            ToastUtils.showShortToast(getView().getContext(), "\"确认收货\"失败->登录令牌无效");
            return;
        }
        requestBean.setAppToken(appToken);
        if (orderId == 0){
            ToastUtils.showShortToast(getView().getContext(), "\"确认收货\"失败->订单id无效");
            return;
        }
        requestBean.setOrderId(String.valueOf(orderId));
        getView().addNetWork(MWZLHttpDataRepository.getInstance().pickConfirm(requestBean, callback));
    }

}
