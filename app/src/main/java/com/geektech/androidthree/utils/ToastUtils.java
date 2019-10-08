package com.geektech.androidthree.utils;

import android.content.Context;
import android.widget.Toast;

public class ToastUtils {

    public static void showToast(String message, Context context){
        makeToast(message, context);
    }

    private static void makeToast(String message, Context context){
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }
}
