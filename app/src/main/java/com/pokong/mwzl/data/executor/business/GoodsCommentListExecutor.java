package com.pokong.mwzl.data.executor.business;

import com.pokong.mwzl.data.DataResponseEntity;
import com.pokong.mwzl.data.bean.business.GoodsCommentListRequestBean;
import com.pokong.mwzl.data.bean.business.GoodsCommentListResponseBean;
import com.pokong.mwzl.data.BaseExecutor;
import com.pokong.mwzl.http.ApiService;

import io.reactivex.Observable;

/**
 * Created on 2018/11/7 17:22
 * User: Zheng
 * E-mail: zhengCH12138@163.com
 * 网络请求执行类--获取指定商品的评价列表
 */
public class GoodsCommentListExecutor extends BaseExecutor<GoodsCommentListRequestBean, GoodsCommentListResponseBean> {

    public GoodsCommentListExecutor(ApiService apiService, GoodsCommentListRequestBean paramsBean) {
        super(apiService, paramsBean);
    }

    @Override
    public Observable<DataResponseEntity<GoodsCommentListResponseBean>> execute() {
        return null;
    }
}
