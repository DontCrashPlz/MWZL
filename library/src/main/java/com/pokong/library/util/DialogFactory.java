package com.pokong.library.util;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.pokong.library.R;

/**
 * Created on 2018/11/27 16:03
 * User: Zheng
 * E-mail: zhengCH12138@163.com
 * 弹窗静态工厂，用于create弹窗
 */
public class DialogFactory {

    public static AlertDialog createLoadingDialog(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_dialog_loading, null);
        // 设置style 控制默认dialog带来的边距问题
        AlertDialog.Builder builder = new AlertDialog.Builder(context,R.style.custom_dialog_no_titlebar);
        builder.setView(view);
        return builder.create();
    }

    public static AlertDialog createLoadingDialog(Context context, String description) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_dialog_loading, null);
        TextView textView = view.findViewById(R.id.loading_dialog_description);
        textView.setText(description);
        // 设置style 控制默认dialog带来的边距问题
        AlertDialog.Builder builder = new AlertDialog.Builder(context,R.style.custom_dialog_no_titlebar);
        builder.setView(view);
        return builder.create();
    }

    public static AlertDialog createDialog(Context context, int layoutRes){
        View view = LayoutInflater.from(context).inflate(layoutRes, null);
        // 设置style 控制默认dialog带来的边距问题
        AlertDialog.Builder builder = new AlertDialog.Builder(context,R.style.custom_dialog_no_titlebar);
        builder.setView(view);
        return builder.create();
    }

    public static AlertDialog createDialog(Context context, View view){
        // 设置style 控制默认dialog带来的边距问题
        AlertDialog.Builder builder = new AlertDialog.Builder(context,R.style.custom_dialog_no_titlebar);
        builder.setView(view);
        return builder.create();
    }

}
