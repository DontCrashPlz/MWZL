package com.pokong.mwzl.order.comment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.pokong.library.base.LazyLoadFragment;
import com.pokong.library.util.ToastUtils;
import com.pokong.mwzl.R;
import com.pokong.mwzl.adapter.OrderListAdapter;
import com.pokong.mwzl.data.bean.OrderListItemEntity;

import java.util.List;

/**
 * Created on 2018/11/16 15:45
 * User: Zheng
 * E-mail: zhengCH12138@163.com
 */
public class WaitCommentFragment extends LazyLoadFragment<WaitCommentPresenter> implements WaitCommentContract.View, SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {

    private SwipeRefreshLayout refreshLayout;
    private RecyclerView recyclerView;
    private OrderListAdapter adapter;
    private ProgressBar progressBar;

    public static WaitCommentFragment newInstance(int tag){
        WaitCommentFragment instance = new WaitCommentFragment();
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
        View view = LayoutInflater.from(getContext()).inflate(R.layout.layout_recycler_empty, null);
        adapter.setEmptyView(view);
        adapter.setEnableLoadMore(false);

        progressBar = rootView.findViewById(R.id.progressBar);

        mPresenter.initParamsBean();
    }

    @Override
    protected void lazyLoad() {
        mPresenter.refreshData();
    }

    @Override
    protected WaitCommentPresenter getRealPresenter() {
        return new WaitCommentPresenter();
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
    public void setNewData(List<OrderListItemEntity> newDataList, boolean isLast) {
        if (progressBar != null && progressBar.isShown()){
            progressBar.setVisibility(View.GONE);
        }
        adapter.setNewData(newDataList);
        refreshLayout.setEnabled(true);
        refreshLayout.setRefreshing(false);
        if (isLast){
            adapter.loadMoreEnd();
        }else {
            adapter.setEnableLoadMore(true);
        }
    }

    @Override
    public void refreshFailed(String failMsg) {
        if (progressBar != null && progressBar.isShown()){
            progressBar.setVisibility(View.GONE);
        }
        //ToastUtils.showShortToast(getContext(), failMsg);
        refreshLayout.setEnabled(true);
        refreshLayout.setRefreshing(false);
        //adapter.setEnableLoadMore(false);
    }

    @Override
    public void addMoreData(List<OrderListItemEntity> moreDataList, boolean isLast) {
        adapter.addData(moreDataList);
        adapter.loadMoreComplete();
        if (isLast){
            adapter.loadMoreEnd();
        }
        refreshLayout.setEnabled(true);
    }

    @Override
    public void loadMoreFailed(String failMsg) {
        ToastUtils.showShortToast(getContext(), failMsg);
        adapter.loadMoreFail();
        refreshLayout.setEnabled(true);
    }
}
