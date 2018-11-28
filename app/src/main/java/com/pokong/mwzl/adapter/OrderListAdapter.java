package com.pokong.mwzl.adapter;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.pokong.library.util.Tools;
import com.pokong.mwzl.R;
import com.pokong.mwzl.app.OrderStatus;
import com.pokong.mwzl.data.bean.GoodsEntity;
import com.pokong.mwzl.data.bean.OrderListItemEntity;

import java.util.ArrayList;

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

        int orderStatus = item.getOrder_status();
        String orderStatusName = "";
        int headColor;
        int expendIconRes;
        int closeIconRes;
        switch (orderStatus){
            case OrderStatus.WAIT_STOCK:{
                orderStatusName = "待备货";
                headColor = mContext.getColor(R.color.order_status_green);
                expendIconRes = R.mipmap.expand_green;
                closeIconRes = R.mipmap.close_green;
                break;
            }
            case OrderStatus.WAIT_PICK:{
                orderStatusName = "待取货";
                headColor = mContext.getColor(R.color.order_status_yellow);
                expendIconRes = R.mipmap.expand_yellow;
                closeIconRes = R.mipmap.close_yellow;
                break;
            }
            case OrderStatus.WAIT_DELIVER:{
                orderStatusName = "待配送";
                headColor = mContext.getColor(R.color.order_status_yellow);
                expendIconRes = R.mipmap.expand_yellow;
                closeIconRes = R.mipmap.close_yellow;
                break;
            }
            case OrderStatus.COMPLETED:{
                orderStatusName = "已完成";
                headColor = mContext.getColor(R.color.order_status_gary);
                expendIconRes = R.mipmap.expand_gary;
                closeIconRes = R.mipmap.close_gary;
                break;
            }
            default:{
                orderStatusName = "无效订单";
                headColor = mContext.getColor(R.color.order_status_gary);
                expendIconRes = R.mipmap.expand_gary;
                closeIconRes = R.mipmap.close_gary;
            }
        }

        helper.setTextColor(R.id.ordercard_tv_serial, headColor)
                .setTextColor(R.id.ordercard_tv_status, headColor)
                .setTextColor(R.id.ordercard_tv_time, headColor)
                .setBackgroundColor(R.id.ordercard_head_devide, headColor)
                .setText(R.id.ordercard_tv_status, orderStatusName);

        helper.setText(R.id.ordercard_tv_serial, item.getOrder_serial_num())
                .setText(R.id.ordercard_tv_time, String.format(mContext.getString(R.string.ordercard_create_time), item.getCreate_time()))
                .setText(R.id.ordercard_tv_ordernum, item.getOrder_id())
                .setText(R.id.ordercard_tv_name, item.getReceiver_name())
                .setText(R.id.ordercard_tv_phone, item.getReceiver_mobile())
                .setText(R.id.ordercard_tv_goodsnum, Tools.formatNumStr(item.getGoods_count()))
                .setText(R.id.ordercard_tv_orderprice, Tools.formatRmbStr(item.getTotalprice()));

        String deliveryType = item.getDelivery_type_name();
        helper.setText(R.id.ordercard_tv_style, deliveryType);
        if ("送".equals(deliveryType)){
            helper.setBackgroundRes(R.id.ordercard_tv_style, R.drawable.shape_circle_orange);
        }else {
            helper.setBackgroundRes(R.id.ordercard_tv_style, R.drawable.shape_circle_green);
        }

        ArrayList<GoodsEntity> goodsList = item.getGoodlist();
        if (goodsList != null && goodsList.size() > 0){
            RecyclerView goodsListRecycler = helper.getView(R.id.ordercard_recycler);
            goodsListRecycler.setLayoutManager(new LinearLayoutManager(mContext));
            goodsListRecycler.setAdapter(new GoodsListAdapter(mContext, goodsList));
        }

        String classStyle = item.getClass_type();
        if (classStyle != null && "cake".equals(classStyle)){
            helper.setGone(R.id.ordercard_toolnum_panel, true)
                    .setText(R.id.ordercard_tv_toolnum, String.valueOf(item.getPerson_num()))
                    .setGone(R.id.ordercard_age_panel, false)
                    .setText(R.id.ordercard_tv_customage, String.valueOf(item.getPerson_age()));
        }else {
            helper.setGone(R.id.ordercard_toolnum_panel, false)
                    .setGone(R.id.ordercard_age_panel, false);
        }

        if (item.isOpend()){//如果当前Item为展开状态，显示内容区，并把图标切换为关闭图标
            helper.setGone(R.id.ordercard_lly_content, true)
                    .setImageResource(R.id.ordercard_iv_expand, closeIconRes);
        }else {//如果当前Item为关闭状态，隐藏内容区，并把图标切换为展开图标
            helper.setGone(R.id.ordercard_lly_content, false)
                    .setImageResource(R.id.ordercard_iv_expand, expendIconRes);
        }
    }
}
