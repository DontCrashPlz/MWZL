package com.pokong.library.util;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * Created on 2018/12/18 18:38
 * User: Zheng
 * E-mail: zhengCH12138@163.com
 * 键盘工具类
 */
public class KeyBoardUtils {

    /**
     * 弹出软键盘
     * @param inputView
     */
    public static void showKeyboard(View inputView){
        InputMethodManager imm = (InputMethodManager) inputView.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null){
            inputView.requestFocus();
            imm.showSoftInput(inputView, 0);
        }
    }

    /**
     * 隐藏软键盘
     * @param inputView
     */
    public static void hideKeyboard(View inputView){
        InputMethodManager imm = (InputMethodManager) inputView.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null){
            inputView.clearFocus();
            imm.hideSoftInputFromWindow(inputView.getWindowToken(), 0);
        }
    }

    /**
     * 切换软键盘
     * @param inputView
     */
    public static void toggleSoftInput(View inputView){
        InputMethodManager imm = (InputMethodManager) inputView.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null){
            imm.toggleSoftInput(0, 0);
        }
    }

}
