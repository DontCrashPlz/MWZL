package com.pokong.mwzl.order.all;

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
import com.pokong.bluetooth.MyBtPrintService;
import com.pokong.library.base.LazyLoadFragment;
import com.pokong.library.util.DialogFactory;
import com.pokong.library.util.LogUtils;
import com.pokong.library.util.SharedPrefUtils;
import com.pokong.library.util.ToastUtils;
import com.pokong.mwzl.HomeActivity;
import com.pokong.mwzl.R;
import com.pokong.mwzl.adapter.OrderListAdapter;
import com.pokong.mwzl.data.DataRequestCallback;
import com.pokong.mwzl.data.bean.OrderListItemEntity;
import com.pokong.mwzl.setting.bluetooth.BluetoothPresenter;
import com.qs.helper.printer.PrinterClass;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2018/11/16 15:46
 * User: Zheng
 * E-mail: zhengCH12138@163.com
 */
public class AllOrderFragment extends LazyLoadFragment<AllOrderPresenter> implements AllOrderContract.View, SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {

    private SwipeRefreshLayout refreshLayout;
    private RecyclerView recyclerView;
    private OrderListAdapter adapter;
    private ProgressBar progressBar;

    public static AllOrderFragment newInstance(int tag){
        AllOrderFragment instance = new AllOrderFragment();
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
            if (vId == R.id.ordercard_tv_print){
                showPrintDialog(currentItemEntity);
            }else if (vId == R.id.ordercard_tv_complete){
                showStockReadyDialog(currentItemEntity.getId(), currentItemEntity.getOrder_serial_num(), position);
            }else if (vId == R.id.ordercard_tv_confirm){
                showConfirmDialog(currentItemEntity.getId(), currentItemEntity.getOrder_serial_num(), position);
            }else {
                ToastUtils.showShortToast(getContext(), "点击了" + currentItemEntity.getId() + "订单的未知按钮");
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
    protected AllOrderPresenter getRealPresenter() {
        return new AllOrderPresenter();
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
    public void showPrintDialog(OrderListItemEntity currentItemEntity) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.layout_dialog_print_confirm, null);
        AlertDialog dialog = DialogFactory.createDialog(getContext(), view);
        TextView contentTv = view.findViewById(R.id.dialog_print_text_description);
        TextView printBtn = view.findViewById(R.id.dialog_print_button_confirm);
        TextView cancelBtn = view.findViewById(R.id.dialog_print_button_cancel);

        String contentStr = String.format(getString(R.string.dialog_print_default_info), currentItemEntity.getOrder_serial_num());

        SpannableString spannableString = new SpannableString(contentStr);
        ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.RED);
        spannableString.setSpan(colorSpan, 5, 9, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        StyleSpan styleSpan_B  = new StyleSpan(Typeface.BOLD);
        spannableString.setSpan(styleSpan_B, 5, 9, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);

        contentTv.setText(spannableString);

        printBtn.setOnClickListener(v -> {
            //todo 处理打印小票
            LogUtils.e("WaitStockFragment", "" + MyBtPrintService.getInstance().getPrintState());
            if (MyBtPrintService.getInstance().getPrintState() != PrinterClass.STATE_CONNECTED){
                ToastUtils.showShortToast(getContext(), "请先连接打印机");
            }else {
                boolean isDoublePrint = (boolean) SharedPrefUtils.get(getContext(),
                        BluetoothPresenter.SP_KEY_IS_DOUBLE_PRINT,
                        false);
                if (isDoublePrint){
                    HomeActivity.printOrder(currentItemEntity);
                }
                HomeActivity.printOrder(currentItemEntity);
                ToastUtils.showShortToast(getContext(), "正在打印，若未正常打印请检测打印机");
            }
            dialog.dismiss();
        });
        cancelBtn.setOnClickListener(v -> dialog.dismiss());

        dialog.show();
    }

    @Override
    public void showStockReadyDialog(long orderId, String orderSerial, int position) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.layout_dialog_stock_ready, null);
        AlertDialog dialog = DialogFactory.createDialog(getContext(), view);
        TextView contentTv = view.findViewById(R.id.dialog_ready_text_description);
        TextView printBtn = view.findViewById(R.id.dialog_ready_button_confirm);
        TextView cancelBtn = view.findViewById(R.id.dialog_ready_button_cancel);

        String contentStr = String.format(getString(R.string.dialog_ready_default_info), orderSerial);

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
            mPresenter.orderReady(orderId, new DataRequestCallback<String>() {
                @Override
                public void onSuccessed(String s) {
                    loadingDialog.dismiss();
                    ToastUtils.showShortToast(getContext(), "备货完成");
                    adapter.remove(position);
                }

                @Override
                public void onFailed(String errorMsg) {
                    loadingDialog.dismiss();
                    ToastUtils.showShortToast(getContext(), "\"备货完成\"失败->" + errorMsg);
                }
            });
        });
        cancelBtn.setOnClickListener(v -> dialog.dismiss());

        dialog.show();
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
