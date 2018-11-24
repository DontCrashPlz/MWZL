package com.pokong.mwzl.http;

import com.google.gson.JsonParseException;
import com.pokong.mwzl.data.BaseResponseBean;
import com.pokong.mwzl.data.DataResponseEntity;

import org.json.JSONException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.text.ParseException;

class CustomExceptionConverter {

    /**
     * 未知错误
     */
    private static final int UNKNOWN = 1000;

    /**
     * 解析错误
     */
    private static final int PARSE_ERROR = 1001;

    /**
     * 网络错误
     */
    private static final int NETWORK_ERROR = 1002;

    /**
     * 协议错误
     */
    private static final int HTTP_ERROR = 1003;

    /**
     * 生成网络请求错误时onErrorReturn()发射的特殊数据
     * @param e
     * @param <T>
     * @return
     */
    static <T extends BaseResponseBean> DataResponseEntity<T> convertException(Throwable e) {
        DataResponseEntity<T> entity = new DataResponseEntity<>();
        entity.setStatus("error");
        if (e instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof ParseException) {
            //解析错误
            entity.setErrorCode(Integer.toString(PARSE_ERROR));
            entity.setDescription("数据解析失败");
            return entity;
        } else if (e instanceof ConnectException) {
            //网络错误
            entity.setErrorCode(Integer.toString(NETWORK_ERROR));
            entity.setDescription("网络连接失败，请检查网络");
            return entity;
        } else if (e instanceof UnknownHostException
                || e instanceof SocketTimeoutException) {
            //连接错误
            entity.setErrorCode(Integer.toString(NETWORK_ERROR));
            entity.setDescription("网络连接失败，请检查网络");
            return entity;
        } else {
            //未知错误
            entity.setErrorCode(Integer.toString(UNKNOWN));
            entity.setDescription(e.getMessage());
            return entity;
        }
    }
}