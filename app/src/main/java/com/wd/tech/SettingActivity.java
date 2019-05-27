package com.wd.tech;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;

import com.wd.tech.app.MyApplication;
import com.wd.tech.bean.SCTXBean;
import com.wd.tech.face.FaceActivity;
import com.wd.tech.bean.infoByUserBean;
import com.wd.tech.utils.netWork.Api;
import com.wd.tech.utils.presenter.IPresentermpl;
import com.wd.tech.utils.view.IView;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingActivity extends AppCompatActivity implements IView {


    @BindView(R.id.my_back)
    ImageView myBack;
    @BindView(R.id.my_setting_img)
    SimpleDraweeView my_setting_img;
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
    private PopupWindow popupWindow;
    private final int REQUEST_PICK = 200;
    private final int REQUEST_CAMEAR = 100;
    private final int REQUEST_PICTRUE = 300;
    private final String PATH_FILE = Environment.getExternalStorageDirectory() + "/file.png";
    private final String path = Environment.getExternalStorageDirectory() + "/image.png";
    private  IPresentermpl iPresentermpl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
         iPresentermpl = new IPresentermpl(this);
        iPresentermpl.getRequest(Api.INFOBYUSERID, infoByUserBean.class);
    }
    @OnClick({R.id.my_setting_Face_user,R.id.my_setting_img})
    public  void  setlcik(View view)
    {
        switch (view.getId())
        {
            case R.id.my_setting_Face_user:
                Intent intent=new Intent(this,FaceActivity.class);
               startActivity(intent);
                break;
            case R.id.my_setting_img:
                if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M) {
                    String[] mStatenetwork = new String[]{
                            //写的权限
                            Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            //读的权限
                            Manifest.permission.READ_EXTERNAL_STORAGE,
                            //入网权限
                            Manifest.permission.ACCESS_NETWORK_STATE,
                            //WIFI权限
                            Manifest.permission.ACCESS_WIFI_STATE,
                            //读手机权限
                            Manifest.permission.READ_PHONE_STATE,
                            //网络权限
                            Manifest.permission.INTERNET,
                            //相机
                            Manifest.permission.CAMERA,
                            Manifest.permission.WRITE_APN_SETTINGS,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.ACCESS_NETWORK_STATE,
                    };
                    ActivityCompat.requestPermissions(this, mStatenetwork, 100);
                }

                getCamera();
                break;
                default:break;
        }
    }

    private void getCamera() {
        View view = View.inflate(this, R.layout.camera_pop, null);
        TextView photo = view.findViewById(R.id.photo);
        TextView camera = view.findViewById(R.id.camera);

        popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);

        //点击pop外部 隐藏
        popupWindow.setTouchable(true);
        //   popupWindow.setContentView(view);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.showAsDropDown(my_setting_img, 50, 50);
        photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,REQUEST_PICK);
                popupWindow.dismiss();
            }
        });
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(path)));
                startActivityForResult(intent, REQUEST_CAMEAR);
                popupWindow.dismiss();
            }
        });
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CAMEAR) {
            //打开裁剪
            Intent intent = new Intent("com.android.camera.action.CROP");
            //将图片设置给裁剪
            intent.setDataAndType(Uri.fromFile(new File(path)), "image/*");
            //设置是否支持裁剪
            intent.putExtra("CROP", true);
            //设置宽高比
            intent.putExtra("aspectX", 1);
            intent.putExtra("aspectY", 1);
            //设置输出后图片大小
            intent.putExtra("outputX", 100);
            intent.putExtra("outputY", 100);
            //返回到data
            intent.putExtra("return-data", true);
            //启动
            startActivityForResult(intent, REQUEST_PICTRUE);
        }
        if(requestCode==REQUEST_PICK)
        {
            Intent intent=new Intent("com.android.camera.action.CROP");
            Uri uri=data.getData();
            intent.setDataAndType(uri,"image/*");
            //设置是否可裁剪
            intent.putExtra("CROP", true);
            //设置宽高比
            intent.putExtra("aspectX", 1);
            intent.putExtra("aspectY", 1);
            //设置输出
            intent.putExtra("outputX", 100);
            intent.putExtra("outputY", 100);
            //返回data
            intent.putExtra("return-data", true);
            startActivityForResult(intent,REQUEST_PICTRUE);
        }
        if (requestCode == REQUEST_PICTRUE) {
            Bitmap bitmap = data.getParcelableExtra("data");
            try {
                MyApplication.saveBitmap(bitmap, PATH_FILE, 50);
            } catch (IOException e) {
                e.printStackTrace();
                Log.i("TAG", e.getMessage());
            }
            // File file = new File(PATH_FILE);
            Map<String, String> map = new HashMap<>();
            map.put("image", PATH_FILE);
            //上传
            getUserAvatar(map);

        }
    }

    private void getUserAvatar(Map<String,String> map) {
        iPresentermpl.postFile(Api.HEADPIC,map,SCTXBean.class);
    }

    @Override
    public void success(Object data) {

        if (data instanceof infoByUserBean) {
            infoByUserBean info = (infoByUserBean) data;
            Log.e("LLLL", info.getResult() + "");
            infoByUserBean.ResultBean result = info.getResult();
            Uri uri = Uri.parse(result.getHeadPic());
            my_setting_img.setImageURI(uri);
            mySettingNameUser.setText(result.getNickName());
            mySettingSexUser.setText(result.getSex());
            mySettingPhoneUser.setText(result.getPhone());
            mySettingEmailUser.setText(result.getEmail());
            mySettingIntegralUser.setText(result.getIntegral());
        }
        if(data instanceof SCTXBean)
        {
            SCTXBean sctxBean= (SCTXBean) data;
            if(sctxBean.getMessage().equals("上传成功"))
            {
                iPresentermpl.getRequest(Api.INFOBYUSERID, infoByUserBean.class);
                Uri uri=Uri.parse(sctxBean.getHeadPath());
                my_setting_img.setImageURI(uri);
            }else
            {
                Toast.makeText(this, "上传失败", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void failure(String error) {

    }
}
