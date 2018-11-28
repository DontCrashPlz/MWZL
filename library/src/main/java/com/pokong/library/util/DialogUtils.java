package com.pokong.library.util;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;

import com.pokong.library.R;

/**
 * Created on 2018/11/27 16:03
 * User: Zheng
 * E-mail: zhengCH12138@163.com
 */
public class DialogUtils {

    public static AlertDialog createLoadingDialog(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_dialog_loading, null);
        // 设置style 控制默认dialog带来的边距问题
        AlertDialog.Builder builder = new AlertDialog.Builder(context,R.style.custom_dialog_no_titlebar);
        builder.setView(view);
        return builder.create();
    }

}
