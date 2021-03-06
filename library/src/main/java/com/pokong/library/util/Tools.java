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
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Collection;
import java.util.IllegalFormatException;
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

    /**
     * 格式化商品数量，也就是在数量前加上“×”
     * eg：1 -> ×1
     * @param object 参数必须是数字，否则就没有意义
     *               参数一般只有String和Integer两种
     * @return
     */
    public static String formatNumStr(Object object){
        if (object == null) return "×0";
        if (object instanceof String) {
            String num = (String) object;
            return "×" + num;
        }
        if (object instanceof Integer) {
            String num = String.valueOf(object);
            return "×" + num;
        }
        return "×0";
    }

    /**
     * @Deprecated -> formatRmbStr(Object object)
     * 格式化人民币字符串，也就是转换成“￥”开头的字符串
     * 人民币默认保留两位小数
     * 整数部分：integer part
     * 小数部分：decimal part
     * eg：0 -> ￥0.00
     * @param object 参数默认是数字形式，请不要蛋疼传入无意义的字符串
     *               暂不支持大写人民币
     *               String暂不支持四舍五入
     * @return
     */
    @Deprecated
    public static String formatRMBStr(Object object){
        if (object == null) return "￥0.00";
        if (object instanceof String){
            String money = (String) object;
            if (money.contains(".")){//包含小数点

                //只有一个小数点
                if (money.length() == 1){
                    return "RMB格式化错误-0";
                }

                //包含多个小数点
                int firstDotIndex = money.indexOf(".");
                int lastDotIndex = money.lastIndexOf(".");
                if (firstDotIndex != lastDotIndex){
                    return "RMB格式化错误-1";
                }

                if (money.startsWith(".")){//小数点开头
                    String decimalStr;
                    if (money.length() >= 3){
                        decimalStr = money.substring(0,2);
                    }else {
                        decimalStr = money + "0";
                    }
                    return "￥0" + decimalStr;
                }else if (money.endsWith(".")){//小数点结尾
                    return "￥" + money + "00";
                }else {//小数点在中间
                    String[] moneyStrs = money.split(".");
                    String integerPart = moneyStrs[0];
                    String decimalPart = moneyStrs[1];
//                    if (decimalPart.length() >= 3){
//                        int firstIndex = Integer.valueOf("" + decimalPart.charAt(0));
//                        int secondIndex = Integer.valueOf("" + decimalPart.charAt(1));
//                        int thirdIndex = Integer.valueOf("" + decimalPart.charAt(2));
//                        if (thirdIndex >= 5) secondIndex += 1;
//                        return "￥" + integerPart + "." + firstDotIndex + secondIndex;
//                    }
                    if (decimalPart.length() >= 2){
                        decimalPart = decimalPart.substring(0, 1);
                        return "￥" + integerPart + "." + decimalPart;
                    }else {
                        return "￥" + integerPart + "." + decimalPart + "0";
                    }
                }
            }else {//不包含小数点
                return "￥" + money + ".00";
            }
        }
        if (object instanceof Integer){
            int money = (int) object;
            return "￥" + money + ".00";
        }
        if (object instanceof Float){
            float money = (float) object;
            BigDecimal b = new BigDecimal(money);
            //表明四舍五入，保留两位小数
            money = b.setScale(2,  RoundingMode.HALF_UP).floatValue();
            return "￥" + money;
        }
        if (object instanceof Double){
            double money = (double) object;
            BigDecimal b = new BigDecimal(money);
            //表明四舍五入，保留两位小数
            money = b.setScale(2,  BigDecimal.ROUND_HALF_UP).doubleValue();
            return "￥" + money;
        }
        return "￥0.00";
    }

    /**
     * 格式化人民币字符串，也就是转换成“￥”开头的字符串
     * 人民币默认保留两位小数
     * @param object
     * @return
     */
    public static String formatRmbStr(Object object){
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        String rmbStr = "0.00";
        if (object instanceof String){
            double objDouble = Double.valueOf((String) object);
            rmbStr = decimalFormat.format(objDouble);
        }else {
            try{
                rmbStr = decimalFormat.format(object);
            }catch (IllegalArgumentException exception){
                exception.printStackTrace();
            }
        }
        return "￥" + rmbStr;
    }

    /**
     * 生成订单序号，1000取余，格式化为四位字符串
     * @param id
     * @return
     */
    public static String formatId(long id){
        id = id % 1000;
        DecimalFormat decimalFormat = new DecimalFormat("0000");
        return decimalFormat.format(id);
    }

    /**
     * 验证是否为有效用户名
     * @param userName
     * @return
     */
    public static boolean isValidUserName(String userName){
        if (userName == null) return false;
        if ("".equals(userName)) return false;
        if (userName.length() < 6) return false;
        return true;
    }

    /**
     * 验证是否为有效密码
     * @param password
     * @return
     */
    public static boolean isValidPassword(String password){
        if (password == null) return false;
        if ("".equals(password)) return false;
        if (password.length() < 6) return false;
        return true;
    }

    /**
     * 判断一个字符是否是汉字
     * PS：中文汉字的编码范围：[\u4e00-\u9fa5]
     *
     * @param c 需要判断的字符
     * @return 是汉字(true), 不是汉字(false)
     */
    public static boolean isChineseChar(char c) {
        return String.valueOf(c).matches("[\u4e00-\u9fa5]");
    }

}
