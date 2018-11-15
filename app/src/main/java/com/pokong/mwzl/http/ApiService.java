package com.pokong.mwzl.http;

import com.pokong.mwzl.data.DataResponseEntity;
import com.pokong.mwzl.data.bean.personal.LoginResponseBean;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by Zheng on 2018/4/22.
 */

public interface ApiService {

    @GET("/index.php")
    Observable<DataResponseEntity<LoginResponseBean>> doLogin(@QueryMap Map<String, String> params);

}
