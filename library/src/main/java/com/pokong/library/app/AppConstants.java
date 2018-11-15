package com.pokong.library.app;

import android.content.Context;

import com.pokong.library.util.ToastUtils;

import java.io.File;

/**
 * Created on 2018/11/13 17:31
 * User: Zheng
 * E-mail: zhengCH12138@163.com
 * 定义Application中的一些常量
 */
public class AppConstants {

    /**
     * 获取文件储存路径
     */
    public static String getFileSavePath(Context context){
        File fileSaveFile = context.getExternalFilesDir(null);
        if (fileSaveFile == null || !(fileSaveFile.exists())){
            ToastUtils.showShortToast(context, "获取文件储存路径失败");
            return null;
        }
        return fileSaveFile.getAbsolutePath() + "/myFile";
    }

    /**
     * 获取缓存储存路径
     */
    public static String getCacheSavePath(Context context){
        File cacheSaveFile = context.getExternalCacheDir();
        if (cacheSaveFile == null || !(cacheSaveFile.exists())){
            ToastUtils.showShortToast(context, "获取缓存储存路径失败");
            return null;
        }
        return cacheSaveFile.getAbsolutePath() + "/myCache";
    }

    /**
     * 获取崩溃日志储存路径
     */
    public static String getCrashSavePath(Context context){
        File crashSaveFile = context.getExternalFilesDir(null);
        if (crashSaveFile == null || !(crashSaveFile.exists())){
            ToastUtils.showShortToast(context, "获取文件储存路径失败");
            return null;
        }
        return crashSaveFile.getAbsolutePath() + "/myCrash";
    }

}
