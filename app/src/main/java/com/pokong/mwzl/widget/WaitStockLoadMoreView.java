package com.pokong.mwzl.widget;

import com.chad.library.adapter.base.loadmore.LoadMoreView;
import com.pokong.mwzl.R;

/**
 * Created on 2018/12/14 9:58
 * User: Zheng
 * E-mail: zhengCH12138@163.com
 */
public class WaitStockLoadMoreView extends LoadMoreView {
    @Override
    public int getLayoutId() {
        return R.layout.view_load_more;
    }

    @Override
    protected int getLoadingViewId() {
        return R.id.load_more_loading_view;
    }

    @Override
    protected int getLoadFailViewId() {
        return R.id.load_more_load_fail_view;
    }

    @Override
    protected int getLoadEndViewId() {
        return R.id.load_more_load_end_view;
    }
}
