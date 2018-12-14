package com.pokong.mwzl.shop.query;

import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.pokong.bluetooth.MyBtPrintService;
import com.pokong.library.base.BaseActivity;
import com.pokong.library.util.DialogFactory;
import com.pokong.library.util.LogUtils;
import com.pokong.library.util.SharedPrefUtils;
import com.pokong.library.util.ToastUtils;
import com.pokong.library.util.Tools;
import com.pokong.mwzl.HomeActivity;
import com.pokong.mwzl.R;
import com.pokong.mwzl.adapter.OrderListAdapter;
import com.pokong.mwzl.app.OrderStatus;
import com.pokong.mwzl.data.DataRequestCallback;
import com.pokong.mwzl.data.bean.OrderListItemEntity;
import com.pokong.mwzl.setting.bluetooth.BluetoothPresenter;
import com.qs.helper.printer.PrinterClass;

import java.util.Calendar;
import java.util.List;

/**
 * Created on 2018/12/10 18:27
 * User: Zheng
 * E-mail: zhengCH12138@163.com
 */
public class OrderQueryActivity extends BaseActivity<OrderQueryPresenter> implements OrderQueryContract.View, SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener, CompoundButton.OnCheckedChangeListener, View.OnClickListener {

    private DrawerLayout mDrawerLayout;

    private Toolbar mToolbar;
    private ImageView mSelectIv;

    private EditText mOrderNumEt;
    private ImageView mQueryIv;

    private SwipeRefreshLayout mRefreshLayout;
    private RecyclerView mRecycler;
    private OrderListAdapter mAdapter;

    private RadioButton mStatusAllRbtn;
    private RadioButton mStatusPayRbtn;
    private RadioButton mStatusStockRbtn;
    private RadioButton mStatusReceiveRbtn;
    private RadioButton mStatusCommentRbtn;
    private RadioButton mStatusCompleteRbtn;

    private RadioButton mTypeAllRbtn;
    private RadioButton mTypeSelfRbtn;
    private RadioButton mTypeDeliveryRbtn;

    private TextView mStartTimeTv;
    private TextView mEndTimeTv;

    private TextView mCancelTv;
    private TextView mConfirmTv;

    private AlertDialog mLoadingProgress;

    @Override
    public Context getRealContext() {
        return this;
    }

    @Override
    public int getContentViewRes() {
        return R.layout.activity_shop_order_query;
    }

    @Override
    public void initToolbar() {
        mDrawerLayout = findViewById(R.id.drawerlayout);

        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("订单查询");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        mSelectIv = findViewById(R.id.orderquery_iv_select);
        mSelectIv.setOnClickListener(v -> mDrawerLayout.openDrawer(Gravity.END));
    }

    @Override
    public void initUI() {
        mPresenter.initParamsBean();

        mOrderNumEt = findViewById(R.id.orderquery_et_ordernum);
        mQueryIv = findViewById(R.id.orderquery_iv_query);
        mQueryIv.setOnClickListener(v -> {
            String queryOrderNum = mOrderNumEt.getText().toString().trim();
            if (Tools.isBlank(queryOrderNum)){
                ToastUtils.showShortToast(getRealContext(), "请输入有效订单号");
                return;
            }
            //todo 弹出加载框
            if (mLoadingProgress != null && !mLoadingProgress.isShowing())
                mLoadingProgress.show();
            //todo 更新请求参数
            mPresenter.updateOrderNoParams(queryOrderNum);
            //todo 开始按筛选条件查询数据
            mPresenter.refreshData();
        });

        initContentArea();

        initStateSelectArea();

        initTypeSelectArea();

        initDateSelectArea();

        mCancelTv = findViewById(R.id.orderquery_tv_cancel);
        mCancelTv.setOnClickListener(this);
        mConfirmTv = findViewById(R.id.orderquery_tv_comfirm);
        mConfirmTv.setOnClickListener(this);

        mLoadingProgress = DialogFactory.createLoadingDialog(getRealContext());
        mLoadingProgress.setCanceledOnTouchOutside(false);
    }

    /**
     * 初始化内容显示区域
     */
    private void initContentArea() {
        mRefreshLayout = findViewById(R.id.orderquery_swiperefresh);
        mRefreshLayout.setOnRefreshListener(this);
        mRefreshLayout.setRefreshing(false);
        mRefreshLayout.setEnabled(false);

        mRecycler = findViewById(R.id.orderquery_recycler);
        mRecycler.setLayoutManager(new LinearLayoutManager(getRealContext()));
        mAdapter = new OrderListAdapter(R.layout.layout_cardview_order_list);
        mAdapter.setOnItemClickListener((adapter, view, position) -> {
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
        mAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            int vId = view.getId();
            OrderListItemEntity currentItemEntity = (OrderListItemEntity) adapter.getData().get(position);
            if (vId == R.id.ordercard_tv_print){
                showPrintDialog(currentItemEntity);
            }else if (vId == R.id.ordercard_tv_complete){
                showStockReadyDialog(currentItemEntity.getId(), currentItemEntity.getOrder_serial_num(), position);
            }else if (vId == R.id.ordercard_tv_confirm){
                showConfirmDialog(currentItemEntity.getId(), currentItemEntity.getOrder_serial_num(), position);
            }else {
                ToastUtils.showShortToast(getRealContext(), "点击了" + currentItemEntity.getId() + "订单的未知按钮");
            }
        });
        mRecycler.setAdapter(mAdapter);
        mAdapter.setOnLoadMoreListener(this, mRecycler);
        View view = LayoutInflater.from(getRealContext()).inflate(R.layout.layout_recycler_empty, null);
        mAdapter.setEmptyView(view);
        mAdapter.setEnableLoadMore(false);
    }

    /**
     * 初始化订单状态筛选区域
     */
    private void initStateSelectArea() {
        mStatusAllRbtn = findViewById(R.id.orderquery_rbtn_status_all);
        mStatusAllRbtn.setOnCheckedChangeListener(this);
        mStatusPayRbtn = findViewById(R.id.orderquery_rbtn_status_pay);
        mStatusPayRbtn.setOnCheckedChangeListener(this);
        mStatusStockRbtn = findViewById(R.id.orderquery_rbtn_status_stock);
        mStatusStockRbtn.setOnCheckedChangeListener(this);
        mStatusReceiveRbtn = findViewById(R.id.orderquery_rbtn_status_receive);
        mStatusReceiveRbtn.setOnCheckedChangeListener(this);
        mStatusCommentRbtn = findViewById(R.id.orderquery_rbtn_status_comment);
        mStatusCommentRbtn.setOnCheckedChangeListener(this);
        mStatusCompleteRbtn = findViewById(R.id.orderquery_rbtn_status_complete);
        mStatusCompleteRbtn.setOnCheckedChangeListener(this);

        mStatusAllRbtn.setChecked(true);
    }

    /**
     * 初始化订单配送类型筛选区域
     */
    private void initTypeSelectArea() {
        mTypeAllRbtn = findViewById(R.id.orderquery_rbtn_type_all);
        mTypeAllRbtn.setOnCheckedChangeListener(this);
        mTypeSelfRbtn = findViewById(R.id.orderquery_rbtn_type_self);
        mTypeSelfRbtn.setOnCheckedChangeListener(this);
        mTypeDeliveryRbtn = findViewById(R.id.orderquery_rbtn_type_delivery);
        mTypeDeliveryRbtn.setOnCheckedChangeListener(this);

        mTypeAllRbtn.setChecked(true);
    }

    /**
     * 初始化日期范围筛选区域
     */
    private void initDateSelectArea() {
        mStartTimeTv = findViewById(R.id.orderquery_tv_starttime);
        mStartTimeTv.setOnClickListener(this);
        mEndTimeTv = findViewById(R.id.orderquery_tv_endtime);
        mEndTimeTv.setOnClickListener(this);
    }

    @Override
    protected OrderQueryPresenter getRealPresenter() {
        return new OrderQueryPresenter();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()== android.R.id.home)
            finish();
        return true;
    }

    @Override
    public void onLoadMoreRequested() {
        mRefreshLayout.setEnabled(false);
        mPresenter.loadMoreData();
    }

    @Override
    public void onRefresh() {
        mAdapter.setEnableLoadMore(false);
        mPresenter.refreshData();
    }

    @Override
    public void setNewData(List<OrderListItemEntity> newDataList, boolean isLast) {
        if (mLoadingProgress != null && mLoadingProgress.isShowing())
            mLoadingProgress.dismiss();
        mAdapter.setNewData(newDataList);
        mRefreshLayout.setEnabled(true);
        mRefreshLayout.setRefreshing(false);
        if (isLast){
            mAdapter.loadMoreEnd();
        }else {
            mAdapter.setEnableLoadMore(true);
        }
    }

    @Override
    public void refreshFailed(String failMsg) {
        if (mLoadingProgress != null && mLoadingProgress.isShowing())
            mLoadingProgress.dismiss();
        //ToastUtils.showShortToast(getContext(), failMsg);
        mRefreshLayout.setEnabled(true);
        mRefreshLayout.setRefreshing(false);
        //adapter.setEnableLoadMore(false);
    }

    @Override
    public void addMoreData(List<OrderListItemEntity> moreDataList, boolean isLast) {
        mAdapter.addData(moreDataList);
        mAdapter.loadMoreComplete();
        if (isLast){
            mAdapter.loadMoreEnd();
        }
        mRefreshLayout.setEnabled(true);
    }

    @Override
    public void loadMoreFailed(String failMsg) {
        ToastUtils.showShortToast(getRealContext(), failMsg);
        mAdapter.loadMoreFail();
        mRefreshLayout.setEnabled(true);
    }

    @Override
    public void showPrintDialog(OrderListItemEntity currentItemEntity) {
        View view = LayoutInflater.from(getRealContext()).inflate(R.layout.layout_dialog_print_confirm, null);
        AlertDialog dialog = DialogFactory.createDialog(getRealContext(), view);
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
                ToastUtils.showShortToast(getRealContext(), "请先连接打印机");
            }else {
                boolean isDoublePrint = (boolean) SharedPrefUtils.get(getRealContext(),
                        BluetoothPresenter.SP_KEY_IS_DOUBLE_PRINT,
                        false);
                if (isDoublePrint){
                    HomeActivity.printOrder(currentItemEntity);
                }
                HomeActivity.printOrder(currentItemEntity);
                ToastUtils.showShortToast(getRealContext(), "正在打印，若未正常打印请检测打印机");
            }
            dialog.dismiss();
        });
        cancelBtn.setOnClickListener(v -> dialog.dismiss());

        dialog.show();
    }

    @Override
    public void showStockReadyDialog(long orderId, String orderSerial, int position) {
        View view = LayoutInflater.from(getRealContext()).inflate(R.layout.layout_dialog_stock_ready, null);
        AlertDialog dialog = DialogFactory.createDialog(getRealContext(), view);
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
            AlertDialog loadingDialog = DialogFactory.createLoadingDialog(getRealContext());
            mPresenter.orderReady(orderId, new DataRequestCallback<String>() {
                @Override
                public void onSuccessed(String s) {
                    loadingDialog.dismiss();
                    ToastUtils.showShortToast(getRealContext(), "备货完成");
                    mAdapter.remove(position);
                }

                @Override
                public void onFailed(String errorMsg) {
                    loadingDialog.dismiss();
                    ToastUtils.showShortToast(getRealContext(), "\"备货完成\"失败->" + errorMsg);
                }
            });
        });
        cancelBtn.setOnClickListener(v -> dialog.dismiss());

        dialog.show();
    }

    @Override
    public void showConfirmDialog(long orderId, String orderSerial, int position) {
        View view = LayoutInflater.from(getRealContext()).inflate(R.layout.layout_dialog_pick_confirm, null);
        AlertDialog dialog = DialogFactory.createDialog(getRealContext(), view);
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
            AlertDialog loadingDialog = DialogFactory.createLoadingDialog(getRealContext());
            mPresenter.pickConfirm(orderId, new DataRequestCallback<String>() {
                @Override
                public void onSuccessed(String s) {
                    loadingDialog.dismiss();
                    ToastUtils.showShortToast(getRealContext(), "已确认收货");
                    mAdapter.remove(position);
                }

                @Override
                public void onFailed(String errorMsg) {
                    loadingDialog.dismiss();
                    ToastUtils.showShortToast(getRealContext(), "\"确认收货\"失败->" + errorMsg);
                }
            });
        });
        cancelBtn.setOnClickListener(v -> dialog.dismiss());

        dialog.show();
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        int vId = buttonView.getId();
        if (isChecked){
            switch (vId){
                case R.id.orderquery_rbtn_status_all:{//订单状态 -> 全部
                    mPresenter.updateStateParams(OrderStatus.ALL);
                    break;
                }
                case R.id.orderquery_rbtn_status_pay:{//订单状态 -> 待支付
                    mPresenter.updateStateParams(OrderStatus.WAIT_PAY);
                    break;
                }
                case R.id.orderquery_rbtn_status_stock:{//订单状态 -> 待备货
                    mPresenter.updateStateParams(OrderStatus.WAIT_STOCK);
                    break;
                }
                case R.id.orderquery_rbtn_status_receive:{//订单状态 -> 待收货
                    mPresenter.updateStateParams(OrderStatus.WAIT_RECEIVE);
                    break;
                }
                case R.id.orderquery_rbtn_status_comment:{//订单状态 -> 待评价
                    mPresenter.updateStateParams(OrderStatus.WAIT_COMMENT);
                    break;
                }
                case R.id.orderquery_rbtn_status_complete:{//订单状态 -> 已完成
                    mPresenter.updateStateParams(OrderStatus.COMPLETED);
                    break;
                }
                case R.id.orderquery_rbtn_type_all:{//配送方式 -> 全部
                    mPresenter.updateTypeParams("");
                    break;
                }
                case R.id.orderquery_rbtn_type_self:{//配送方式 -> 自提
                    mPresenter.updateTypeParams("self_mention");
                    break;
                }
                case R.id.orderquery_rbtn_type_delivery:{//配送方式 -> 配送
                    mPresenter.updateTypeParams("delivery");
                    break;
                }
                default:
                    break;
            }
        }
    }

    @Override
    public void onClick(View v) {
        int vId = v.getId();
        switch (vId){
            case R.id.orderquery_tv_starttime:{//时间范围 -> 开始时间
                //todo 弹出开始时间选择器
                Calendar c = Calendar.getInstance();
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        getRealContext(),
                        (view, year, month, dayOfMonth) -> {
                            String startTime = String.valueOf(year) + "-" + String.valueOf(month + 1) + "-" + String.valueOf(dayOfMonth);
                            mStartTimeTv.setText(startTime);
                            mStartTimeTv.setBackgroundResource(R.drawable.radiobutton_background_orange_hollow_selected);
                            mStartTimeTv.setTextColor(getResources().getColor(R.color.button_background_orange, null));
                            mPresenter.updateBeginTimeParams(startTime + " 00:00:00");
                        },
                        c.get(Calendar.YEAR),
                        c.get(Calendar.MONTH),
                        c.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
                break;
            }
            case R.id.orderquery_tv_endtime:{//时间范围 -> 结束时间
                //todo 弹出结束时间选择器
                Calendar c = Calendar.getInstance();
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        getRealContext(),
                        (view, year, month, dayOfMonth) -> {
                            String endTime = String.valueOf(year) + "-" + String.valueOf(month + 1) + "-" + String.valueOf(dayOfMonth);
                            mEndTimeTv.setText(endTime);
                            mEndTimeTv.setBackgroundResource(R.drawable.radiobutton_background_orange_hollow_selected);
                            mEndTimeTv.setTextColor(getResources().getColor(R.color.button_background_orange, null));
                            mPresenter.updateEndTimeParams(endTime + " 23:59:59");
                        },
                        c.get(Calendar.YEAR),
                        c.get(Calendar.MONTH),
                        c.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
                break;
            }
            case R.id.orderquery_tv_cancel:{//取消条件查询按钮
                //todo 关闭条件筛选抽屉
                mDrawerLayout.closeDrawer(Gravity.END);
                break;
            }
            case R.id.orderquery_tv_comfirm:{//开始条件查询按钮
                //todo 关闭条件筛选抽屉
                mDrawerLayout.closeDrawer(Gravity.END);
                //todo 弹出加载框
                if (mLoadingProgress != null && !mLoadingProgress.isShowing())
                    mLoadingProgress.show();
                //todo 开始按筛选条件查询数据
                mPresenter.refreshData();
                break;
            }
        }
    }
}
