package com.pokong.mwzl.order.pick;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.pokong.library.base.LazyLoadFragment;
import com.pokong.library.util.DialogFactory;
import com.pokong.library.util.ToastUtils;
import com.pokong.mwzl.R;
import com.pokong.mwzl.adapter.OrderListAdapter;
import com.pokong.mwzl.data.DataRequestCallback;
import com.pokong.mwzl.data.bean.OrderListItemEntity;
import com.pokong.mwzl.order.deliver.WaitDeliverPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2018/11/16 15:42
 * User: Zheng
 * E-mail: zhengCH12138@163.com
 */
public class WaitPickFragment extends LazyLoadFragment<WaitPickPresenter> implements WaitPickContract.View, SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {

    private SwipeRefreshLayout refreshLayout;
    private RecyclerView recyclerView;
    private OrderListAdapter adapter;
    private ProgressBar progressBar;

    public static WaitPickFragment newInstance(int tag){
        WaitPickFragment instance = new WaitPickFragment();
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
        adapter.setOnItemChildClickListener((adapter, view, position) -> {
            int vId = view.getId();
            OrderListItemEntity currentItemEntity = (OrderListItemEntity) adapter.getData().get(position);
            if (vId == R.id.ordercard_tv_confirm){
                showConfirmDialog(currentItemEntity.getId(), currentItemEntity.getOrder_serial_num(), position);
            }else {
                ToastUtils.showShortToast(getContext(), "点击了" + currentItemEntity.getId() + "订单的未知按钮");
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
    protected WaitPickPresenter getRealPresenter() {
        return new WaitPickPresenter();
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

    @Override
    public void showConfirmDialog(long orderId, String orderSerial, int position) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.layout_dialog_pick_confirm, null);
        AlertDialog dialog = DialogFactory.createDialog(getContext(), view);
        TextView contentTv = view.findViewById(R.id.dialog_pick_confirm_text_description);
        TextView printBtn = view.findViewById(R.id.dialog_pick_confirm_button_confirm);
        TextView cancelBtn = view.findViewById(R.id.dialog_pick_confirm_button_cancel);

        String contentStr = String.format(getString(R.string.dialog_pick_confirm_default_info), orderSerial);

        SpannableString spannableString = new SpannableString(contentStr);
        ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.RED);
        spannableString.setSpan(colorSpan, 2, 6, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        StyleSpan styleSpan_B  = new StyleSpan(Typeface.BOLD);
        spannableString.setSpan(styleSpan_B, 2, 6, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);

        contentTv.setText(spannableString);

        printBtn.setOnClickListener(v -> {
            //todo 处理备货完成
            dialog.dismiss();
            AlertDialog loadingDialog = DialogFactory.createLoadingDialog(getContext());
            mPresenter.pickConfirm(orderId, new DataRequestCallback<String>() {
                @Override
                public void onSuccessed(String s) {
                    loadingDialog.dismiss();
                    ToastUtils.showShortToast(getContext(), "已确认收货");
                    adapter.remove(position);
                }

                @Override
                public void onFailed(String errorMsg) {
                    loadingDialog.dismiss();
                    ToastUtils.showShortToast(getContext(), "\"确认收货\"失败->" + errorMsg);
                }
            });
        });
        cancelBtn.setOnClickListener(v -> dialog.dismiss());

        dialog.show();
    }
}
