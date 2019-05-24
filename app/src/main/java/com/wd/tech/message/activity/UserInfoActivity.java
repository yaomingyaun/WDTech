package com.wd.tech.message.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.wd.tech.R;
import com.wd.tech.app.Apis;
import com.wd.tech.message.bean.AddFrientBeans;
import com.wd.tech.message.bean.FindUserBeans;
import com.wd.tech.utils.presenter.IPresentermpl;
import com.wd.tech.utils.view.IView;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 用户详细信息
 */
public class UserInfoActivity extends AppCompatActivity  {

    @BindView(R.id.userActivity_photo)
    SimpleDraweeView userActivityPhoto;
    @BindView(R.id.userActivity_username)
    TextView userActivityUsername;
    @BindView(R.id.userActivity_number)
    TextView userActivityNumber;
    @BindView(R.id.userActivity_sex)
    TextView userActivitySex;
    @BindView(R.id.userActivity_phone)
    TextView userActivityPhone;
    @BindView(R.id.userActivity_email)
    TextView userActivityEmail;
    @BindView(R.id.userActivity_add)
    Button userActivityAdd;
    @BindView(R.id.userActivity_signature)
    TextView userActivitySignature;
    private FindUserBeans.ResultBean result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        FindUserBeans findUserBeans = (FindUserBeans) intent.getSerializableExtra("findUserBeans");
        result = findUserBeans.getResult();
        initData();
        userActivityAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserInfoActivity.this, AddFriendsActivity.class);
                //添加传送数据
                intent.putExtra("userid", Integer.parseInt(result.getUserId()));
                intent.putExtra("img", result.getHeadPic());
                intent.putExtra("name", result.getNickName());

                startActivity(intent);
                   }
        });
    }

    private void initData() {
        //用户名
        userActivityUsername.setText(result.getNickName());
        //头像
        userActivityPhoto.setImageURI(Uri.parse(result.getHeadPic()));
        //积分
        userActivityNumber.setText(result.getIntegral());
        //签名
        if (result.getSignature()==null) {
            userActivitySignature.setText("你的好友很懒，没有留下任何信息……");
        }
        {
            userActivitySignature.setText(result.getSignature());
        }
        //性别
        int i = Integer.parseInt(result.getSex());
        if (i == 1) {
            result.setSex("男");
        }
        if (i == 2) {
            result.setSex("女");
        }
        userActivitySex.setText(result.getSex());
        //手机号
        userActivityPhone.setText(result.getPhone());
        //邮箱
        if (result.getEmail()==null) {
           userActivityEmail.setText("暂无邮箱");
        } else {
            userActivityEmail.setText(result.getEmail());
        }

    }

}
