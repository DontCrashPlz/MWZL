package com.pokong.mwzl.data.executor.mwzl;

import com.pokong.mwzl.data.BaseDataResponseEntity;
import com.pokong.mwzl.data.BaseExecutor;
import com.pokong.mwzl.data.DataResponseEntity;
import com.pokong.mwzl.data.bean.mwzl.LocationRequestBean;
import com.pokong.mwzl.data.bean.mwzl.LocationResponseBean;
import com.pokong.mwzl.http.ApiService;

import java.util.HashMap;

import io.reactivex.Observable;

/**
 * Created by Zheng on 2018/10/19.
 * 网络请求执行类--地址校对
 */

public class LocationExecutor extends BaseExecutor<LocationRequestBean, LocationResponseBean> {

    public LocationExecutor(ApiService apiService, LocationRequestBean paramsBean) {
        super(apiService, paramsBean);
    }

    @Override
    public Observable<DataResponseEntity<LocationResponseBean>> execute() {
        return apiService.uploadLocation(new HashMap<>());
    }
}
