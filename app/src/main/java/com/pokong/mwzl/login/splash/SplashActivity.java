package com.pokong.mwzl.login.splash;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;

import com.pokong.library.base.BaseActivity;
import com.pokong.library.util.ToastUtils;

/**
 * Created on 2018/11/15 16:16
 * User: Zheng
 * E-mail: zhengCH12138@163.com
 */
public class SplashActivity extends BaseActivity<SplashPresenter> implements SplashContract.View {

    //private static final int MY_PERMISSIONS_REQUEST_CAMERA = 1;//照相机权限申请码
    private static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 1;//从内存卡读取数据权限申请码
    private static final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 2;//向内存卡写入数据权限申请码

    @Override
    public Context getRealContext() {
        return this;
    }

    @Override
    public int getContentViewRes() {
        return 0;
    }

    @Override
    public void initToolbar() {

    }

    @Override
    public void initUI() {
        //TODO 申请权限
        checkPermissions();
        //TODO 判断更新

        //TODO 判断自动登录
    }

    @Override
    protected SplashPresenter getRealPresenter() {
        return new SplashPresenter();
    }

    private void checkPermissions() {
        //如果没有权限
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    100);
        } else {

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 100) {

        }

    }

    @Override
    public void gotoLogin() {

    }

    @Override
    public void autoLogin() {

    }
}
