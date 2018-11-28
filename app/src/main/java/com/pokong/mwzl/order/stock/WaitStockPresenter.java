package com.pokong.mwzl.order.stock;

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
 * Created on 2018/11/16 15:24
 * User: Zheng
 * E-mail: zhengCH12138@163.com
 */
public class WaitStockPresenter extends BasePresenter<WaitStockFragment> implements WaitStockContract.Presenter {

    private OrderListRequestBean paramsBean;//请求参数
    private long lastOrderId;//最后一条数据的ID值

    @Override
    public void initParamsBean() {
        paramsBean = new OrderListRequestBean();
        paramsBean.setAppToken(MyApplication.getInstance().getAppToken());
        paramsBean.setOrder_status(OrderStatus.WAIT_STOCK);
    }

    @Override
    public void refreshData() {
        if (paramsBean == null) initParamsBean();
        lastOrderId = 0;
        requestData(new DataRequestCallback<MultiPageListEntity<OrderListItemEntity>>() {
            @Override
            public void onSuccessed(MultiPageListEntity<OrderListItemEntity> orderListItemEntityMultiPageListEntity) {
                ArrayList<OrderListItemEntity> dataList = orderListItemEntityMultiPageListEntity.getList();
                if (dataList != null && dataList.size() > 0){
                    lastOrderId = dataList.get(dataList.size() - 1).getId();
                    getView().setNewData(dataList);
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
        paramsBean.setStartId(lastOrderId);
        requestData(new DataRequestCallback<MultiPageListEntity<OrderListItemEntity>>() {
            @Override
            public void onSuccessed(MultiPageListEntity<OrderListItemEntity> orderListItemEntityMultiPageListEntity) {
                ArrayList<OrderListItemEntity> dataList = orderListItemEntityMultiPageListEntity.getList();
                if (dataList != null && dataList.size() > 0){
                    lastOrderId = dataList.get(dataList.size() - 1).getId();
                    getView().addMoreData(dataList);
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
        getView().addNetWork(MWZLHttpDataRepository.getInstance().getOrderList(paramsBean,callback));
    }

}
