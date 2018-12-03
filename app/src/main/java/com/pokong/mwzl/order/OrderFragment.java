package com.pokong.mwzl.order;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.flyco.tablayout.SlidingTabLayout;
import com.pokong.library.base.BaseFragment;
import com.pokong.mwzl.R;
import com.pokong.mwzl.order.all.AllOrderFragment;
import com.pokong.mwzl.order.complete.CompletedFragment;
import com.pokong.mwzl.order.deliver.WaitDeliverFragment;
import com.pokong.mwzl.order.pick.WaitPickFragment;
import com.pokong.mwzl.order.stock.WaitStockFragment;

/**
 * Created on 2018/11/16 15:53
 * User: Zheng
 * E-mail: zhengCH12138@163.com
 */
public class OrderFragment extends BaseFragment<OrderPresenter> implements OrderContract.View {

    private TextView mShopNameTv;
    private ImageView mScanIv;
    private ImageView mNoticeIv;
    private ImageView mBluetoothIv;

    private SlidingTabLayout mTabLayout;
    private ViewPager mOrderVp;

    public static OrderFragment newInstance(int tag){
        OrderFragment instance = new OrderFragment();
        Bundle args = new Bundle();
        args.putInt("tag", tag);
        instance.setArguments(args);
        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_home_order, container, false);
        initView(mView);
        return mView;
    }

    private void initView(View view){
        mShopNameTv = view.findViewById(R.id.homeorder_tv_shopname);

        mScanIv = view.findViewById(R.id.homeorder_iv_scan);
        mScanIv.setOnClickListener(v -> clickScanIcon());

        mNoticeIv = view.findViewById(R.id.homeorder_iv_notice);
        mNoticeIv.setOnClickListener(v -> clickNoticeIcon());

        mBluetoothIv = view.findViewById(R.id.homeorder_iv_bluetooth);
        mBluetoothIv.setOnClickListener(v -> clickBluetoothIcon());

        mOrderVp = view.findViewById(R.id.homeorder_vp_content);
        mOrderVp.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                switch (i){
                    case 0:{
                        return WaitStockFragment.newInstance(0);
                    }
                    case 1:{
                        return WaitPickFragment.newInstance(0);
                    }
                    case 2:{
                        return WaitDeliverFragment.newInstance(0);
                    }
                    case 3:{
                        return CompletedFragment.newInstance(0);
                    }
                    case 4:{
                        return AllOrderFragment.newInstance(0);
                    }
                    default:
                        throw new NullPointerException("Home Page ViewPager not found Fragment.");
                }
            }

            @Override
            public int getCount() {
                return 5;
            }
        });

        mTabLayout = view.findViewById(R.id.homeorder_tablayout);
        mTabLayout.setViewPager(mOrderVp,new String[]{"待备货", "待取货", "待配送", "已完成", "全部订单"});

    }

    @Override
    protected OrderPresenter getRealPresenter() {
        return new OrderPresenter();
    }

    /**
     * 扫描二维码图标点击事件
     */
    @Override
    public void clickScanIcon(){
        //todo 跳转到扫码页面
    }

    /**
     * 系统消息图标点击事件
     */
    @Override
    public void clickNoticeIcon(){
        //todo 跳转到系统消息页面
    }

    /**
     * 蓝牙图标点击事件
     */
    @Override
    public void clickBluetoothIcon(){
        //todo 跳转到蓝牙设置页面
    }

}
