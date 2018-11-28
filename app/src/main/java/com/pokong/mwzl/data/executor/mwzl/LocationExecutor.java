package com.pokong.mwzl.data.executor.mwzl;

import com.pokong.library.util.Tools;
import com.pokong.mwzl.data.BaseExecutor;
import com.pokong.mwzl.data.DataResponseBean;
import com.pokong.mwzl.data.bean.mwzl.LocationRequestBean;
import com.pokong.mwzl.data.bean.mwzl.LocationResponseBean;
import com.pokong.mwzl.http.ApiService;
import com.pokong.mwzl.http.ErrorDataResponseFactory;

import java.util.HashMap;

import io.reactivex.Observable;

/**
 * Created by Zheng on 2018/10/19.
 * 网络请求执行类--地址校对
 */

public class LocationExecutor extends BaseExecutor<LocationRequestBean, String> {

    private final String PARAMKEY_APPTOKEN = "appToken";
    private final String PARAMKEY_X = "xLocation";
    private final String PARAMKEY_Y = "yLocation";

    private HashMap<String, String> paramsMap;

    public LocationExecutor(ApiService apiService, LocationRequestBean paramsBean) {
        super(apiService, paramsBean);
        paramsMap = new HashMap<>();
    }

    @Override
    public Observable<DataResponseBean<String>> execute() {

        String appToken = paramsBean.getAppToken();
        if (Tools.isBlank(appToken)){
            return Observable.create(emitter -> emitter.onNext(ErrorDataResponseFactory.create("appToken无效")));
        }
        paramsMap.put(PARAMKEY_APPTOKEN, paramsBean.getAppToken());

        double xLocation = paramsBean.getxLocation();
        if (xLocation == 0){
            return Observable.create(emitter -> emitter.onNext(ErrorDataResponseFactory.create("xLocation无效")));
        }
        paramsMap.put(PARAMKEY_X, String.valueOf(xLocation));

        double yLocation = paramsBean.getyLocation();
        if (yLocation == 0){
            return Observable.create(emitter -> emitter.onNext(ErrorDataResponseFactory.create("yLocation无效")));
        }
        paramsMap.put(PARAMKEY_Y, String.valueOf(yLocation));

        return apiService.uploadLocation(paramsMap);
    }
}
