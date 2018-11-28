package com.pokong.mwzl.data;

/**
 * Created by Zheng on 2018/10/8.
 */

public interface DataRequestCallback <T> {

    void onSuccessed(T t);

    void onFailed(String errorMsg);

}
