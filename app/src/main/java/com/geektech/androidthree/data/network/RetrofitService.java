package com.geektech.androidthree.data.network;

import com.geektech.androidthree.data.current_weather.CurrentWeatherModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetrofitService {

    @GET("weather")
    Call<CurrentWeatherModel> getWeatherByCity(@Query("q") String city,@Query("units") String unit, @Query("APPID") String appid);

    @GET("img/wn/{icon}@2x.png")
    Call<CurrentWeatherModel> getWeatherIcon(@Path("icon") String icon);

}
