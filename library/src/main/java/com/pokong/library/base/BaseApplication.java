package com.pokong.library.base;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.pokong.library.app.ACache;
import com.pokong.library.app.ActivityManager;
import com.pokong.library.app.AppConstants;
import com.pokong.library.app.MyUncatchExceptionHandler;
import com.pokong.library.app.ServiceManager;
import com.pokong.library.util.LogUtils;
import com.tencent.smtt.sdk.QbSdk;

import java.io.File;

/**
 * Created by Zheng on 2018/4/14.
 */

public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        //搜集本地tbs内核信息并上报服务器，服务器返回结果决定使用哪个内核。
        QbSdk.PreInitCallback cb = new QbSdk.PreInitCallback() {

            @Override
            public void onViewInitFinished(boolean arg0) {
                // TODO Auto-generated method stub
                //x5內核初始化完成的回调，为true表示x5内核加载成功，否则表示x5内核加载失败，会自动切换到系统内核。
                LogUtils.d("app", " onViewInitFinished is " + arg0);
            }

            @Override
            public void onCoreInitFinished() {
                // TODO Auto-generated method stub
            }
        };
        //x5内核初始化接口
        QbSdk.initX5Environment(getApplicationContext(),  cb);

        //init MyUncatchExceptionHandler
        MyUncatchExceptionHandler mUncatchExceptionHandler= MyUncatchExceptionHandler.getInstance();
        mUncatchExceptionHandler.init(this, AppConstants.getCrashSavePath(this));

        // 初始化ACache，可以考虑在此初始化时将缓存地址更改为磁盘SD卡(网络请求的缓存默认放在data/data目录下了)
        //此处初始化Acache创建放置图片缓存的文件夹
        File cacheDir = new File(AppConstants.getCacheSavePath(this));
        ACache.get(cacheDir, 20 * 1024 * 1024, Integer.MAX_VALUE);

    }

    /**
     * 获取当前程序的版本号
     */
    public static String getVersionName(Context context) throws Exception{
        //获取packagemanager的实例
        PackageManager packageManager = context.getPackageManager();
        //getPackageName()是你当前类的包名，0代表是获取版本信息
        PackageInfo packInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
        return packInfo.versionName;
    }

    /**
     * 退出应用程序
     */
    public void AppExit() {
        ActivityManager.getInstance().finishAll();
        ServiceManager.getInstance().finishAll();
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }

}
