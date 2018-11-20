package com.pokong.library.util;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.os.Looper;

import com.pokong.library.eventbus.UpdateFailedEvent;

import org.greenrobot.eventbus.EventBus;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created on 2018/11/19 19:02
 * User: Zheng
 * E-mail: zhengCH12138@163.com
 */
public class UpdateUtils {

    public static void downLoadApk(final Context context, final String url) {
        //进度条对话框
        final ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setProgressStyle(android.app.ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setMessage("正在下载更新");
        progressDialog.show();
        new Thread() {
            @Override
            public void run() {
                try {
                    File file = getFileFromServer(url, progressDialog);
                    if (file != null) {
                        installApk(context, file);
                    } else {
                        //安装包下载失败，将失败事件发送到SplashActivity
                        EventBus.getDefault().post(new UpdateFailedEvent());
                    }
                    progressDialog.dismiss(); //结束掉进度条对话框
                } catch (Exception e) {
                    e.printStackTrace();
                    //安装包下载失败，将失败事件发送到SplashActivity
                    EventBus.getDefault().post(new UpdateFailedEvent());
                    progressDialog.dismiss(); //结束掉进度条对话框
                }
            }
        }.start();
    }

    public static void installApk(Context context, File file) {
        Intent intent = new Intent();
        //执行动作
        intent.setAction(Intent.ACTION_VIEW);
        //执行的数据类型
        intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        context.startActivity(intent);
    }

    public static File getFileFromServer(String path, ProgressDialog pd) throws Exception {
        //如果相等的话表示当前的sdcard挂载在手机上并且是可用的
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            URL url = new URL(path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);
            //获取到文件的大小
            //pd.setMax(conn.getContentLength());
            int contentLength = conn.getContentLength();
            pd.setMax(100);

            File file = new File(Environment.getExternalStorageDirectory(), "美味滋乐.apk");
            try (InputStream iss = conn.getInputStream();
                 FileOutputStream fos = new FileOutputStream(file);
                 BufferedInputStream bis = new BufferedInputStream(iss)) {
                byte[] buffer = new byte[1024];
                int len;
                int total = 0;
                while ((len = bis.read(buffer)) != -1) {
                    fos.write(buffer, 0, len);
                    for (int i = 0; i < 10000; i++) {
                    }
                    total += len;
                    //获取当前下载量
                    pd.setProgress((total * 100) / contentLength);
                }
            }
            return file;
        } else {
            return null;
        }
    }

}
