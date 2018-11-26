package com.pokong.mwzl.data.executor.business;

import com.pokong.library.util.Tools;
import com.pokong.mwzl.data.DataResponseEntity;
import com.pokong.mwzl.data.bean.business.ShopInfoRequestBean;
import com.pokong.mwzl.data.bean.business.ShopInfoResponseBean;
import com.pokong.mwzl.data.BaseExecutor;
import com.pokong.mwzl.http.ApiService;
import com.pokong.mwzl.http.ErrorDataResponseFactory;

import io.reactivex.Observable;

/**
 * Created on 2018/11/7 17:21
 * User: Zheng
 * E-mail: zhengCH12138@163.com
 * 网络请求执行类--获取店铺基本信息
 */
public class ShopInfoExecutor extends BaseExecutor<ShopInfoRequestBean, ShopInfoResponseBean> {

    public ShopInfoExecutor(ApiService apiService, ShopInfoRequestBean paramsBean) {
        super(apiService, paramsBean);
    }

    @Override
    public Observable<DataResponseEntity<ShopInfoResponseBean>> execute() {
        String appToken = paramsBean.getAppToken();
        if (Tools.isBlank(appToken)){
            return Observable.create(emitter -> emitter.onNext(ErrorDataResponseFactory.create("appToken无效")));
        }
        return apiService.getShopInfo(appToken);
    }
}
