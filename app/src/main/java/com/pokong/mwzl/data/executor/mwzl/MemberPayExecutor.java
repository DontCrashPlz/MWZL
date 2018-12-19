package com.pokong.mwzl.data.executor.mwzl;

import com.pokong.library.util.Tools;
import com.pokong.mwzl.data.BaseExecutor;
import com.pokong.mwzl.data.DataResponseBean;
import com.pokong.mwzl.data.bean.mwzl.MemberPayRequestBean;
import com.pokong.mwzl.data.bean.mwzl.PickConfirmRequestBean;
import com.pokong.mwzl.http.ApiService;
import com.pokong.mwzl.http.ErrorDataResponseFactory;

import java.util.HashMap;

import io.reactivex.Observable;

/**
 * Created by Zheng on 2018/10/19.
 * 网络请求执行类--会员余额支付
 */

public class MemberPayExecutor extends BaseExecutor<MemberPayRequestBean, String> {

    private final String PARAMKEY_APPTOKEN = "appToken";
    private final String PARAMKEY_MOBILE = "mobile";
    private final String PARAMKEY_TOTAL = "total";
    private final String PARAMKEY_ORDERTYPE = "orderType";
    private final String PARAMKEY_TERMINALTYPE = "terminalType";
    private final String PARAMKEY_PAYTYPE = "payType";

    private HashMap<String, String> paramsMap;

    public MemberPayExecutor(ApiService apiService, MemberPayRequestBean paramsBean) {
        super(apiService, paramsBean);
        paramsMap = new HashMap<>();
    }

    @Override
    public Observable<DataResponseBean<String>> execute() {
        String appToken = paramsBean.getAppToken();
        if (Tools.isBlank(appToken)){
            return Observable.create(emitter -> emitter.onNext(ErrorDataResponseFactory.create("appToken无效")));
        }
        paramsMap.put(PARAMKEY_APPTOKEN, appToken);

        String mobile = paramsBean.getMobile();
        if (Tools.isBlank(mobile)){
            return Observable.create(emitter -> emitter.onNext(ErrorDataResponseFactory.create("请输入有效手机号码")));
        }
        paramsMap.put(PARAMKEY_MOBILE, mobile);

        double total = paramsBean.getTotal();
        if (total <= 0){
            return Observable.create(emitter -> emitter.onNext(ErrorDataResponseFactory.create("请输入有效支付金额")));
        }
        paramsMap.put(PARAMKEY_TOTAL, String.valueOf(total));

        paramsMap.put(PARAMKEY_ORDERTYPE, paramsBean.getOrderType());
        paramsMap.put(PARAMKEY_TERMINALTYPE, paramsBean.getTerminalType());
        paramsMap.put(PARAMKEY_PAYTYPE, paramsBean.getPayType());

        return apiService.memberPay(paramsMap);
    }
}
