package com.pokong.mwzl.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.pokong.mwzl.R;
import com.pokong.mwzl.data.bean.MemberHistoryBean;

/**
 * Created on 2018/11/24 10:51
 * User: Zheng
 * E-mail: zhengCH12138@163.com
 */
public class MemberHistoryListAdapter extends BaseQuickAdapter<MemberHistoryBean, BaseViewHolder> {

    public MemberHistoryListAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, MemberHistoryBean item) {
        helper.setText(R.id.member_history_name, item.getMemberName())
                .setText(R.id.member_history_time, item.getCurrentTime())
                .setText(R.id.member_history_amount, item.getAmount());
    }

}
