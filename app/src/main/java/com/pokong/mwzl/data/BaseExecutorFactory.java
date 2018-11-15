package com.pokong.mwzl.data;

import com.pokong.mwzl.data.bean.business.CommentGoodsListRequestBean;
import com.pokong.mwzl.data.bean.business.CustomCakeRequestBean;
import com.pokong.mwzl.data.bean.business.GoodsCommentListRequestBean;
import com.pokong.mwzl.data.bean.business.OrderDetailRequestBean;
import com.pokong.mwzl.data.bean.business.OrderListRequestBean;
import com.pokong.mwzl.data.bean.business.OrderReadyRequestBean;
import com.pokong.mwzl.data.bean.business.QueryOrderRequestBean;
import com.pokong.mwzl.data.bean.business.ShopInfoRequestBean;
import com.pokong.mwzl.data.bean.personal.LoginRequestBean;
import com.pokong.mwzl.data.bean.personal.NoticeDetailRequestBean;
import com.pokong.mwzl.data.bean.personal.NoticeListRequestBean;
import com.pokong.mwzl.data.executor.business.CommentGoodsListExecutor;
import com.pokong.mwzl.data.executor.business.CustomCakeExecutor;
import com.pokong.mwzl.data.executor.business.GoodsCommentListExecutor;
import com.pokong.mwzl.data.executor.business.OrderDetailExecutor;
import com.pokong.mwzl.data.executor.business.OrderListExecutor;
import com.pokong.mwzl.data.executor.business.OrderReadyExecutor;
import com.pokong.mwzl.data.executor.business.QueryOrderExecutor;
import com.pokong.mwzl.data.executor.business.ShopInfoExecutor;
import com.pokong.mwzl.data.executor.personal.LoginExecutor;
import com.pokong.mwzl.data.executor.personal.NoticeDetailExecutor;
import com.pokong.mwzl.data.executor.personal.NoticeListExecutor;
import com.pokong.mwzl.http.ApiService;

/**
 * Created on 2018/11/8 16:21
 * User: Zheng
 * E-mail: zhengCH12138@163.com
 */
public class BaseExecutorFactory {

    public static DataExecutor<BaseResponseBean> create(ApiService apiService, BaseRequestBean paramsBean){
        DataExecutor executor = null;
        if (paramsBean instanceof LoginRequestBean){
            executor = new LoginExecutor(apiService, (LoginRequestBean) paramsBean);
        }else if (paramsBean instanceof NoticeListRequestBean){
            executor = new NoticeListExecutor(apiService, (NoticeListRequestBean) paramsBean);
        }else if (paramsBean instanceof NoticeDetailRequestBean){
            executor = new NoticeDetailExecutor(apiService, (NoticeDetailRequestBean) paramsBean);
        }else if (paramsBean instanceof OrderListRequestBean){
            executor = new OrderListExecutor(apiService, (OrderListRequestBean) paramsBean);
        }else if (paramsBean instanceof OrderDetailRequestBean){
            executor = new OrderDetailExecutor(apiService, (OrderDetailRequestBean) paramsBean);
        }else if (paramsBean instanceof OrderReadyRequestBean){
            executor = new OrderReadyExecutor(apiService, (OrderReadyRequestBean) paramsBean);
        }else if (paramsBean instanceof ShopInfoRequestBean){
            executor = new ShopInfoExecutor(apiService, (ShopInfoRequestBean) paramsBean);
        }else if (paramsBean instanceof QueryOrderRequestBean){
            executor = new QueryOrderExecutor(apiService, (QueryOrderRequestBean) paramsBean);
        }else if (paramsBean instanceof CommentGoodsListRequestBean){
            executor = new CommentGoodsListExecutor(apiService, (CommentGoodsListRequestBean) paramsBean);
        }else if (paramsBean instanceof GoodsCommentListRequestBean){
            executor = new GoodsCommentListExecutor(apiService, (GoodsCommentListRequestBean) paramsBean);
        }else if (paramsBean instanceof CustomCakeRequestBean){
            executor = new CustomCakeExecutor(apiService, (CustomCakeRequestBean) paramsBean);
        }
        if (executor == null) {
            throw new NullPointerException("BaseExecutorFactory未找到有效的网络执行器");
        }
        return executor;
    }

}
