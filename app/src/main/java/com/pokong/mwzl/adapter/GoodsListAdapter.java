package com.pokong.mwzl.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.pokong.library.util.Tools;
import com.pokong.mwzl.R;
import com.pokong.mwzl.data.bean.GoodsEntity;

import java.util.ArrayList;

/**
 * Created on 2018/11/27 23:38
 * User: Zheng
 * E-mail: zhengCH12138@163.com
 */
public class GoodsListAdapter extends RecyclerView.Adapter<GoodsListAdapter.GoodsViewHolder> {

    private Context mContext;

    private ArrayList<GoodsEntity> mData;

    public GoodsListAdapter(Context context, ArrayList<GoodsEntity> data){
        mContext = context;
        mData = data;
    }

    @NonNull
    @Override
    public GoodsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_item_goodslist, viewGroup, false);
        return new GoodsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GoodsViewHolder goodsViewHolder, int i) {
        GoodsEntity entity = mData.get(i);

        String imageUrl = entity.getImgurl();
        if (!Tools.isBlank(imageUrl)){

            RequestOptions options = new RequestOptions()
                    .placeholder(R.mipmap.logo)
                    .error(R.mipmap.logo);

            Glide.with(mContext)
                    .load(entity.getImgurl())
                    .apply(options)
                    .into(goodsViewHolder.mImageIv);
        }

        goodsViewHolder.mNameTv.setText(entity.getGoods_name());

        goodsViewHolder.mCountTv.setText(Tools.formatNumStr(entity.getCount()));

        String specInfoStr = entity.getSpec_info();
        if (!Tools.isBlank(specInfoStr)){
            goodsViewHolder.mSizeTv.setText(specInfoStr);
        }

        goodsViewHolder.mPriceTv.setText(Tools.formatRmbStr(entity.getPrice()));

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    class GoodsViewHolder extends RecyclerView.ViewHolder{

        private ImageView mImageIv;
        private TextView mNameTv;
        private TextView mCountTv;
        private TextView mSizeTv;
        private TextView mPriceTv;

        public GoodsViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageIv = itemView.findViewById(R.id.goods_iv_img);
            mNameTv = itemView.findViewById(R.id.goods_tv_name);
            mCountTv = itemView.findViewById(R.id.goods_tv_count);
            mSizeTv = itemView.findViewById(R.id.goods_tv_size);
            mPriceTv = itemView.findViewById(R.id.goods_tv_price);
        }
    }

}
