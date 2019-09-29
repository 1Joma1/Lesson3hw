package com.geektech.androidthree.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.geektech.androidthree.R;
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
        viewPager.setAdapter(new onBoardAdapter(this));

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
                } else finish();
            }
        });
        skipBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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

    public class onBoardAdapter extends PagerAdapter {

        private Context context;

        public onBoardAdapter(Context context) {
            this.context = context;
        }

        @Override
        public int getCount() {
            return 4;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View) object);
        }

        @Override
        public Object instantiateItem(ViewGroup collection, int position) {
            LayoutInflater inflater = LayoutInflater.from(context);
            ViewGroup layout = (ViewGroup) inflater.inflate(R.layout.item_viewpager_onboard, collection, false);
            ImageView onBoardImage = layout.findViewById(R.id.onboard_imageview);
            TextView onBoardText = layout.findViewById(R.id.onboard_textview);
            switch (position) {
                case 0:
                    onBoardImage.setImageDrawable(getResources().getDrawable(R.drawable.on_board1));
                    onBoardText.setText("В данном приложении вы можете учиться))");
                    break;
                case 1:
                    onBoardImage.setImageDrawable(getResources().getDrawable(R.drawable.on_board2));
                    onBoardText.setText("В данном приложении вы можете обновлять))");
                    break;
                case 2:
                    onBoardImage.setImageDrawable(getResources().getDrawable(R.drawable.on_board3));
                    onBoardText.setText("В данном приложении вы можете удалять");
                    break;
                case 3:
                    onBoardImage.setImageDrawable(getResources().getDrawable(R.drawable.on_board4));
                    onBoardText.setText("Спасибо что вы с нами))");
                    break;
            }
            collection.addView(layout);
            return layout;

        }
    }
}
