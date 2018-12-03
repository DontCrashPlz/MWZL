package com.pokong.mwzl;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.RadioButton;

import com.pokong.library.base.BaseActivity;
import com.pokong.library.base.BasePresenter;
import com.pokong.library.util.ToastUtils;
import com.pokong.mwzl.order.OrderFragment;
import com.pokong.mwzl.setting.SettingFragment;
import com.pokong.mwzl.shop.ShopManageFragment;

/**
 * Created on 2018/11/15 14:09
 * User: Zheng
 * E-mail: zhengCH12138@163.com
 */
public class HomeActivity extends BaseActivity<HomePresenter> implements CompoundButton.OnCheckedChangeListener {

    private RadioButton mOrderRbtn;
    private RadioButton mShopRbtn;
    private RadioButton mSettingRbtn;

    private FragmentManager fragmentManager;

    @Override
    public Context getRealContext() {
        return this;
    }

    @Override
    public int getContentViewRes() {
        return R.layout.acitivity_home;
    }

    @Override
    public void initToolbar() {
        View decorView = getWindow().getDecorView();
        int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
        decorView.setSystemUiVisibility(option);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
    }

    @Override
    public void initUI() {

        fragmentManager = getSupportFragmentManager();

        mOrderRbtn = findViewById(R.id.home_rbtn_order);
        mOrderRbtn.setOnCheckedChangeListener(this);
        mShopRbtn = findViewById(R.id.home_rbtn_shop);
        mShopRbtn.setOnCheckedChangeListener(this);
        mSettingRbtn = findViewById(R.id.home_rbtn_setting);
        mSettingRbtn.setOnCheckedChangeListener(this);

        //默认选中订单管理页
        mOrderRbtn.setChecked(true);
    }

    @Override
    protected HomePresenter getRealPresenter() {
        return null;
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        int vId = buttonView.getId();
        switch (vId){
            case R.id.home_rbtn_order:{
                if (isChecked){
                    fragmentManager.beginTransaction()
                            .replace(R.id.home_fly_fragment, OrderFragment.newInstance(0))
                            .commit();
                }
                break;
            }
            case R.id.home_rbtn_shop:{
                if (isChecked){
                    fragmentManager.beginTransaction()
                            .replace(R.id.home_fly_fragment, ShopManageFragment.newInstance(0))
                            .commit();
                }
                break;
            }
            case R.id.home_rbtn_setting:{
                if (isChecked){
                    fragmentManager.beginTransaction()
                            .replace(R.id.home_fly_fragment, SettingFragment.newInstance(0))
                            .commit();
                }
                break;
            }
        }
    }

}
