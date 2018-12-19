package com.pokong.mwzl.shop.member;

import android.support.v7.app.AlertDialog;

import com.pokong.library.base.BasePresenter;
import com.pokong.library.util.DialogFactory;
import com.pokong.library.util.ToastUtils;
import com.pokong.library.util.Tools;
import com.pokong.mwzl.app.MyApplication;
import com.pokong.mwzl.data.DataRequestCallback;
import com.pokong.mwzl.data.bean.mwzl.MemberInfoRequestBean;
import com.pokong.mwzl.data.bean.mwzl.MemberInfoResponseBean;
import com.pokong.mwzl.data.bean.mwzl.MemberPayRequestBean;
import com.pokong.mwzl.data.source.MWZLHttpDataRepository;

/**
 * Created on 2018/12/18 14:17
 * User: Zheng
 * E-mail: zhengCH12138@163.com
 */
public class MemberServicePresenter extends BasePresenter<MemberServiceActivity> implements MemberServiceContract.Presenter {

    @Override
    public void requestMemberInfo(String mobile) {
        MemberInfoRequestBean requestBean = new MemberInfoRequestBean();

        String appToken = MyApplication.getInstance().getAppToken();
        if (Tools.isBlank(appToken)) {
            ToastUtils.showShortToast(getView().getRealContext(), "\"获取会员信息\"失败->登录令牌无效");
            return;
        }
        requestBean.setAppToken(appToken);

        requestBean.setMobile(mobile);

        AlertDialog alertDialog = DialogFactory.createLoadingDialog(getView().getRealContext());
        alertDialog.show();
        getView().addNetWork(MWZLHttpDataRepository.getInstance().getMemberInfo(requestBean, new DataRequestCallback<MemberInfoResponseBean>() {
            @Override
            public void onSuccessed(MemberInfoResponseBean memberInfoResponseBean) {
                alertDialog.dismiss();
                getView().refreshMemberInfo(memberInfoResponseBean);
            }

            @Override
            public void onFailed(String errorMsg) {
                alertDialog.dismiss();
                ToastUtils.showShortToast(getView().getRealContext(), errorMsg);
            }
        }));
    }

    @Override
    public void payMemberBalance(String mobile, double payAmount, DataRequestCallback<String> callback) {
        MemberPayRequestBean requestBean = new MemberPayRequestBean();

        String appToken = MyApplication.getInstance().getAppToken();
        if (Tools.isBlank(appToken)) {
            ToastUtils.showShortToast(getView().getRealContext(), "\"会员消费\"失败->登录令牌无效");
            return;
        }
        requestBean.setAppToken(appToken);

        requestBean.setMobile(mobile);

        requestBean.setTotal(payAmount);

        getView().addNetWork(MWZLHttpDataRepository.getInstance().memberPay(requestBean, callback));

    }

}
