package com.pokong.mwzl.shop;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.pokong.bluetooth.MyBtPrintService;
import com.pokong.library.base.BaseFragment;
import com.pokong.library.util.LogUtils;
import com.pokong.library.util.ToastUtils;
import com.pokong.mwzl.R;
import com.pokong.mwzl.app.MyApplication;
import com.pokong.mwzl.data.bean.business.ShopInfoResponseBean;
import com.pokong.mwzl.setting.bluetooth.BluetoothActivity;
import com.qs.helper.printer.PrinterClass;

/**
 * Created on 2018/11/16 15:56
 * User: Zheng
 * E-mail: zhengCH12138@163.com
 */
public class ShopManageFragment extends BaseFragment<ShopManagePresenter> implements ShopManageContract.View {

    private ImageView mScanIv;
    private ImageView mNoticeIv;
    private ImageView mBluetoothIv;

    private ImageView mShopLogoIv;
    private TextView mShopNameTv;
    private TextView mManagerTv;
    private TextView mTelephoneTv;
    private TextView mAddressTv;

    private TextView mStatisticsTv;
    private TextView mLocationTv;
    private TextView mOrderQueryTv;
    private TextView mCommentTv;

    public static ShopManageFragment newInstance(int tag){
        ShopManageFragment instance = new ShopManageFragment();
        Bundle args = new Bundle();
        args.putInt("tag", tag);
        instance.setArguments(args);
        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_home_shop, container, false);

        initUI(mView);

        return mView;
    }

    @Override
    public void onResume() {
        super.onResume();

        refreshShopInfo();

        LogUtils.e("ShopManageFragment", String.valueOf(MyBtPrintService.getInstance().getPrintState()));
        if (MyBtPrintService.getInstance().getPrintState() == PrinterClass.STATE_CONNECTED){
            mBluetoothIv.setImageResource(R.mipmap.toolbar_bluetooth);
        }else {
            mBluetoothIv.setImageResource(R.mipmap.toolbar_bluetooth_break);
        }
    }

    private void initUI(View view) {
        mScanIv = view.findViewById(R.id.homeshop_iv_scan);
        mScanIv.setOnClickListener(v -> clickScanIcon());
        mNoticeIv = view.findViewById(R.id.homeshop_iv_notice);
        mNoticeIv.setOnClickListener(v -> clickNoticeIcon());
        mBluetoothIv = view.findViewById(R.id.homeshop_iv_bluetooth);
        mBluetoothIv.setOnClickListener(v -> clickBluetoothIcon());

        mShopLogoIv = view.findViewById(R.id.homeshop_iv_logo);
        mShopNameTv = view.findViewById(R.id.homeshop_tv_shopname);
        mManagerTv = view.findViewById(R.id.homeshop_tv_manager);
        mTelephoneTv = view.findViewById(R.id.homeshop_tv_telephone);
        mAddressTv = view.findViewById(R.id.homeshop_tv_address);

        mStatisticsTv = view.findViewById(R.id.homeshop_tv_statistics);
        mStatisticsTv.setOnClickListener(v -> clickStatistics());
        mLocationTv = view.findViewById(R.id.homeshop_tv_location);
        mLocationTv.setOnClickListener(v -> clickLocation());
        mOrderQueryTv = view.findViewById(R.id.homeshop_tv_query);
        mOrderQueryTv.setOnClickListener(v -> clickOrderQuery());
        mCommentTv = view.findViewById(R.id.homeshop_tv_comment);
        mCommentTv.setOnClickListener(v -> clickComment());
    }


    @Override
    protected ShopManagePresenter getRealPresenter() {
        return new ShopManagePresenter();
    }

    @Override
    public void clickScanIcon() {
        //todo 跳转到"扫码"页面
        ToastUtils.showShortToast(getContext(), "扫码功能尚未开放");
    }

    @Override
    public void clickNoticeIcon() {
        //todo 跳转到"系统消息"页面
        ToastUtils.showShortToast(getContext(), "公告系统尚未开放");
    }

    @Override
    public void clickBluetoothIcon() {
        //todo 跳转到"蓝牙设置"页面
        Intent intent = new Intent(getContext(), BluetoothActivity.class);
        startActivity(intent);
    }

    @Override
    public void clickStatistics() {
        //todo 跳转到"统计"页面
        ToastUtils.showShortToast(getContext(), "统计功能尚未开放");
    }

    @Override
    public void clickLocation() {
        //todo 跳转到"地址校对"页面
        ToastUtils.showShortToast(getContext(), "地址校对功能尚未开放");
    }

    @Override
    public void clickOrderQuery() {
        //todo 跳转到"订单查询"页面
        ToastUtils.showShortToast(getContext(), "订单查询功能尚未开放");
    }

    @Override
    public void clickComment() {
        //todo 跳转到"商品评价"页面
        ToastUtils.showShortToast(getContext(), "商品评价功能尚未开放");
    }

    @Override
    public void showBluetoothConnectedIcon() {
        mBluetoothIv.setImageResource(R.mipmap.toolbar_bluetooth);
    }

    @Override
    public void showBluetoothBreakIcon() {
        mBluetoothIv.setImageResource(R.mipmap.toolbar_bluetooth_break);
    }

    @Override
    public void refreshShopInfo() {
        ShopInfoResponseBean shopInfo = MyApplication.getInstance().getShopInfo();
        if (shopInfo != null){

            String imgUrl = shopInfo.getImgurl();
            if (imgUrl != null && !("null".equals(imgUrl)) && imgUrl.length() > 0){
                RequestOptions options = new RequestOptions()
                        .placeholder(R.mipmap.logo)
                        .error(R.mipmap.logo);
                Glide.with(getContext())
                        .load(shopInfo.getImgurl())
                        .apply(options)
                        .into(mShopLogoIv);
            }

            String shopName = shopInfo.getStore_name();
            if (shopName != null && !("null".equals(shopName)) && shopName.length() > 0){
                mShopNameTv.setText(shopName);
            }

            String managerName = shopInfo.getContact();
            if (shopName != null && !("null".equals(shopName)) && shopName.length() > 0){
                mManagerTv.setText(shopName);
            }

            String telephoneStr = shopInfo.getStore_telephone();
            if (telephoneStr != null && !("null".equals(telephoneStr)) && telephoneStr.length() > 0){
                mTelephoneTv.setText(telephoneStr);
            }

            String addressStr = shopInfo.getStore_address();
            if (addressStr != null && !("null".equals(addressStr)) && addressStr.length() > 0){
                mAddressTv.setText(addressStr);
            }
        }
    }

}
