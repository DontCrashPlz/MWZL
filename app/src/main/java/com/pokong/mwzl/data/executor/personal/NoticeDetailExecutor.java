package com.pokong.mwzl.data.executor.personal;

import com.pokong.mwzl.data.DataResponseBean;
import com.pokong.mwzl.data.bean.personal.NoticeDetailRequestBean;
import com.pokong.mwzl.data.bean.personal.NoticeDetailResponseBean;
import com.pokong.mwzl.data.BaseExecutor;
import com.pokong.mwzl.http.ApiService;

import io.reactivex.Observable;

/**
 * Created on 2018/11/7 14:31
 * User: Zheng
 * E-mail: zhengCH12138@163.com
 * 网络请求执行类--获取系统消息详情
 */
public class NoticeDetailExecutor extends BaseExecutor<NoticeDetailRequestBean, NoticeDetailResponseBean> {

    public NoticeDetailExecutor(ApiService apiService, NoticeDetailRequestBean paramsBean) {
        super(apiService, paramsBean);
    }

    @Override
    public Observable<DataResponseBean<NoticeDetailResponseBean>> execute() {
        return null;
    }
}
