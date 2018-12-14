package com.pokong.library.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created on 2018/12/10 19:26
 * User: Zheng
 * E-mail: zhengCH12138@163.com
 * 时间校验器
 */
public class TimeChecker {

    /**
     * 校验yyyy-MM-dd HH:mm:ss格式的时间字符串是否有效
     * @param timeStr
     * @return
     */
    public static boolean isTimeStrValid(String timeStr){
        if (timeStr == null) return false;
        //if (timeStr.length() != 19) return false;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(timeStr);
            return true;
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
    }

}
