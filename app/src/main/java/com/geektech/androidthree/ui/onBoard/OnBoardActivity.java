package com.geektech.androidthree.ui.onBoard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.geektech.androidthree.R;
import com.geektech.androidthree.ui.main.MainActivity;
import com.rd.PageIndicatorView;

public class OnBoardActivity extends AppCompatActivity {


    public static void start(Context context) {
        context.startActivity(new Intent(context, OnBoardActivity.class));
    }

    private ViewPager viewPager;
    private Button nextBtn, skipBtn;
    private PageIndicatorView pageIndicatorView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_board);

        nextBtn = findViewById(R.id.next_button_onboard);
        skipBtn = findViewById(R.id.skip_button_onboard);

        pageIndicatorView = findViewById(R.id.pageIndicatorView);
        pageIndicatorView.setCount(4);
        pageIndicatorView.setSelection(2);

        viewPager = findViewById(R.id.onboard_viewpager);
        viewPager.setAdapter(new onBoardAdapter());

        viewPagerListener();
        buttonClickListener();
    }

    private void buttonClickListener() {
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (viewPager.getCurrentItem() < 3) {
                    int i = viewPager.getCurrentItem();
                    viewPager.setCurrentItem(++i);
                } else {
                    MainActivity.start(OnBoardActivity.this);
                    finish();
                }
            }
        });
        skipBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.start(OnBoardActivity.this);
                finish();
            }
        });
    }

    private void viewPagerListener() {
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                pageIndicatorView.setSelection(position);
                if (position == 3) {
                    nextBtn.setText("Начать");
                    skipBtn.setVisibility(View.GONE);
                } else {
                    nextBtn.setText("Далее");
                    skipBtn.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
