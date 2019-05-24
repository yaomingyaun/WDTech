package com.wd.tech;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.wd.tech.bean.SignBean;
import com.wd.tech.utils.netWork.Api;
import com.wd.tech.utils.presenter.IPresentermpl;
import com.wd.tech.utils.view.IView;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignActivity extends AppCompatActivity implements IView {

    @BindView(R.id.my_back)
    ImageView myBack;
    @BindView(R.id.my_sign)
    EditText mySign;
    @BindView(R.id.my_sign_but_put)
    Button mySignButPut;
    private IPresentermpl iPresentermpl;
    private String sign;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);
        ButterKnife.bind(this);
        iPresentermpl = new IPresentermpl(this);
        intent = new Intent(SignActivity.this,MessageActivity.class);
    }

    @OnClick({R.id.my_back, R.id.my_sign_but_put})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.my_back:
                startActivity(intent);
                break;
            case R.id.my_sign_but_put:
                sign = mySign.getText().toString();
                Map<String,String> map=new HashMap<>();
                map.put("signature",sign);
                iPresentermpl.putRequest(Api.SIGNATURE,map,SignBean.class);
                break;
        }
    }

    @Override
    public void success(Object data) {
        SignBean signBean= (SignBean) data;
        if (signBean.getMessage().equals("修改成功")){
            Log.e("OOOO",signBean.getMessage());
            intent.putExtra("Sign",sign);
            startActivity(intent);
            Toast.makeText(this,""+signBean.getMessage(),Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void failure(String error) {

    }
}
