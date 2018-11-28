package com.pokong.mwzl.data.executor.business;

import com.pokong.mwzl.data.DataResponseBean;
import com.pokong.mwzl.data.bean.business.CommentGoodsListRequestBean;
import com.pokong.mwzl.data.bean.business.CommentGoodsListResponseBean;
import com.pokong.mwzl.data.BaseExecutor;
import com.pokong.mwzl.http.ApiService;

import io.reactivex.Observable;

/**
 * Created on 2018/11/7 17:22
 * User: Zheng
 * E-mail: zhengCH12138@163.com
 * 网络请求执行类--获取全部评价商品列表
 */
public class CommentGoodsListExecutor extends BaseExecutor<CommentGoodsListRequestBean, CommentGoodsListResponseBean> {

    public CommentGoodsListExecutor(ApiService apiService, CommentGoodsListRequestBean paramsBean) {
        super(apiService, paramsBean);
    }

    @Override
    public Observable<DataResponseBean<CommentGoodsListResponseBean>> execute() {
        return null;
    }
}
