package com.pokong.mwzl.shop.member;

import com.pokong.mwzl.data.DataRequestCallback;
import com.pokong.mwzl.data.bean.mwzl.MemberInfoResponseBean;

/**
 * Created on 2018/12/18 14:17
 * User: Zheng
 * E-mail: zhengCH12138@163.com
 */
public interface MemberServiceContract {

    interface View{

        /**
         * 刷新会员信息
         * @param memberInfoResponseBean
         */
        void refreshMemberInfo(MemberInfoResponseBean memberInfoResponseBean);

        /**
         * 弹出支付金额弹窗
         */
        void showPayAmountDialog();

        /**
         * 弹出支付确认弹窗
         */
        void showPayConfirmDialog(double payAmount);

    }

    interface Presenter{

        /**
         * 请求会员信息
         * @param mobile
         */
        void requestMemberInfo(String mobile);

        /**
         * 支付会员余额
         * @param mobile
         * @param payAmount
         */
        void payMemberBalance(String mobile, double payAmount, DataRequestCallback<String> callback);

    }

}
