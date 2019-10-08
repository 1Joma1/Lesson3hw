package com.geektech.androidthree.ui.base;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.geektech.androidthree.R;
import com.geektech.androidthree.data.current_weather.CurrentWeatherModel;
import com.geektech.androidthree.data.network.RetrofitBuilder;
import com.geektech.androidthree.ui.main.MainActivity;
import com.geektech.androidthree.utils.ToastUtils;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class BaseWeatherFragment extends BaseFragment {

    @BindView(R.id.iconView)
    ImageView iconView;
    @BindView(R.id.refresh_button)
    TextView refreshButton;
    @BindView(R.id.city_edit_text)
    EditText cityName;
    @BindView(R.id.main_type)
    TextView mainSkyType;
    @BindView(R.id.main_bg)
    ImageView mainImage;
    @BindView(R.id.loadingView)
    View loadingView;
    @BindView(R.id.temp_celsius)
    TextView temp;
    @BindView(R.id.weather_text)
    TextView weatherText;

    private String searchCity = "Bishkek";

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getWeatherByCity(searchCity);
        onClicks();
        changeBackgroundByTime();
    }

    private void getWeatherByCity(String city) {
        RetrofitBuilder.getService().getWeatherByCity(city, "metric", getResources().getString(R.string.api_key))
                .enqueue(new Callback<CurrentWeatherModel>() {
                    @Override
                    public void onResponse(@org.jetbrains.annotations.Nullable Call<CurrentWeatherModel> call, @org.jetbrains.annotations.Nullable Response<CurrentWeatherModel> response) {
                        if (response != null && response.isSuccessful() && response.body() != null) {
                            String icon = "http://openweathermap.org/img/wn/" + response.body().getWeather().get(0).getIcon() + "@2x.png";
                            Glide.with(getContext()).load(icon).into(iconView);
                            temp.setText(String.valueOf(response.body().getMain().getTemp().intValue()));
                            weatherText.setVisibility(View.VISIBLE);
                            mainSkyType.setText(response.body().getWeather().get(0).getMain());
                            refreshButton.setVisibility(View.VISIBLE);
                            loadingView.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void onFailure(@org.jetbrains.annotations.Nullable Call<CurrentWeatherModel> call, @org.jetbrains.annotations.Nullable Throwable t) {
                        if (t != null) Log.e("getCurrentWeather", t.getMessage());
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

    @OnClick(R.id.refresh_button)
    public void refresh() {
        refreshButton.setVisibility(View.GONE);
        loadingView.setVisibility(View.VISIBLE);
        getWeatherByCity(searchCity);
    }

    private void onClicks() {

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

    private int getHour() {
        return Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
    }

}
