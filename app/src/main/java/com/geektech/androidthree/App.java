package com.geektech.androidthree;

import android.app.Application;

import com.geektech.androidthree.data.SharedPreferenceHelper;


public class App extends Application {

    private static SharedPreferenceHelper preferenceHelper;

    @Override
    public void onCreate() {
        super.onCreate();
        preferenceHelper = new SharedPreferenceHelper(this);

    }


    public static SharedPreferenceHelper getPreferenceHelper() {
        return preferenceHelper;
    }
}
