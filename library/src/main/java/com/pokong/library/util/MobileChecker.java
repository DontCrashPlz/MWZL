package com.pokong.library.util;

import java.util.regex.PatternSyntaxException;

/**
 * Created on 2018/12/19 10:29
 * User: Zheng
 * E-mail: zhengCH12138@163.com
 * 电话号码校验器
 */
public class MobileChecker {

    /**
     * 校验手机号字符串是否有效
     * 默认认为11位是有效字符串
     * @param mobileStr
     * @return
     */
    public static boolean isMobileStrValid(String mobileStr){

        if (mobileStr == null) return false;

        try {
            mobileStr = mobileStr.replaceAll("-", "");
        }catch (PatternSyntaxException e){
            e.printStackTrace();
        }

        mobileStr = mobileStr.trim();

        return mobileStr.length() == 11;

    }

}
