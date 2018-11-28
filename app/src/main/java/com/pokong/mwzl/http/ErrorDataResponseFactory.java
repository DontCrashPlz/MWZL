package com.pokong.mwzl.http;

import com.pokong.library.util.Tools;
import com.pokong.mwzl.data.DataResponseBean;

/**
 * Created on 2018/11/26 11:22
 * User: Zheng
 * E-mail: zhengCH12138@163.com
 */
public class ErrorDataResponseFactory {

    public static <T> DataResponseBean<T> create(String description){
        DataResponseBean<T> entity = new DataResponseBean<>();
        entity.setStatus("error");
        if (!Tools.isBlank(description))
            entity.setDescription(description);
        return entity;
    }

    public static <T> DataResponseBean<T> create(String errorCode, String description){
        DataResponseBean<T> entity = new DataResponseBean<>();
        entity.setStatus("error");
        if (!Tools.isBlank(errorCode))
            entity.setErrorCode(errorCode);
        if (!Tools.isBlank(description))
            entity.setDescription(description);
        return entity;
    }

}
