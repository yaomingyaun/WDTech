package com.wd.tech.activity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.wd.tech.R;
import com.wd.tech.app.MyApplication;
import com.wd.tech.bean.NewsReplaceBean;

import com.wd.tech.utils.netWork.Api;
import com.wd.tech.utils.presenter.IPresentermpl;
import com.wd.tech.utils.view.IView;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NewsMessageActivity extends AppCompatActivity implements IView {

@BindView(R.id.edit_num)
    TextView edit_num;
    @BindView(R.id.messagecontent)
    EditText messagecontent;
    @BindView(R.id.message_icon)
    ImageView message_icon;
    PopupWindow popupWindow;
    @BindView(R.id.messageok)
    TextView messageok;
    @BindView(R.id.message_qx)
            TextView message_qx;
    @BindView(R.id.messqge_yuyin)
    TextView messqge_yuyin;
    public  final static int VEDIO_KU = 101;
    IPresentermpl iPresentermpl;
    private final int REQUEST_PICK = 200;
    private final int REQUEST_CAMEAR = 100;
    private final int REQUEST_PICTRUE = 300;
    private final String PATH_FILE = Environment.getExternalStorageDirectory() + "/file.png";
    private final String path = Environment.getExternalStorageDirectory() + "/image.png";


    private String userId,sessionId;
    private SharedPreferences sharedPreferences;
    String[] permissions = new String[]{
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.INTERNET};
    List<String> mPermissionList = new ArrayList<>();
    private final int mRequestCode = 100;//权限请求码
    AlertDialog mPermissionDialog;
    String mPackName = "com.wd.tech";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_message);
        ButterKnife.bind(this);
        iPresentermpl=new IPresentermpl(this);
        //Appid

        sharedPreferences = getSharedPreferences("User", MODE_PRIVATE);
        userId = sharedPreferences.getString("userId", "");
        sessionId = sharedPreferences.getString("sessionId", "");
        //输入
        messagecontent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                edit_num.setText(String.valueOf(s.length()) + "/300");
                if (s.length() >= 300) {
                    Toast.makeText(NewsMessageActivity.this, "上限了，亲", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }




    @OnClick({R.id.message_icon,R.id.messageok,R.id.message_qx,R.id.messqge_yuyin})
        public  void  setlick(View view)
        {
                switch (view.getId())
                {
                    case R.id.message_icon:
                        mPermissionList.clear();//清空已经允许的没有通过的权限
                        //逐个判断是否还有未通过的权限
                        for (int i = 0;i<permissions.length;i++){
                            if (ContextCompat.checkSelfPermission(this,permissions[i])!=
                                    PackageManager.PERMISSION_GRANTED){
                                mPermissionList.add(permissions[i]);//添加还未授予的权限到mPermissionList中
                            }
                        }
                        //申请权限
                        if (mPermissionList.size()>0){//有权限没有通过，需要申请
                            ActivityCompat.requestPermissions(this,permissions,mRequestCode);
                        }else {
                            //权限已经都通过了，可以将程序继续打开了
                            getpop();
                        }
                        getpop();
                        break;
                    case R.id.messageok:
                        String name=messagecontent.getText().toString();
                        Map<String,String> map=new HashMap<>();
                        map.put("content",name);

                        File file=new File(PATH_FILE);

                        iPresentermpl.postImageConRequestIpresenter(Api.RELEASEPOST,map,file,NewsReplaceBean.class);
                        finish();
                        break;
                    case R.id.message_qx:
                            finish();
                        break;
                        //开启语音权限

                        default:break;
                }
        }


    private void showPermissionDialog() {
        if (mPermissionDialog == null) {
            mPermissionDialog = new AlertDialog.Builder(this)
                    .setMessage("已禁用权限，请手动授予")
                    .setPositiveButton("设置", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            cancelPermissionDialog();
                            Uri packageURI = Uri.parse("package:" + mPackName);
                            Intent intent = new Intent(Settings.
                                    ACTION_APPLICATION_DETAILS_SETTINGS, packageURI);
                            startActivity(intent);
                        }
                    })
                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //关闭页面或者做其他操作
                            cancelPermissionDialog();
                            NewsMessageActivity.this.finish();
                        }
                    })
                    .create();
        }
        mPermissionDialog.show();
    }
    private void cancelPermissionDialog() {
        mPermissionDialog.cancel();
    }

        //调用相机
    private void getpop() {
        View view = View.inflate(this, R.layout.alert_message_icon, null);
        LinearLayout photo = view.findViewById(R.id.phone);
        LinearLayout camera = view.findViewById(R.id.camera);
        LinearLayout xjqx = view.findViewById(R.id.xjqx);

        popupWindow=new PopupWindow(view,ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT,true);
        //点击外部 pop消失

        popupWindow.setTouchable(true);
        popupWindow.setOutsideTouchable(true);

        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        //位于相册下方
        popupWindow.showAtLocation(view,Gravity.BOTTOM,0,0);
        //点击吊起相册
        photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_PICK);
                intent.setType("video/*;image/*");
                startActivityForResult(intent,REQUEST_PICK);
                popupWindow.dismiss();
            }
        });
        //点击吊起相几
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(path)));
                startActivityForResult(intent, REQUEST_CAMEAR);
                popupWindow.dismiss();
            }
        });
        //点击取消
        xjqx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            popupWindow.dismiss();

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
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
            message_icon.setImageBitmap(bitmap);
        }
    }


    @Override
    public void success(Object data) {
        if(data instanceof NewsReplaceBean)
        {
            NewsReplaceBean newsReplaceBean= (NewsReplaceBean) data;
            if(newsReplaceBean.getMessage().equals("发布成功"))
            {
                Toast.makeText(this, "发布成功", Toast.LENGTH_SHORT).show();
            }else
            {
                Toast.makeText(this, "发布失败", Toast.LENGTH_SHORT).show();
            }
        }
    }
    @Override
    public void failure(String error) {

    }
}
