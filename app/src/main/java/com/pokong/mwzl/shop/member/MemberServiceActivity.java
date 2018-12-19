package com.pokong.mwzl.shop.member;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.pokong.library.base.BaseActivity;
import com.pokong.library.util.DialogFactory;
import com.pokong.library.util.MobileChecker;
import com.pokong.library.util.ToastUtils;
import com.pokong.library.util.Tools;
import com.pokong.mwzl.R;
import com.pokong.mwzl.data.DataRequestCallback;
import com.pokong.mwzl.data.bean.mwzl.MemberInfoResponseBean;

/**
 * Created on 2018/12/18 14:17
 * User: Zheng
 * E-mail: zhengCH12138@163.com
 */
public class MemberServiceActivity extends BaseActivity<MemberServicePresenter> implements MemberServiceContract.View {

    private EditText mSearchMobileEt;
    private ImageView mSearchIv;

    private TextView mNicknameTv;
    private TextView mMobileTv;
    private TextView mBalanceTv;
    private TextView mIntegralTv;

    private Button mPayBtn;

    @Override
    public Context getRealContext() {
        return this;
    }

    @Override
    public int getContentViewRes() {
        return R.layout.activity_shop_member;
    }

    @Override
    public void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null){
            getSupportActionBar().setTitle("会员消费");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public void initUI() {
        mSearchMobileEt = findViewById(R.id.member_et_mobile);
        mSearchMobileEt.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH){
                clickSearch(v);
            }
            return false;
        });
        mSearchIv = findViewById(R.id.member_iv_search);

        mNicknameTv = findViewById(R.id.member_tv_nickname);
        mNicknameTv.setText("");
        mMobileTv = findViewById(R.id.member_tv_mobile);
        mMobileTv.setText("");
        mBalanceTv = findViewById(R.id.member_tv_balance);
        mBalanceTv.setText("");
        mIntegralTv = findViewById(R.id.member_tv_integral);
        mIntegralTv.setText("");

        mPayBtn = findViewById(R.id.member_btn_pay);
        mPayBtn.setVisibility(View.GONE);
    }

    @Override
    protected MemberServicePresenter getRealPresenter() {
        return new MemberServicePresenter();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()== android.R.id.home)
            finish();
        return true;
    }

    /**
     * 点击搜索按钮
     * @param view
     */
    public void clickSearch(View view){
        mNicknameTv.setText("");
        mMobileTv.setText("");
        mBalanceTv.setText("");
        mIntegralTv.setText("");
        mPayBtn.setVisibility(View.GONE);
        String mSearchMobile = mSearchMobileEt.getText().toString().trim();
        if (MobileChecker.isMobileStrValid(mSearchMobile)){
            mPresenter.requestMemberInfo(mSearchMobile);
        }else {
            ToastUtils.showShortToast(getRealContext(), "请输入有效手机号");
        }
    }

    /**
     * 点击付款按钮
     * @param view
     */
    public void clickPay(View view){
        showPayAmountDialog();
    }

    @Override
    public void refreshMemberInfo(MemberInfoResponseBean memberInfoResponseBean) {
        mNicknameTv.setText(memberInfoResponseBean.getNickname());
        mMobileTv.setText(memberInfoResponseBean.getMobile());
        mBalanceTv.setText(String.valueOf(memberInfoResponseBean.getAvailablebalance()));
        mIntegralTv.setText(String.valueOf(memberInfoResponseBean.getIntegral()));

        if (memberInfoResponseBean.getAvailablebalance() > 0){
            mPayBtn.setVisibility(View.VISIBLE);
        }else {
            mPayBtn.setVisibility(View.GONE);
        }
    }

    @Override
    public void showPayAmountDialog() {
        View view = LayoutInflater.from(getRealContext()).inflate(R.layout.layout_dialog_pay_edit, null);
        AlertDialog dialog = DialogFactory.createDialog(getRealContext(), view);
        EditText payAmountEt = view.findViewById(R.id.dialog_pay_edit_edittext);
        TextView payBtn = view.findViewById(R.id.dialog_pay_edit_button_confirm);
        TextView cancelBtn = view.findViewById(R.id.dialog_pay_edit_button_cancel);

        payBtn.setOnClickListener(v -> {
            String payAmountStr = payAmountEt.getText().toString();
            if (Tools.isBlank(payAmountStr)){
                ToastUtils.showShortToast(getRealContext(), "请输入有效支付金额");
                return;
            }
            double payAmount;
            try{
                payAmount = Double.parseDouble(payAmountStr);
            }catch (NumberFormatException e){
                e.printStackTrace();
                ToastUtils.showShortToast(getRealContext(), "请输入有效支付金额");
                return;
            }
            if (payAmount > 0){
                dialog.dismiss();
                showPayConfirmDialog(payAmount);
            }else {
                ToastUtils.showShortToast(getRealContext(), "请输入有效支付金额");
            }
        });
        cancelBtn.setOnClickListener(v -> dialog.dismiss());

        dialog.show();
    }

    @Override
    public void showPayConfirmDialog(double payAmount) {
        View view = LayoutInflater.from(getRealContext()).inflate(R.layout.layout_dialog_pay_confirm, null);
        AlertDialog dialog = DialogFactory.createDialog(getRealContext(), view);
        TextView contentTv = view.findViewById(R.id.dialog_pay_confirm_tv_content);
        TextView payBtn = view.findViewById(R.id.dialog_pay_confirm_button_confirm);
        TextView cancelBtn = view.findViewById(R.id.dialog_pay_confirm_button_cancel);

        String payAmountStr = String.valueOf(payAmount);
        String contentStr = String.format(getString(R.string.dialog_pay_confirm_default_info), payAmountStr);

        SpannableString spannableString = new SpannableString(contentStr);
        ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.RED);
        spannableString.setSpan(colorSpan, 4, 4 + payAmountStr.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        StyleSpan styleSpan_B  = new StyleSpan(Typeface.BOLD);
        spannableString.setSpan(styleSpan_B, 4, 4 + payAmountStr.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);

        contentTv.setText(spannableString);

        payBtn.setOnClickListener(v -> {
            dialog.dismiss();
            AlertDialog loadingDialog = DialogFactory.createLoadingDialog(getRealContext());
            loadingDialog.show();
            String mobileStr = mMobileTv.getText().toString();
            mPresenter.payMemberBalance(mobileStr, payAmount, new DataRequestCallback<String>() {
                @Override
                public void onSuccessed(String s) {
                    loadingDialog.dismiss();
                    ToastUtils.showShortToast(getRealContext(), "消费完成");
                    mPresenter.requestMemberInfo(mobileStr);
                }

                @Override
                public void onFailed(String errorMsg) {
                    loadingDialog.dismiss();
                    ToastUtils.showShortToast(getRealContext(), "\"会员消费\"失败->" + errorMsg);
                }
            });
        });
        cancelBtn.setOnClickListener(v -> dialog.dismiss());

        dialog.show();
    }

}
