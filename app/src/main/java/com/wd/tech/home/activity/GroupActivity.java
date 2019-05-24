package com.wd.tech.home.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.ImageView;

import com.wd.tech.R;
import com.wd.tech.utils.serchutils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GroupActivity extends AppCompatActivity {

    @BindView(R.id.activity_group_search_ed)
    EditText activityGroupSearchEd;
    @BindView(R.id.activity_group_search_iv)
    ImageView activityGroupSearchIv;
    @BindView(R.id.activity_group_delete_iv)
    ImageView activityGroupDeleteIv;
    @BindView(R.id.activity_group_recycle)
    RecyclerView activityGroupRecycle;
    private ImageView head_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);
        ButterKnife.bind(this);
        head_back = findViewById(R.id.head_back);
        serchutils.clearButtonListener(activityGroupSearchEd,activityGroupDeleteIv,activityGroupSearchIv);
    }
}
