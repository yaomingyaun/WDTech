package com.wd.tech.user.activity;


import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.iflytek.cloud.Setting;
import com.wd.tech.R;
import com.wd.tech.ShowActivity;
import com.wd.tech.utils.netWork.NoStudoInterent;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    private  int t=3;
    @BindView(R.id.wuwangluo)
    RelativeLayout wuwangluo;
    @BindView(R.id.yindaoye)
    RelativeLayout yindaoye;
    @BindView(R.id.network_sz)
    Button network_sz;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
       //判断网络主题
        if(!NoStudoInterent.isNetworkConnected(MainActivity.this))
        {
            wuwangluo.setVisibility(View.VISIBLE);
            yindaoye.setVisibility(View.GONE);
        }else
        {
            wuwangluo.setVisibility(View.GONE);
            yindaoye.setVisibility(View.VISIBLE);
            handler.sendEmptyMessage(0);
        }
    }
private Handler handler=new Handler() {
    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        switch (msg.what) {
            case 0:
                if (t == 0) {
                    Intent intent=new Intent(MainActivity.this,LoginActivity.class);
                    startActivity(intent);
                    finish();
                    return;
                }
                t--;
                handler.sendEmptyMessageDelayed(0, 1000);
                break;
//
            default:
                break;
        };

    }

};
@OnClick(R.id.network_sz)
        public  void setLick(View v)
{
            switch (v.getId())
            {
                case R.id.network_sz:
                    startActivity(new Intent(Settings.ACTION_WIRELESS_SETTINGS));
                    finish();
                    break;
            }
}

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(handler);

    }
}
