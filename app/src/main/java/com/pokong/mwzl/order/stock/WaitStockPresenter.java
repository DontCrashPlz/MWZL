package com.pokong.mwzl.order.stock;

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
import com.pokong.mwzl.data.bean.mwzl.WaitStockNumRequestBean;
import com.pokong.mwzl.data.source.MWZLHttpDataRepository;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created on 2018/11/16 15:24
 * User: Zheng
 * E-mail: zhengCH12138@163.com
 */
public class WaitStockPresenter extends BasePresenter<WaitStockFragment> implements WaitStockContract.Presenter {

    private OrderListRequestBean paramsBean;//请求参数
    private WaitStockNumRequestBean  waitStockNumRequestBean;//待备货数量请求参数
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
                    if (orderListItemEntityMultiPageListEntity.isLast()){
                        getView().setNewData(dataList, true);
                    }else {
                        getView().setNewData(dataList, false);
                    }
                }else {
                    getView().setNewData(new ArrayList<>(), true);
                }
                getStockNum(new DataRequestCallback<Long>() {
                    @Override
                    public void onSuccessed(Long aLong) {
                        getView().refreshStockNum(aLong);
                    }

                    @Override
                    public void onFailed(String errorMsg) {
                        getView().refreshStockNum(0L);
                    }
                });
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
        requestData(new DataRequestCallback<MultiPageListEntity<OrderListItemEntity>>() {
            @Override
            public void onSuccessed(MultiPageListEntity<OrderListItemEntity> orderListItemEntityMultiPageListEntity) {
                ArrayList<OrderListItemEntity> dataList = orderListItemEntityMultiPageListEntity.getList();
                if (dataList != null && dataList.size() > 0){
                    lastOrderId = dataList.get(dataList.size() - 1).getId();
                    if (orderListItemEntityMultiPageListEntity.isLast()){
                        getView().addMoreData(dataList, true);
                    }else {
                        getView().addMoreData(dataList, false);
                    }
                }else {
                    getView().loadMoreFailed("暂时没有数据了");
                }
                getStockNum(new DataRequestCallback<Long>() {
                    @Override
                    public void onSuccessed(Long aLong) {
                        getView().refreshStockNum(aLong);
                    }

                    @Override
                    public void onFailed(String errorMsg) {
                        getView().refreshStockNum(0L);
                    }
                });
            }

            @Override
            public void onFailed(String errorMsg) {
                getView().loadMoreFailed(errorMsg);
            }
        });
    }

    @Override
    public void requestData(DataRequestCallback<MultiPageListEntity<OrderListItemEntity>> callback) {
        paramsBean.setStartId(lastOrderId);
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
    public void getStockNum(DataRequestCallback<Long> callback) {
        if (waitStockNumRequestBean == null)
            waitStockNumRequestBean = new WaitStockNumRequestBean();

        if (Tools.isBlank(waitStockNumRequestBean.getAppToken())){
            String appToken = MyApplication.getInstance().getAppToken();
            if (Tools.isBlank(appToken)) {
                ToastUtils.showShortToast(getView().getContext(), "待备货订单数量获取失败->登录令牌无效");
                return;
            }
            waitStockNumRequestBean.setAppToken(appToken);
        }

        waitStockNumRequestBean.setMaxOrderId(lastOrderId);

        Disposable disposable = getView().mStockNumDisposable;
        if (disposable != null && !disposable.isDisposed())
            disposable.dispose();
        getView().mStockNumDisposable = MWZLHttpDataRepository.getInstance().getWaitStockNum(waitStockNumRequestBean, callback);
    }


}
