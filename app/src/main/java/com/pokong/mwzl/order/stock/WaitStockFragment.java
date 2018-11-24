package com.pokong.mwzl.order.stock;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.pokong.library.base.LazyLoadFragment;
import com.pokong.library.util.LogUtils;
import com.pokong.mwzl.R;
import com.pokong.mwzl.adapter.OrderListAdapter;
import com.pokong.mwzl.data.bean.OrderListItemEntity;

import java.util.ArrayList;

/**
 * Created on 2018/11/16 15:24
 * User: Zheng
 * E-mail: zhengCH12138@163.com
 */
public class WaitStockFragment extends LazyLoadFragment<WaitStockPresenter> implements SwipeRefreshLayout.OnRefreshListener, WaitStockContract.View, BaseQuickAdapter.RequestLoadMoreListener {

    private SwipeRefreshLayout refreshLayout;
    private RecyclerView recyclerView;
    private OrderListAdapter adapter;
    private ProgressBar progressBar;

    private ArrayList<OrderListItemEntity> orderList;

    public static WaitStockFragment newInstance(int tag){
        WaitStockFragment instance = new WaitStockFragment();
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
            OrderListItemEntity currentItemEntity = orderList.get(position);
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

        progressBar = rootView.findViewById(R.id.progressBar);
    }

    @Override
    protected void lazyLoad() {
        LogUtils.d("WaitStockFragment lazyLoad", "run");
        orderList = new ArrayList<>();
        orderList.add(new OrderListItemEntity());
        orderList.add(new OrderListItemEntity());
        orderList.add(new OrderListItemEntity());
        orderList.add(new OrderListItemEntity());
        adapter.addData(orderList);
        progressBar.setVisibility(View.GONE);
        refreshLayout.setEnabled(true);
    }

    @Override
    protected WaitStockPresenter getRealPresenter() {
        return new WaitStockPresenter();
    }

    @Override
    public void onRefresh() {
        adapter.setEnableLoadMore(false);
        new Thread(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            getActivity().runOnUiThread(() -> {
                refreshLayout.setRefreshing(false);
                adapter.setEnableLoadMore(true);
            });
        }).start();
    }

    @Override
    public void onLoadMoreRequested() {
        refreshLayout.setEnabled(false);
        new Thread(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            getActivity().runOnUiThread(() -> {
                adapter.loadMoreFail();
                refreshLayout.setEnabled(true);
            });
        }).start();
    }
}
