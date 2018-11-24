package com.pokong.mwzl.order.pick;

import android.os.Bundle;
import android.view.View;

import com.pokong.library.base.LazyLoadFragment;
import com.pokong.mwzl.R;

/**
 * Created on 2018/11/16 15:42
 * User: Zheng
 * E-mail: zhengCH12138@163.com
 */
public class WaitPickFragment extends LazyLoadFragment<WaitPickPresenter> {

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

    }

    @Override
    protected void lazyLoad() {

    }

    @Override
    protected WaitPickPresenter getRealPresenter() {
        return null;
    }
}
