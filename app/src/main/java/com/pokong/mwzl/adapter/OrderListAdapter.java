package com.pokong.mwzl.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.pokong.mwzl.R;
import com.pokong.mwzl.data.bean.OrderListItemEntity;

/**
 * Created on 2018/11/24 10:51
 * User: Zheng
 * E-mail: zhengCH12138@163.com
 */
public class OrderListAdapter extends BaseQuickAdapter<OrderListItemEntity, BaseViewHolder> {

    public OrderListAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, OrderListItemEntity item) {
        if (item.isOpend()){//如果当前Item为展开状态，显示内容区，并把图标切换为关闭图标
            helper.setGone(R.id.ordercard_lly_content, true)
                    .setImageResource(R.id.ordercard_iv_expand, R.mipmap.close);
        }else {//如果当前Item为关闭状态，隐藏内容区，并把图标切换为展开图标
            helper.setGone(R.id.ordercard_lly_content, false)
                    .setImageResource(R.id.ordercard_iv_expand, R.mipmap.expand);
        }
    }
}
