package com.pokong.library.app;

import android.app.Service;

import java.util.ArrayDeque;
import java.util.HashMap;

/**
 * Created by Zheng on 2017/10/16.
 * 全局Service管理器
 */

public class ServiceManager {

    private static ServiceManager mInstance = null;

    private static HashMap<String, Service> mServiceStack = new HashMap<>();

    private ServiceManager() {
    }

    public static ServiceManager getInstance() {
        if (mInstance == null) {
            synchronized (ServiceManager.class) {
                if (mInstance == null) {
                    mInstance = new ServiceManager();
                }
            }
        }
        return mInstance;
    }

    public void addService(Service service) {
        if (mServiceStack == null) {
            mServiceStack = new HashMap<>();
        }
        mServiceStack.put(service.getClass().getSimpleName(), service);
    }

    public void removeService(Service service) {
        if (mServiceStack == null) return;
        if (service != null) {
            mServiceStack.remove(service.getClass().getSimpleName());
        }

    }

    public void finishAll() {
        if (mServiceStack == null) return;
        for (String key : mServiceStack.keySet()){
            Service service = mServiceStack.get(key);
            if (service != null) service.stopSelf();
        }
        mServiceStack.clear();
    }

}
