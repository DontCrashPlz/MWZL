package com.pokong.mwzl.login.splash;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.pokong.library.base.BaseActivity;
import com.pokong.library.util.ToastUtils;
import com.pokong.library.util.Tools;
import com.pokong.library.util.UpdateUtils;
import com.pokong.mwzl.HomeActivity;
import com.pokong.mwzl.R;
import com.pokong.mwzl.data.bean.UpdateInfoEntity;
import com.pokong.library.eventbus.UpdateFailedEvent;
import com.pokong.mwzl.login.login.LoginActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created on 2018/11/15 16:16
 * User: Zheng
 * E-mail: zhengCH12138@163.com
 */
public class SplashActivity extends BaseActivity<SplashPresenter> implements SplashContract.View {

    //private static final int MY_PERMISSIONS_REQUEST_CAMERA = 1;//照相机权限申请码
    //private static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 1;//从内存卡读取数据权限申请码
    //private static final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 2;//向内存卡写入数据权限申请码

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public Context getRealContext() {
        return this;
    }

    @Override
    public int getContentViewRes() {
        return R.layout.activity_splash;
    }

    @Override
    public void initToolbar() {

    }

    @Override
    public void initUI() {
        EventBus.getDefault().register(this);
        //TODO 1.申请权限
        //TODO 2.判断更新
        //TODO 3.判断自动登录
        checkPermissions();
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
        } else {//如果有权限，检查更新
            mPresenter.checkUpdate();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 100) {
            mPresenter.checkUpdate();
        }
    }

    @Override
    public void skipToLogin() {
        Intent intent = new Intent(getRealContext(), LoginActivity.class);
        startActivity(intent);
        this.finish();
    }

    @Override
    public void skipToHome() {
        Intent intent = new Intent(getRealContext(), HomeActivity.class);
        startActivity(intent);
        this.finish();
    }

    @Override
    public void showUpdateDialog(UpdateInfoEntity updateInfoEntity) {
        View view = LayoutInflater.from(getRealContext()).inflate(R.layout.layout_dialog_update, null);
        // 设置style 控制默认dialog带来的边距问题
        AlertDialog.Builder builder = new AlertDialog.Builder(getRealContext(),R.style.custom_dialog_no_titlebar);
        builder.setView(view).setCancelable(false);
        AlertDialog dialog = builder.create();

        TextView descriptionText = view.findViewById(R.id.dialog_update_text_description);
        TextView cancelBtn = view.findViewById(R.id.dialog_update_button_cancel);
        TextView updateBtn = view.findViewById(R.id.dialog_update_button_update);

        if (!Tools.isBlank(updateInfoEntity.getDescription()))
            descriptionText.setText(updateInfoEntity.getDescription());
        cancelBtn.setOnClickListener(v -> {
            dialog.dismiss();
            mPresenter.checkAutoLogin();
        });
        updateBtn.setOnClickListener(v -> {
            dialog.dismiss();
            UpdateUtils.downLoadApk(getRealContext(), updateInfoEntity.getUrl());
        });

        dialog.show();
    }

    /**
     * 接收安装包下载失败事件
     * UpdateUtils -> downLoadApk(Context context, String url)
     * lines-> 43,49
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void updateFailed(UpdateFailedEvent event){
        ToastUtils.showShortToast(getRealContext(), "安装包下载失败");
        mPresenter.checkAutoLogin();
    }

}
