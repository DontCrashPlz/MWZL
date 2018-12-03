package com.pokong.mwzl.login.login;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.pokong.library.base.BaseActivity;
import com.pokong.library.util.DialogFactory;
import com.pokong.library.util.Tools;
import com.pokong.mwzl.HomeActivity;
import com.pokong.mwzl.R;

/**
 * Created on 2018/11/16 9:43
 * User: Zheng
 * E-mail: zhengCH12138@163.com
 */
public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.View {

    private EditText mUserNameEt;

    private EditText mPasswordEt;

    private CheckBox mRememberCb;

    private TextView mForgetTv;

    private Button mLoginBtn;

    private AlertDialog mLoadingDialog;

    @Override
    protected void onDestroy() {
        //页面销毁前销毁LoadingDialog
        if (mLoadingDialog != null){
            if (mLoadingDialog.isShowing())
                mLoadingDialog.dismiss();
            mLoadingDialog = null;
        }
        super.onDestroy();
    }

    @Override
    public Context getRealContext() {
        return this;
    }

    @Override
    public int getContentViewRes() {
        return R.layout.activity_login;
    }

    @Override
    public void initToolbar() {
        View decorView = getWindow().getDecorView();
        int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
        decorView.setSystemUiVisibility(option);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
    }

    @Override
    public void initUI() {

        mUserNameEt = findViewById(R.id.login_et_username);
        mPasswordEt = findViewById(R.id.login_et_password);
        mRememberCb = findViewById(R.id.login_cb_remember);
        mForgetTv = findViewById(R.id.login_tv_forget);
        mLoginBtn = findViewById(R.id.login_btn_login);

        mLoadingDialog = DialogFactory.createLoadingDialog(getRealContext());
        mLoadingDialog.setCanceledOnTouchOutside(false);
        mLoadingDialog.setOnDismissListener(dialog -> clearNetWork());

        String lastUserName = mPresenter.getSharedUserName();
        if (!Tools.isBlank(lastUserName)){
            mUserNameEt.setText(lastUserName);
            boolean isRemember = mPresenter.getSharedRememberStatus();
            if (isRemember){
                String lastPassword = mPresenter.getSharedPassword();
                if (!Tools.isBlank(lastPassword)){
                    mRememberCb.setChecked(true);
                    mPasswordEt.setText(lastPassword);
                }
            }
        }
    }

    @Override
    protected LoginPresenter getRealPresenter() {
        return new LoginPresenter();
    }

    /**
     * 忘记密码点击事件
     * @param view
     */
    public void clickForget(View view){
        //todo 跳转到找回密码页面
    }

    /**
     * 登录按钮点击事件
     * @param view
     */
    public void clickLogin(View view){
        mPresenter.tryToLogin();
    }

    @Override
    public String getUserNameInput() {
        Editable editable = mUserNameEt.getText();
        if (editable == null) return null;
        return editable.toString().trim();
    }

    @Override
    public String getPasswordInput() {
        Editable editable = mPasswordEt.getText();
        if (editable == null) return null;
        return editable.toString().trim();
    }

    @Override
    public boolean getRememberCheckboxStatus() {
        return mRememberCb.isChecked();
    }

    @Override
    public void skipToHome() {
        Intent intent = new Intent(getRealContext(), HomeActivity.class);
        startActivity(intent);
        this.finish();
    }

    @Override
    public void showLoadingDialog() {
        if (mLoadingDialog != null && !mLoadingDialog.isShowing()){
            mLoadingDialog.show();
        }
    }

    @Override
    public void dismissLoadingDialog() {
        if (mLoadingDialog != null && mLoadingDialog.isShowing()){
            mLoadingDialog.dismiss();
        }
    }
}
