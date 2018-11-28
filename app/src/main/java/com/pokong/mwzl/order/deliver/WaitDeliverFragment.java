package com.pokong.mwzl.order.deliver;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.pokong.library.base.LazyLoadFragment;
import com.pokong.library.util.LogUtils;
import com.pokong.library.util.ToastUtils;
import com.pokong.mwzl.R;
import com.pokong.mwzl.adapter.OrderListAdapter;
import com.pokong.mwzl.data.bean.OrderListItemEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2018/11/16 15:43
 * User: Zheng
 * E-mail: zhengCH12138@163.com
 */
public class WaitDeliverFragment extends LazyLoadFragment<WaitDeliverPresenter> implements WaitDeliverContract.View, BaseQuickAdapter.RequestLoadMoreListener, SwipeRefreshLayout.OnRefreshListener {

    private SwipeRefreshLayout refreshLayout;
    private RecyclerView recyclerView;
    private OrderListAdapter adapter;
    private ProgressBar progressBar;

    public static WaitDeliverFragment newInstance(int tag){
        WaitDeliverFragment instance = new WaitDeliverFragment();
        Bundle args = new Bundle();
        args.putInt("tag", tag);
        instance.setArguments(args);
        return instance;
    }

    @Override
    protected int getContentView() {
        return R.layout.fragment_order_list;
    }

    @Override
    protected void init(View rootView) {

        refreshLayout = rootView.findViewById(R.id.swiperefresh);
        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setRefreshing(false);
        refreshLayout.setEnabled(false);

        recyclerView = rootView.findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new OrderListAdapter(R.layout.layout_cardview_order_list);
        adapter.setOnItemClickListener((adapter, view, position) -> {
            OrderListItemEntity currentItemEntity = (OrderListItemEntity) adapter.getData().get(position);
            boolean isItemOpend = currentItemEntity.isOpend();
            if (isItemOpend){
                currentItemEntity.setOpend(false);
                adapter.setData(position, currentItemEntity);
            }else {
                currentItemEntity.setOpend(true);
                adapter.setData(position, currentItemEntity);
            }
        });
        recyclerView.setAdapter(adapter);
        adapter.setOnLoadMoreListener(this, recyclerView);
        adapter.setEnableLoadMore(false);

        progressBar = rootView.findViewById(R.id.progressBar);

        mPresenter.initParamsBean();
    }

    @Override
    protected void lazyLoad() {
        mPresenter.refreshData();
    }

    @Override
    protected WaitDeliverPresenter getRealPresenter() {
        return new WaitDeliverPresenter();
    }

    @Override
    public void onLoadMoreRequested() {
        refreshLayout.setEnabled(false);
        mPresenter.loadMoreData();
    }

    @Override
    public void onRefresh() {
        adapter.setEnableLoadMore(false);
        mPresenter.refreshData();
    }

    @Override
    public void setNewData(List<OrderListItemEntity> newDataList) {
        if (progressBar != null && progressBar.isShown()){
            progressBar.setVisibility(View.GONE);
            refreshLayout.setEnabled(true);
        }
        adapter.setNewData(newDataList);
        refreshLayout.setRefreshing(false);
        adapter.setEnableLoadMore(true);
    }

    @Override
    public void refreshFailed(String failMsg) {
        ToastUtils.showShortToast(getContext(), failMsg);
        refreshLayout.setRefreshing(false);
        adapter.setEnableLoadMore(true);
    }

    @Override
    public void addMoreData(List<OrderListItemEntity> moreDataList) {
        adapter.addData(moreDataList);
        adapter.loadMoreComplete();
        refreshLayout.setEnabled(true);
    }

    @Override
    public void loadMoreFailed(String failMsg) {
        ToastUtils.showShortToast(getContext(), failMsg);
        adapter.loadMoreFail();
        refreshLayout.setEnabled(true);
    }
}
