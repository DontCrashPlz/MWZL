package com.pokong.mwzl.order.complete;

import com.pokong.library.base.BasePresenter;
import com.pokong.mwzl.app.MyApplication;
import com.pokong.mwzl.app.OrderStatus;
import com.pokong.mwzl.data.DataRequestCallback;
import com.pokong.mwzl.data.MultiPageListEntity;
import com.pokong.mwzl.data.bean.OrderListItemEntity;
import com.pokong.mwzl.data.bean.business.OrderListRequestBean;
import com.pokong.mwzl.data.source.MWZLHttpDataRepository;

import java.util.ArrayList;

/**
 * Created on 2018/11/16 15:45
 * User: Zheng
 * E-mail: zhengCH12138@163.com
 */
public class CompletedPresenter extends BasePresenter<CompletedFragment> implements CompletedContract.Presenter {

    private OrderListRequestBean paramsBean;//请求参数
    private int currentPageNumber = 1;

    @Override
    public void initParamsBean() {
        paramsBean = new OrderListRequestBean();
        paramsBean.setAppToken(MyApplication.getInstance().getAppToken());
        paramsBean.setOrder_status(OrderStatus.COMPLETED);
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

}
