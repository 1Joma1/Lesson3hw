package com.geektech.androidthree.ui.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.geektech.androidthree.R;
import com.geektech.androidthree.data.current_weather.CurrentWeatherModel;
import com.geektech.androidthree.data.network.RetrofitBuilder;

import org.jetbrains.annotations.Nullable;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private TextView temp;
    private TextView weatherText;
    private TextView refreshButton;
    private View loadingView;
    private ImageView mainImage;
    private ImageView iconView;
    private TextView mainSkyType;
    private EditText cityName;
    private String searchCity = "Bishkek";

    public static void start(Context context) {
        context.startActivity(new Intent(context, MainActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findAllViewById();
        getWeatherByCity(searchCity);
        onClicks();
        changeBackgroundByTime();
    }

    private void getWeatherByCity(String city) {
        RetrofitBuilder.getService().getWeatherByCity(city, "metric", getResources().getString(R.string.api_key))
                .enqueue(new Callback<CurrentWeatherModel>() {
                    @Override
                    public void onResponse(@Nullable Call<CurrentWeatherModel> call, @Nullable Response<CurrentWeatherModel> response) {
                        if (response != null && response.isSuccessful() && response.body() != null) {
                            String icon = "http://openweathermap.org/img/wn/" + response.body().getWeather().get(0).getIcon() + "@2x.png";
                            Glide.with(MainActivity.this).load(icon).into(iconView);
                            temp.setText(String.valueOf(response.body().getMain().getTemp().intValue()));
                            weatherText.setVisibility(View.VISIBLE);
                            mainSkyType.setText(response.body().getWeather().get(0).getMain());
                            refreshButton.setVisibility(View.VISIBLE);
                            loadingView.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void onFailure(@Nullable Call<CurrentWeatherModel> call, @Nullable Throwable t) {
                        if (t != null) Log.e("getCurrentWeather", t.getMessage());
                    }
                });
    }

    private void findAllViewById() {
        iconView = findViewById(R.id.iconView);
        cityName = findViewById(R.id.city_edit_text);
        mainSkyType = findViewById(R.id.main_type);
        mainImage = findViewById(R.id.main_bg);
        loadingView = findViewById(R.id.loadingView);
        refreshButton = findViewById(R.id.refresh_button);
        temp = findViewById(R.id.temp_celsius);
        weatherText = findViewById(R.id.weather_text);
    }

    private int getHour() {
        return Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
    }

    private void onClicks() {
        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                refreshButton.setVisibility(View.GONE);
                loadingView.setVisibility(View.VISIBLE);
                getWeatherByCity(searchCity);
            }
        });

        cityName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                searchCity = charSequence.toString();
                getWeatherByCity(searchCity);
            }

            @Override
            public void afterTextChanged(Editable editable) {
                cityName.clearFocus();
            }
        });
    }

    private void changeBackgroundByTime() {
        if (getHour() > 18 || getHour() < 7) {
            mainImage.setImageDrawable(getResources().getDrawable(R.drawable.bg_weather_night));
        } else {
            mainImage.setImageDrawable(getResources().getDrawable(R.drawable.bg_weather_day));
        }
    }
}
