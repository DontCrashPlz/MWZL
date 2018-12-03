package com.pokong.mwzl.shop;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.pokong.library.base.BaseFragment;
import com.pokong.mwzl.R;

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

    }

    @Override
    public void clickNoticeIcon() {

    }

    @Override
    public void clickBluetoothIcon() {

    }

    @Override
    public void clickStatistics() {

    }

    @Override
    public void clickLocation() {

    }

    @Override
    public void clickOrderQuery() {

    }

    @Override
    public void clickComment() {

    }
}
