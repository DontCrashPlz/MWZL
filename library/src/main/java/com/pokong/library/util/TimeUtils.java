package com.pokong.library.util;

import java.util.Calendar;
import java.util.TimeZone;

/**
 * 时间工具类
 * Created by Zheng on 2018/6/6.
 */

public class TimeUtils {

    public static String getUSATime(){
        final Calendar c = Calendar.getInstance();
        String mYear;
        String mMonth;
        String mDay;
        String mWay;
        c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        mYear = String.valueOf(c.get(Calendar.YEAR)); // 获取当前年份
        mMonth = String.valueOf(c.get(Calendar.MONTH) + 1);// 获取当前月份
        mDay = String.valueOf(c.get(Calendar.DAY_OF_MONTH));// 获取当前月份的日期号码
        mWay = String.valueOf(c.get(Calendar.DAY_OF_WEEK));
        if("1".equals(mWay)){
            mWay ="日";
        }else if("2".equals(mWay)){
            mWay ="一";
        }else if("3".equals(mWay)){
            mWay ="二";
        }else if("4".equals(mWay)){
            mWay ="三";
        }else if("5".equals(mWay)){
            mWay ="四";
        }else if("6".equals(mWay)){
            mWay ="五";
        }else if("7".equals(mWay)){
            mWay ="六";
        }
        return mMonth + "/" + mDay +"/" + mYear + " 周"+mWay;
    }
}
