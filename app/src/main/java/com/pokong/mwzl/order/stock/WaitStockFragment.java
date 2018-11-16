package com.pokong.mwzl.order.stock;

import com.pokong.library.base.LazyLoadFragment;

/**
 * Created on 2018/11/16 15:24
 * User: Zheng
 * E-mail: zhengCH12138@163.com
 */
public class WaitStockFragment extends LazyLoadFragment<WaitStockPresenter> {
    @Override
    protected int getContentView() {
        return 0;
    }

    @Override
    protected void init() {

    }

    @Override
    protected void lazyLoad() {

    }

    @Override
    protected WaitStockPresenter getRealPresenter() {
        return new WaitStockPresenter();
    }
}
