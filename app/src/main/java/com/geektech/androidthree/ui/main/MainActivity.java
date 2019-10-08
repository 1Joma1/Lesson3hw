package com.geektech.androidthree.ui.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.geektech.androidthree.R;
import com.geektech.androidthree.ui.base.BaseActivity;
import com.geektech.androidthree.ui.map.MapFragment;
import com.geektech.androidthree.ui.weather.WeatherFragment;
import com.geektech.androidthree.utils.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.go_to_map_click_view)
    TextView nextClick;

    Fragment fragment;
    WeatherFragment weatherFragment = new WeatherFragment();
    MapFragment mapFragment = new MapFragment();

    public static void start(Context context) {
        context.startActivity(new Intent(context, MainActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragment = weatherFragment;
        setFragment();
    }

    private void setFragment() {
        getSupportFragmentManager().beginTransaction().replace(R.id.main_view_pager, fragment).commit();
    }

    @Override
    protected Integer getResId() {
        return R.layout.activity_main;
    }

    @OnClick(R.id.go_to_map_click_view)
    public void goToMapClick() {
        if (nextClick.getText().equals("Map")) {
            fragment = mapFragment;
            setFragment();
            nextClick.setText("Weather");
            nextClick.setTextColor(getResources().getColor(R.color.black));
            nextClick.setBackgroundColor(getResources().getColor(R.color.transparent_white));
        } else {
            fragment = weatherFragment;
            setFragment();
            nextClick.setText("Map");
            nextClick.setTextColor(getResources().getColor(R.color.white));
            nextClick.setBackgroundColor(getResources().getColor(R.color.transparent_black));
        }
    }
}
