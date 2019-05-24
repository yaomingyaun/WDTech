package com.wd.tech.activity;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.wd.tech.R;
import com.wd.tech.adapter.MyImageAdapter;
import com.wd.tech.adapter.PhotoViewPager;

import java.util.ArrayList;
import java.util.List;

public class PhotoViewActivity extends AppCompatActivity {

    private PhotoViewPager mViewPager;
    private int currentPosition;
    private MyImageAdapter adapter;
    private TextView mTvImageCount;
    private int imagepostion;
    private ArrayList<String> urls;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_view);
        mViewPager = (PhotoViewPager) findViewById(R.id.mViewPager);
        mTvImageCount=findViewById(R.id.mTvImageCount);
        initData();
    }

    private void initData() {
        //接受传值
        Intent intent = getIntent();
        imagepostion = intent.getIntExtra("imageposition",0);
        urls = intent.getStringArrayListExtra("image");
        MyImageAdapter adapter = new MyImageAdapter(urls, this);
        mViewPager.setAdapter(adapter);
        mViewPager.setCurrentItem(imagepostion, false);
        mTvImageCount.setText(imagepostion+1 + "/" + urls.size());
        mViewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                imagepostion = position;
                mTvImageCount.setText(imagepostion + 1 + "/" + urls.size());
            }
        });

    }


}
