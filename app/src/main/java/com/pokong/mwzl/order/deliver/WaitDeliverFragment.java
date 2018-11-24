package com.pokong.mwzl.order.deliver;

import android.os.Bundle;
import android.view.View;

import com.pokong.library.base.LazyLoadFragment;
import com.pokong.mwzl.R;

/**
 * Created on 2018/11/16 15:43
 * User: Zheng
 * E-mail: zhengCH12138@163.com
 */
public class WaitDeliverFragment extends LazyLoadFragment<WaitDeliverPresenter> {

    public static WaitDeliverFragment newInstance(int tag){
        WaitDeliverFragment instance = new WaitDeliverFragment();
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

    }

    @Override
    protected void lazyLoad() {

    }

    @Override
    protected WaitDeliverPresenter getRealPresenter() {
        return null;
    }
}
