package com.geektech.androidthree.data.network;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitBuilder {
    private static String BASE_URL="http://api.openweathermap.org/data/2.5/";
    private static RetrofitBuilder retrofitBuilder;
    private static RetrofitService retrofitService;

    public static RetrofitService getService(){
        if (retrofitService == null)
            retrofitService = buildRetrofit();
        return retrofitService;
    }

    private static RetrofitService buildRetrofit(){
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(getClient())
                .addConverterFactory(GsonConverterFactory.create()).build()
                .create(RetrofitService.class);
    }

    private static OkHttpClient getClient(){
        return new OkHttpClient.Builder()
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS)
                .build();
    }
}
