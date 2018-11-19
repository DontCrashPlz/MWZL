package com.pokong.library.util;

import android.app.Dialog;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Map;

/**
 * 全局工具类
 * 这里定义一些全局使用的工具方法
 * Created by Zheng on 2016/5/4.
 */
public class Tools {

    /**
     * 获取当前程序的版本号
     */
    public static String getVersionName(Context context) {
        //获取packagemanager的实例
        PackageManager packageManager = context.getPackageManager();
        //getPackageName()是你当前类的包名，0代表是获取版本信息
        PackageInfo packInfo;
        try{
            packInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            ToastUtils.showShortToast(context, "获取应用版本号失败");
            return "unknow";
        }
        return packInfo.versionName;
    }

    /**
     * 显示自定义弹窗
     */
    public static void showCustomDialog(Context context, int ResourcesId, int themeId){
        View view = LayoutInflater.from(context).inflate(ResourcesId, null);
        // 设置style 控制默认dialog带来的边距问题
        final Dialog dialog = new Dialog(context, themeId);
        dialog.setContentView(view);
        dialog.show();

        // 设置相关位置，一定要在 show()之后
        Window window = dialog.getWindow();
        window.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams params = window.getAttributes();
        params.gravity = Gravity.CENTER;
        window.setAttributes(params);
    }

    /**
     * 格式化float数值
     * @param floatNum 要格式化的float
     * @param scaleNum 要保留几位小数，默认2位
     * @return
     */
    public static String formatFloat(float floatNum, int scaleNum){
        LogUtils.d("formatFloat before", String.valueOf(floatNum));
        int realScaleNum = 2;
        if (scaleNum > 0) realScaleNum = scaleNum;
        BigDecimal b = new BigDecimal(floatNum);
        //表明四舍五入，保留两位小数
        floatNum = b.setScale(realScaleNum,  BigDecimal.ROUND_HALF_UP).floatValue();
        LogUtils.d("formatFloat after", String.valueOf(floatNum));
        return String.valueOf(floatNum);
    }

    /**
     * 判断参数是否为Null或者""、"  ",如果为数字将判断其是否为0
     *
     * @param object
     *            ,可以是常用的Collection、Set、Map、String、Long、Integer类型，其他的类型会直接返回true;
     * @return true/false
     */
    public static boolean isBlank(Object object) {
        if (object == null) {
            return true;
        }
        if (object instanceof Collection) {
            return ((Collection<?>) object).size() == 0;
        }
        if (object instanceof Map) {
            return ((Map<?,?>) object).size() == 0;
        }
        if (object instanceof String) {
            return "".equals(((String) object).trim());
        }
        if (object instanceof Integer) {
            return (Integer) object == 0;
        }
        if (object instanceof Long) {
            return (Long) object == 0;
        }
        return false;
    }

}
