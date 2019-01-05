package com.pokong.mwzl.shop.statistics;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.pokong.library.base.BaseActivity;
import com.pokong.library.base.BasePresenter;
import com.pokong.library.util.DialogFactory;
import com.pokong.library.util.LogUtils;
import com.pokong.mwzl.R;
import com.pokong.mwzl.app.MyApplication;
import com.pokong.mwzl.app.NetConstants;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;

/**
 * Created on 2018/12/10 18:27
 * User: Zheng
 * E-mail: zhengCH12138@163.com
 */
public class ShopStatisticsActivity extends BaseActivity {

    private Toolbar mToolbar;
    private WebView mWebview;
    private AlertDialog mLoadingDialog;

    @Override
    public Context getRealContext() {
        return this;
    }

    @Override
    public int getContentViewRes() {
        return R.layout.activity_shop_statistics;
    }

    @Override
    public void initToolbar() {
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("店铺统计");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public void initUI() {

        mLoadingDialog = DialogFactory.createLoadingDialog(getRealContext());
        mLoadingDialog.setCanceledOnTouchOutside(false);
        mLoadingDialog.show();

        mWebview = findViewById(R.id.webview);
        WebSettings settings = mWebview.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setSupportZoom(false);
        settings.setDisplayZoomControls(false);
        settings.setBuiltInZoomControls(false);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        mWebview.setInitialScale(80);
        mWebview.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView webView, int i) {
                super.onProgressChanged(webView, i);
                if (i== 100){
                    if (mLoadingDialog != null && mLoadingDialog.isShowing()) mLoadingDialog.dismiss();
                }
            }
        });
        String chartUrl= NetConstants.BASEURL + NetConstants.STATISTICS + "?appToken=" + MyApplication.getInstance().getAppToken();
        LogUtils.d("chartUrl", chartUrl);
        mWebview.loadUrl(chartUrl);
    }

    @Override
    protected BasePresenter getRealPresenter() {
        return null;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()== android.R.id.home)
            finish();
        return true;
    }

    @Override
    protected void onDestroy() {
        //页面销毁前销毁LoadingDialog
        if (mLoadingDialog != null){
            if (mLoadingDialog.isShowing())
                mLoadingDialog.dismiss();
            mLoadingDialog = null;
        }

        if (this.mWebview != null) {
            mWebview.destroy();
        }
        super.onDestroy();
    }
}
