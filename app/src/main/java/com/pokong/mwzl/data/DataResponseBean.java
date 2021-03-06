package com.pokong.mwzl.data;

import com.pokong.library.util.Tools;

import java.io.Serializable;

/**
 * 所有JsonObject数据请求响应数据实体的基类
 * Created by Zheng on 2018/5/6.
 */

public class DataResponseBean<T> implements Serializable {

    private T data = null;//真实数据
    private String description;//错误描述，isSuccess = false时有效
    private String errorCode;//错误代码，isSuccess = false时有效
    private String status;//获取数据是否成功

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "DataResponseBean{" +
                "data=" + data +
                ", description='" + description + '\'' +
                ", errorCode='" + errorCode + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    /**
     * 判断数据请求是否成功
     * @return
     */
    public boolean isSuccess(){
        if (Tools.isBlank(getStatus())) return false;
        if ("success".equals(getStatus())) return true;
        return false;
    }

}
