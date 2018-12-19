package com.pokong.mwzl.data.executor.mwzl;

import com.pokong.library.util.Tools;
import com.pokong.mwzl.data.BaseExecutor;
import com.pokong.mwzl.data.DataResponseBean;
import com.pokong.mwzl.data.bean.business.ShopInfoRequestBean;
import com.pokong.mwzl.data.bean.business.ShopInfoResponseBean;
import com.pokong.mwzl.data.bean.mwzl.MemberInfoRequestBean;
import com.pokong.mwzl.data.bean.mwzl.MemberInfoResponseBean;
import com.pokong.mwzl.http.ApiService;
import com.pokong.mwzl.http.ErrorDataResponseFactory;

import io.reactivex.Observable;

/**
 * Created on 2018/11/7 17:21
 * User: Zheng
 * E-mail: zhengCH12138@163.com
 * 网络请求执行类--获取会员信息
 */
public class MemberInfoExecutor extends BaseExecutor<MemberInfoRequestBean, MemberInfoResponseBean> {

    public MemberInfoExecutor(ApiService apiService, MemberInfoRequestBean paramsBean) {
        super(apiService, paramsBean);
    }

    @Override
    public Observable<DataResponseBean<MemberInfoResponseBean>> execute() {
        String appToken = paramsBean.getAppToken();
        if (Tools.isBlank(appToken)){
            return Observable.create(emitter -> emitter.onNext(ErrorDataResponseFactory.create("appToken无效")));
        }

        String mobile = paramsBean.getMobile();
        if (Tools.isBlank(mobile)){
            return Observable.create(emitter -> emitter.onNext(ErrorDataResponseFactory.create("请输入有效电话号码")));
        }

        return apiService.getMemberInfo(appToken, mobile);
    }
}
