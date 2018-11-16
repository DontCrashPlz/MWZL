package com.pokong.library.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created on 2018/11/16 14:32
 * User: Zheng
 * E-mail: zhengCH12138@163.com
 */
public abstract class LazyLoadFragment <T extends BasePresenter> extends BaseFragment {
    protected View rootView;
    protected boolean isInitView = false;//标识符--页面初始化
    protected boolean isVisible = false;//标识符--页面可见性
    protected boolean isLoadedOnce = false;//标识符--页面已执行过一次懒加载

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(getContentView(), container, false);
        init();
        isInitView = true;//页面初始化完成
        tryToLoad();
        return rootView;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        //isVisibleToUser这个boolean值表示:该Fragment的UI 用户是否可见，获取该标志记录下来
        if(isVisibleToUser){
            isVisible = true;
            tryToLoad();
        }else{
            isVisible = false;
        }
    }

    /**
     * 尝试去执行懒加载
     */
    private void tryToLoad(){
        //如果页面初始化完成、页面可见、且页面没有执行过懒加载，执行懒加载操作
        if(isInitView && isVisible && !isLoadedOnce ){
            lazyLoad();
            isLoadedOnce = true;
        }
    }

    /**
     * 加载页面布局文件
     * @return
     */
    protected abstract int getContentView();

    /**
     * 让布局中的view与fragment中的变量建立起映射
     */
    protected abstract void init();

    /**
     * 加载要显示的数据
     */
    protected abstract void lazyLoad();

    /**
     * 设置页面实际的Presenter
     * @return
     */
    @Override
    protected abstract T getRealPresenter();
}
