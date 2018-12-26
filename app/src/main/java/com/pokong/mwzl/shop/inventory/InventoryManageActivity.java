package com.pokong.mwzl.shop.inventory;

import android.content.Context;
import android.support.v7.widget.Toolbar;

import com.pokong.library.base.BaseActivity;
import com.pokong.mwzl.R;

/**
 * Created on 2018/12/20 15:16
 * User: Zheng
 * E-mail: zhengCH12138@163.com
 */
public class InventoryManageActivity extends BaseActivity<InventoryManagePresenter> implements InventoryManageContract.View {
    @Override
    public Context getRealContext() {
        return this;
    }

    @Override
    public int getContentViewRes() {
        return R.layout.activity_shop_inventory;
    }

    @Override
    public void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null){
            getSupportActionBar().setTitle("库存管理");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public void initUI() {

    }

    @Override
    protected InventoryManagePresenter getRealPresenter() {
        return new InventoryManagePresenter();
    }
}
