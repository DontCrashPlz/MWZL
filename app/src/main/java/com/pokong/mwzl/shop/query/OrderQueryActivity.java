package com.pokong.mwzl.shop.query;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.pokong.library.base.BaseActivity;
import com.pokong.mwzl.data.bean.OrderListItemEntity;

import java.util.List;

/**
 * Created on 2018/12/10 18:27
 * User: Zheng
 * E-mail: zhengCH12138@163.com
 */
public class OrderQueryActivity extends BaseActivity<OrderQueryPresenter> implements OrderQueryContract.View, SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMoreRequested() {

    }

    @Override
    public void setNewData(List<OrderListItemEntity> newDataList, boolean isLast) {

    }

    @Override
    public void refreshFailed(String failMsg) {

    }

    @Override
    public void addMoreData(List<OrderListItemEntity> moreDataList, boolean isLast) {

    }

    @Override
    public void loadMoreFailed(String failMsg) {

    }

    @Override
    public void showPrintDialog(OrderListItemEntity currentItemEntity) {

    }

    @Override
    public void showStockReadyDialog(long orderId, String orderSerial, int position) {

    }

    @Override
    public void showConfirmDialog(long orderId, String orderSerial, int position) {

    }

    @Override
    public Context getRealContext() {
        return null;
    }

    @Override
    public int getContentViewRes() {
        return 0;
    }

    @Override
    public void initToolbar() {

    }

    @Override
    public void initUI() {

    }

    @Override
    protected OrderQueryPresenter getRealPresenter() {
        return null;
    }
}
