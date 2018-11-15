package com.pokong.library.base;

import android.app.Application;

import com.pokong.library.app.ACache;
import com.pokong.library.app.ActivityManager;
import com.pokong.library.app.AppConstants;
import com.pokong.library.app.MyUncatchExceptionHandler;
import com.pokong.library.app.ServiceManager;

import java.io.File;

/**
 * Created by Zheng on 2018/4/14.
 */

public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        //init MyUncatchExceptionHandler
        MyUncatchExceptionHandler mUncatchExceptionHandler= MyUncatchExceptionHandler.getInstance();
        mUncatchExceptionHandler.init(this, AppConstants.getCrashSavePath(this));

        // 初始化ACache，可以考虑在此初始化时将缓存地址更改为磁盘SD卡(网络请求的缓存默认放在data/data目录下了)
        //此处初始化Acache创建放置图片缓存的文件夹
        File cacheDir = new File(AppConstants.getCacheSavePath(this));
        ACache.get(cacheDir, 20 * 1024 * 1024, Integer.MAX_VALUE);

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
