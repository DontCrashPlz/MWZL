package com.pokong.mwzl.data.source;

/**
 * 获取个人相关数据
 * Created by Zheng on 2018/9/28.
 */

import com.pokong.mwzl.data.DataRequestCallback;
import com.pokong.mwzl.data.bean.personal.LoginRequestBean;
import com.pokong.mwzl.data.bean.personal.LoginResponseBean;
import com.pokong.mwzl.data.bean.personal.NoticeDetailRequestBean;
import com.pokong.mwzl.data.bean.personal.NoticeDetailResponseBean;
import com.pokong.mwzl.data.bean.personal.NoticeListRequestBean;
import com.pokong.mwzl.data.bean.personal.NoticeListResponseBean;

import io.reactivex.disposables.Disposable;

/**
 * 获取个人相关数据
 * Created by Zheng on 2018/9/28.
 */

public interface PersonalInfoDataSource {

    /** 登录 */
    Disposable doLogin(LoginRequestBean paramsBean,
                       DataRequestCallback<LoginResponseBean> callback);

    /** 获取系统消息列表 */
    Disposable getSystemNoticeList(NoticeListRequestBean paramsBean,
                                   DataRequestCallback<NoticeListResponseBean> callback);

    /** 获取系统消息详情 */
    Disposable getSystemNoticeDetail(NoticeDetailRequestBean paramsBean,
                                     DataRequestCallback<NoticeDetailResponseBean> callback);

}

