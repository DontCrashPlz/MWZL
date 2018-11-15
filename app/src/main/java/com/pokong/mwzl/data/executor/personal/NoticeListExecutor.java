package com.pokong.mwzl.data.executor.personal;

import com.pokong.mwzl.data.DataResponseEntity;
import com.pokong.mwzl.data.bean.personal.NoticeListRequestBean;
import com.pokong.mwzl.data.bean.personal.NoticeListResponseBean;
import com.pokong.mwzl.data.BaseExecutor;
import com.pokong.mwzl.http.ApiService;

import io.reactivex.Observable;

/**
 * Created on 2018/11/7 14:30
 * User: Zheng
 * E-mail: zhengCH12138@163.com
 * 网络请求执行类--获取系统消息列表
 */
public class NoticeListExecutor extends BaseExecutor<NoticeListRequestBean, NoticeListResponseBean> {

    public NoticeListExecutor(ApiService apiService, NoticeListRequestBean paramsBean) {
        super(apiService, paramsBean);
    }

    @Override
    public Observable<DataResponseEntity<NoticeListResponseBean>> execute() {
        return null;
    }
}
