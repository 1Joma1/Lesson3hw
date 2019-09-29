package com.geektech.androidthree.data;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public class SharedPreferenceHelper {

    private final static String PREFRENCE = "sharedPref";
    private final static String IS_FIRSST_LAUNCH = "isFirstLaunch";

    private SharedPreferences sharedPreferences;

    public SharedPreferenceHelper(Context context) {
        sharedPreferences = context.getSharedPreferences(PREFRENCE, MODE_PRIVATE);
    }

    public void setIsFirstLaunch(){
        sharedPreferences.edit().putBoolean(IS_FIRSST_LAUNCH, false).apply();
    }

    public boolean isFirstLaunch(){
        return sharedPreferences.getBoolean(IS_FIRSST_LAUNCH, true);
    }
}
