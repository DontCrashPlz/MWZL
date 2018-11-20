package com.pokong.mwzl;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.RadioButton;

import com.pokong.library.base.BaseActivity;
import com.pokong.library.base.BasePresenter;

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
//                    fragmentManager.beginTransaction()
//                            .replace(R.id.home_fly_fragment, HomePageFragment.newInstance(""))
//                            .commit();
                }
                break;
            }
            case R.id.home_rbtn_shop:{
                if (isChecked){
//                    fragmentManager.beginTransaction()
//                            .replace(R.id.home_fly_fragment, HomePageFragment.newInstance(""))
//                            .commit();
                }
                break;
            }
            case R.id.home_rbtn_setting:{
                if (isChecked){
//                    fragmentManager.beginTransaction()
//                            .replace(R.id.home_fly_fragment, HomePageFragment.newInstance(""))
//                            .commit();
                }
                break;
            }
        }
    }
}
