package com.pokong.mwzl.data;

/**
 * Created by Zheng on 2018/10/8.
 */

public interface DataRequestCallback <T extends BaseResponseBean> {

    void onSuccessed(T t);

    void onFailed(String errorMsg);

}
