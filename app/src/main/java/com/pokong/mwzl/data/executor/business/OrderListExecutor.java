package com.pokong.mwzl.data.executor.business;

import com.pokong.library.util.LogUtils;
import com.pokong.library.util.TimeChecker;
import com.pokong.library.util.Tools;
import com.pokong.mwzl.data.DataResponseBean;
import com.pokong.mwzl.data.MultiPageListEntity;
import com.pokong.mwzl.data.bean.OrderListItemEntity;
import com.pokong.mwzl.data.bean.business.OrderListRequestBean;
import com.pokong.mwzl.data.BaseExecutor;
import com.pokong.mwzl.http.ApiService;
import com.pokong.mwzl.http.ErrorDataResponseFactory;

import java.util.HashMap;

import io.reactivex.Observable;

/**
 * Created on 2018/11/7 16:38
 * User: Zheng
 * E-mail: zhengCH12138@163.com
 * 网络请求执行类--获取订单列表
 */
public class OrderListExecutor extends BaseExecutor<OrderListRequestBean, MultiPageListEntity<OrderListItemEntity>> {

    private final String PARAMKEY_APPTOKEN = "appToken";
    private final String PARAMKEY_ORDERCOLUNM = "orderColunm";
    private final String PARAMKEY_ORDERMODE = "orderMode";
    private final String PARAMKEY_PAGESIZE = "pageSize";
    private final String PARAMKEY_PAGENUMBER = "pageNumber";
    private final String PARAMKEY_SPLITPAGE = "splitpage";

    private final String PARAMKEY_STARTID = "_query.startId";
    private final String PARAMKEY_ORDERSTATUS = "_query.order_status";
    private final String PARAMKET_DELIVERY_TYPT = "_query.deliveryType";
    private final String PARAMKET_BEGINTIME = "_query.beginTime";
    private final String PARAMKET_ENDTIME = "_query.endTime";
    private final String PARAMKET_ORDERNO = "_query.orderNo";

    private HashMap<String, String> paramsMap;

    public OrderListExecutor(ApiService apiService, OrderListRequestBean paramsBean) {
        super(apiService, paramsBean);
        paramsMap = new HashMap<>();
    }

    @Override
    public Observable<DataResponseBean<MultiPageListEntity<OrderListItemEntity>>> execute() {
        String appToken = paramsBean.getAppToken();
        if (Tools.isBlank(appToken)){
            return Observable.create(emitter -> emitter.onNext(ErrorDataResponseFactory.create("appToken无效")));
        }
        paramsMap.put(PARAMKEY_APPTOKEN, appToken);

        paramsMap.put(PARAMKEY_ORDERCOLUNM, paramsBean.getOrderColunm());

        paramsMap.put(PARAMKEY_ORDERMODE, paramsBean.getOrderMode());

        paramsMap.put(PARAMKEY_PAGESIZE, String.valueOf(paramsBean.getPageSize()));

        paramsMap.put(PARAMKEY_PAGENUMBER, String.valueOf(paramsBean.getPageNumber()));

        long startId = paramsBean.getStartId();
        if (startId > 0){
            paramsMap.put(PARAMKEY_STARTID, String.valueOf(startId));
        }

        paramsMap.put(PARAMKEY_SPLITPAGE, String.valueOf(paramsBean.getSplitpage()));

        int orderStatus = paramsBean.getOrder_status();
        if (orderStatus > 0){
            paramsMap.put(PARAMKEY_ORDERSTATUS, String.valueOf(orderStatus));
        }

        String deliveryType = paramsBean.getDeliveryType();
        if (!Tools.isBlank(deliveryType)){
            paramsMap.put(PARAMKET_DELIVERY_TYPT, deliveryType);
        }

        String beginTime = paramsBean.getBeginTime();
        if (TimeChecker.isTimeStrValid(beginTime)){
            LogUtils.e("OrderListExecutor", "beginTime: " + beginTime);
            paramsMap.put(PARAMKET_BEGINTIME, beginTime);
        }
        String endTime = paramsBean.getEndTime();
        if (TimeChecker.isTimeStrValid(endTime)){
            LogUtils.e("OrderListExecutor", "endTime: " + endTime);
            paramsMap.put(PARAMKET_ENDTIME, endTime);
        }

        String orderNo = paramsBean.getOrderNo();
        if (!Tools.isBlank(orderNo)){
            LogUtils.e("OrderListExecutor", "orderNo: " + orderNo);
            paramsMap.put(PARAMKET_ORDERNO, orderNo);
        }

        return apiService.getOrderList(paramsMap);
    }
}
