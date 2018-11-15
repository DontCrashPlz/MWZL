package com.pokong.library.app;

import android.app.Activity;

import java.util.ArrayDeque;
import java.util.Stack;

/**
 * Created by Zheng on 2017/10/16.
 * 全局Activity管理器
 */

public class ActivityManager {

    private static ActivityManager mInstance = null;

    private static ArrayDeque<Activity> mActivityStack = new ArrayDeque<>();

    private ActivityManager() {
    }

    public static ActivityManager getInstance() {
        if (mInstance == null) {
            synchronized (ActivityManager.class) {
                if (mInstance == null) {
                    mInstance = new ActivityManager();
                }
            }
        }
        return mInstance;
    }

    public void addActivity(Activity activity) {
        if (mActivityStack == null) {
            mActivityStack = new ArrayDeque<>();
        }
        mActivityStack.add(activity);
    }

    public void removeActivity(Activity activity) {
        if (mActivityStack == null) return;
        if (activity != null) {
            mActivityStack.removeLastOccurrence(activity);
        }

    }

    public void finishAll() {
        if (mActivityStack == null) return;
        for (Activity activity : mActivityStack) {
            if (activity != null) activity.finish();
        }
        mActivityStack.clear();
    }

}
