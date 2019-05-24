package com.wd.tech.user.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.wd.tech.R;
import com.wd.tech.user.bean.SingBean;
import com.wd.tech.utils.base.RsaCoder;
import com.wd.tech.utils.netWork.Api;
import com.wd.tech.utils.presenter.IPresentermpl;
import com.wd.tech.utils.view.IView;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SingActivity extends AppCompatActivity implements IView {

    @BindView(R.id.sing_nickname)
    EditText sing_nickname;
    @BindView(R.id.sing_phone_num)
    EditText sing_phone_num;
    @BindView(R.id.sing_phone_pwd)
    EditText sing_phone_pwd;
    @BindView(R.id.sing)
    Button sing;
    IPresentermpl iPresentermpl;
    String pwd2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing);
        ButterKnife.bind(this);
        iPresentermpl=new IPresentermpl(this);

    }
    @OnClick({R.id.sing})
    public  void setlick(View view)
    {
        switch (view.getId())
        {
            case R.id.sing:
                        String name=sing_nickname.getText().toString();
                        String phone=sing_phone_num.getText().toString();
                        String  pwd=sing_phone_pwd.getText().toString();
                        //密码加密
                try {
                     pwd2= RsaCoder.encryptByPublicKey(pwd);

                } catch (Exception e) {
                    e.printStackTrace();

                }
                    if(name.length()==0)
                    {
                        Toast.makeText(this, "昵称不能为空", Toast.LENGTH_SHORT).show();
                    }else if(phone.length()<11)
                    {
                        Toast.makeText(this,"请输入正确的账号长度",Toast.LENGTH_LONG).show();
                    }else if(pwd.length()<5)
                    {
                        Toast.makeText(this,"密码长度最少为6",Toast.LENGTH_LONG).show();
                    }
                    Map<String,String> map=new HashMap<>();
                    map.put("phone",phone);
                    map.put("nickName",name);
                    map.put("pwd",pwd2);
                    iPresentermpl.postRequest(Api.REGISTER,map,SingBean.class);
                break;
        }
    }

    @Override
    public void success(Object data) {
        if(data instanceof SingBean)
        {
            SingBean singBean= (SingBean) data;
            if(singBean.getMessage().equals("注册成功"))
            {
                Toast.makeText(this, singBean.getMessage()+"", Toast.LENGTH_SHORT).show();
            }else
            {
                Toast.makeText(this, singBean.getMessage()+"" ,Toast.LENGTH_SHORT).show();
            }
        }

    }

    @Override
    public void failure(String error) {

    }
}
