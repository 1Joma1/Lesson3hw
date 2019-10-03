package com.geektech.androidthree.ui.onBoard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.geektech.androidthree.R;

public class onBoardAdapter extends PagerAdapter {

    private Context context;

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
        context = collection.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_viewpager_onboard, null);
        ImageView onBoardImage = view.findViewById(R.id.onboard_imageview);
        TextView onBoardText = view.findViewById(R.id.onboard_textview);
        switch (position) {
            case 0:
                onBoardImage.setImageDrawable(context.getResources().getDrawable(R.drawable.on_board1));
                onBoardText.setText("В данном приложении вы можете учиться))");
                break;
            case 1:
                onBoardImage.setImageDrawable(context.getResources().getDrawable(R.drawable.on_board2));
                onBoardText.setText("В данном приложении вы можете обновлять))");
                break;
            case 2:
                onBoardImage.setImageDrawable(context.getResources().getDrawable(R.drawable.on_board3));
                onBoardText.setText("В данном приложении вы можете удалять");
                break;
            case 3:
                onBoardImage.setImageDrawable(context.getResources().getDrawable(R.drawable.on_board4));
                onBoardText.setText("Спасибо что вы с нами))");
                break;
        }
        collection.addView(view);
        return view;

    }
}