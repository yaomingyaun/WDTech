package com.wd.tech;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.wd.tech.bean.MessageBean;
import com.wd.tech.bean.SignBean;
import com.wd.tech.utils.netWork.Api;
import com.wd.tech.utils.presenter.IPresentermpl;
import com.wd.tech.utils.view.IView;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MessageActivity extends AppCompatActivity implements IView {


    @BindView(R.id.my_back)
    ImageView myBack;
    @BindView(R.id.my_finish)
    TextView myFinish;
    @BindView(R.id.my_message_name_user)
    EditText myMessageNameUser;
    @BindView(R.id.my_message_name)
    RelativeLayout myMessageName;
    @BindView(R.id.my_message_sex_user)
    EditText myMessageSexUser;
    @BindView(R.id.my_message_sex)
    RelativeLayout myMessageSex;
    @BindView(R.id.my_message_qianming)
    RelativeLayout myMessageQianming;
    @BindView(R.id.my_message_birthday_user)
    TextView myMessageBirthdayUser;
    @BindView(R.id.my_message_birthday)
    RelativeLayout myMessageBirthday;
    @BindView(R.id.my_message_Email_user)
    EditText myMessageEmailUser;
    @BindView(R.id.my_message_Email)
    RelativeLayout myMessageEmail;
    //获取日期格式器对象
    DateFormat fmtDateAndTime = DateFormat.getDateInstance(1);
    //获取一个日历对象
    Calendar dateAndTime = Calendar.getInstance(Locale.CHINA);
    //当点击DatePickerDialog控件的设置按钮时，调用该方法
    DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            //修改日历控件的年，月，日
            //这里的year,monthOfYear,dayOfMonth的值与DatePickerDialog控件设置的最新值一致
            dateAndTime.set(Calendar.YEAR, year);
            dateAndTime.set(Calendar.MONTH, monthOfYear);
            dateAndTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            //将页面TextView的显示更新为最新时间
            updateLabel();
        }
    };
    private Intent intent1;
    private IPresentermpl iPresentermpl;
    private String name;
    private String sex;
    private String sign;
    private String date;
    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        ButterKnife.bind(this);
        iPresentermpl = new IPresentermpl(this);
        name = myMessageNameUser.getText().toString();
        sex = myMessageSexUser.getText().toString();
        Intent intent = getIntent();
        sign = intent.getStringExtra("signature");
        date = myMessageBirthdayUser.getText().toString().trim();
        email = myMessageEmailUser.getText().toString();
        intent1 = new Intent(this,ShowActivity.class);
    }

    @Override
    public void success(Object data) {
        MessageBean messageBean= (MessageBean) data;
        if (messageBean.getMessage().equals("完善成功")){
            Log.e("OOOO",messageBean.getMessage());
            Toast.makeText(this,""+messageBean.getMessage(),Toast.LENGTH_LONG).show();
            startActivity(intent1);
        }
    }

    @Override
    public void failure(String error) {

    }
    //更新页面TextView的方法
    private void updateLabel() {
        myMessageBirthdayUser.setText(fmtDateAndTime.format(dateAndTime.getTime()));
    }

    @OnClick({R.id.my_message_qianming, R.id.my_message_birthday_user,R.id.my_back,R.id.my_finish})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.my_message_qianming:
                Intent intent = new Intent(this, SignActivity.class);
                startActivity(intent);
                break;
            case R.id.my_message_birthday_user:
                //生成一个DatePickerDialog对象，并显示。显示的DatePickerDialog控件可以选择年月日，并设置
                new DatePickerDialog(MessageActivity.this,
                        d,
                        dateAndTime.get(Calendar.YEAR),
                        dateAndTime.get(Calendar.MONTH),
                        dateAndTime.get(Calendar.DAY_OF_MONTH)).show();
                break;
            case R.id.my_back:
                    startActivity(intent1);
                break;
            case R.id.my_finish:
                Map<String,String> map=new HashMap<>();
                map.put("nickName",name);
                map.put("sex",sex);
                map.put("signature",sign);
                map.put("birthday",date);
                map.put("email",email);
                Log.e("MMMM",map+"");
                iPresentermpl.postRequest(Api.PERFECTUSERINFO,map, MessageBean.class);
                break;
        }
    }
}
