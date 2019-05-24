package com.wd.tech;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import com.wd.tech.face.FaceActivity;
import com.wd.tech.bean.infoByUserBean;
import com.wd.tech.utils.netWork.Api;
import com.wd.tech.utils.presenter.IPresentermpl;
import com.wd.tech.utils.view.IView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingActivity extends AppCompatActivity implements IView {


    @BindView(R.id.my_back)
    ImageView myBack;
    @BindView(R.id.my_setting_img)
    SimpleDraweeView mySettingImg;
    @BindView(R.id.my_setting_tou)
    RelativeLayout mySettingTou;
    @BindView(R.id.my_setting_name_user)
    TextView mySettingNameUser;
    @BindView(R.id.my_setting_name)
    RelativeLayout mySettingName;
    @BindView(R.id.my_setting_sex_user)
    TextView mySettingSexUser;
    @BindView(R.id.my_setting_sex)
    RelativeLayout mySettingSex;
    @BindView(R.id.my_setting_qianming)
    RelativeLayout mySettingQianming;
    @BindView(R.id.my_setting_birthday_user)
    TextView mySettingBirthdayUser;
    @BindView(R.id.my_setting_birthday)
    RelativeLayout mySettingBirthday;
    @BindView(R.id.my_setting_phone_user)
    TextView mySettingPhoneUser;
    @BindView(R.id.my_setting_phone)
    RelativeLayout mySettingPhone;
    @BindView(R.id.my_setting_Email_user)
    TextView mySettingEmailUser;
    @BindView(R.id.my_setting_Email)
    RelativeLayout mySettingEmail;
    @BindView(R.id.my_setting_integral_user)
    TextView mySettingIntegralUser;
    @BindView(R.id.my_setting_integral)
    RelativeLayout mySettingIntegral;
    @BindView(R.id.my_setting_VIP_user)
    TextView mySettingVIPUser;
    @BindView(R.id.my_setting_VIP)
    RelativeLayout mySettingVIP;
    @BindView(R.id.my_setting_Face_user)
    TextView mySettingFaceUser;
    @BindView(R.id.my_setting_Face)
    RelativeLayout mySettingFace;
    @BindView(R.id.my_setting_out_user)
    TextView mySettingOutUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
        IPresentermpl iPresentermpl = new IPresentermpl(this);
        iPresentermpl.getRequest(Api.INFOBYUSERID, infoByUserBean.class);
    }
    @OnClick({R.id.my_setting_Face_user})
    public  void  setlcik(View view)
    {
        switch (view.getId())
        {
            case R.id.my_setting_Face_user:
                Intent intent=new Intent(this,FaceActivity.class);
               startActivity(intent);
                break;
        }
    }
    @Override
    public void success(Object data) {

        if (data instanceof infoByUserBean) {
            infoByUserBean info = (infoByUserBean) data;
            Log.e("LLLL", info.getResult() + "");
            infoByUserBean.ResultBean result = info.getResult();
            Uri uri = Uri.parse(result.getHeadPic());
            mySettingImg.setImageURI(uri);
            mySettingNameUser.setText(result.getNickName());
            mySettingSexUser.setText(result.getSex());
            mySettingPhoneUser.setText(result.getPhone());
            mySettingEmailUser.setText(result.getEmail());
            mySettingIntegralUser.setText(result.getIntegral());
        }
    }

    @Override
    public void failure(String error) {

    }
}
