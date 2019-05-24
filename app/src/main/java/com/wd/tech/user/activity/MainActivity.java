package com.wd.tech.user.activity;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.wd.tech.R;
import com.wd.tech.ShowActivity;

public class MainActivity extends AppCompatActivity {
    private ImageView yindaoye;
    private  int t=5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        handler.sendEmptyMessage(0);
    }
private Handler handler=new Handler() {
    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        switch (msg.what) {
            case 0:
                if (t == 0) {
                    //sadsad
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(handler);

    }
}
