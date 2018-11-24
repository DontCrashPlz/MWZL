package com.pokong.mwzl.data;

import com.pokong.library.util.Tools;

/**
 * Created on 2018/11/23 14:04
 * User: Zheng
 * E-mail: zhengCH12138@163.com
 */
public class DataUtils {

    /**
     * 判断数据请求是否成功
     * @param responseBean
     * @return
     */
    public static boolean isDataRequestSuccess(DataResponseEntity responseBean){
        if (responseBean == null) return false;
        if (Tools.isBlank(responseBean.getStatus())) return false;
        if ("success".equals(responseBean.getStatus())) return true;
        return false;
    }

}
