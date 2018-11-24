package com.pokong.mwzl.data.bean.personal;

import com.pokong.mwzl.data.BaseResponseBean;

/**
 * Created on 2018/11/7 9:55
 * User: Zheng
 * E-mail: zhengCH12138@163.com
 * 登录响应实体类
 */
public class LoginResponseBean implements BaseResponseBean {
    private String apptoken;

    public String getApptoken() {
        return apptoken;
    }

    public void setApptoken(String apptoken) {
        this.apptoken = apptoken;
    }

    @Override
    public String toString() {
        return "LoginResponseBean{" +
                "apptoken='" + apptoken + '\'' +
                '}';
    }
}
//    {
//        "data":{
//        "apptoken":"bdcfb3c91b8db932952b1ea606e7"
//        },
//        "errorCode":"",
//        "description":"",
//        "status":"success"
//    }
