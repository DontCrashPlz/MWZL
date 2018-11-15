package com.pokong.mwzl.data;

import io.reactivex.Observable;

/**
 * 数据获取执行者
 * Created by Zheng on 2018/10/19.
 */

public interface DataExecutor<T extends BaseResponseBean> {

    /**
     * 执行数据请求的方法
     * @return
     */
    Observable<DataResponseEntity<T>> execute();

}
