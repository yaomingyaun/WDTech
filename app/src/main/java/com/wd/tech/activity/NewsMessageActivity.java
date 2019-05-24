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
import com.wd.tech.adapter.FullyGridLayoutManager;
import com.wd.tech.adapter.GridImageAdapter;
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
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class NewsMessageActivity extends AppCompatActivity implements IView {

@BindView(R.id.edit_num)
    TextView edit_num;
    @BindView(R.id.messagecontent)
    EditText messagecontent;
    @BindView(R.id.recycler)
    RecyclerView recycler;
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
   // private final String path = Environment.getExternalStorageDirectory() + "/image.png";

    private int maxSelectNum = 9;
    private List<LocalMedia> selectList = new ArrayList<>();
    private GridImageAdapter adapter;
    private PopupWindow pop;
    private String path;
    private  List<File> files=new ArrayList<>();

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
        initWidget();
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




    @OnClick({R.id.recycler,R.id.messageok,R.id.message_qx,R.id.messqge_yuyin})
        public  void  setlick(View view)
        {
                switch (view.getId())
                {
                    case R.id.recycler:

//                        mPermissionList.clear();//清空已经允许的没有通过的权限
//                        //逐个判断是否还有未通过的权限
//                        for (int i = 0;i<permissions.length;i++){
//                            if (ContextCompat.checkSelfPermission(this,permissions[i])!=
//                                    PackageManager.PERMISSION_GRANTED){
//                                mPermissionList.add(permissions[i]);//添加还未授予的权限到mPermissionList中
//                            }
//                        }
//                        //申请权限
//                        if (mPermissionList.size()>0){//有权限没有通过，需要申请
//                            ActivityCompat.requestPermissions(this,permissions,mRequestCode);
//                        }else {
//                            //权限已经都通过了，可以将程序继续打开了
//                            getpop();
//                        }
//                        getpop();
                        break;
                    case R.id.messageok:
                        String name=messagecontent.getText().toString();

                        MultipartBody.Builder builder=new MultipartBody.Builder().setType(MultipartBody.FORM);
                        if(name.equals(""))
                        {
                            Toast.makeText(this, "请输入发表的内容！", Toast.LENGTH_SHORT).show();
                        }else
                        {
                            for (int i = 0; i <files.size() ; i++) {
                                builder.addFormDataPart("file",files.get(i).getName(),RequestBody.create(MediaType.parse("image/*"),files.get(i)));
                            }
                            HashMap<String,String> map=new HashMap<>();
                            map.put("content",name);
                            //MultipartBody build=builder.build();
                            iPresentermpl.imagepost(Api.RELEASEPOST,map,files,NewsReplaceBean.class);
                        }
                        finish();
                        break;
                    case R.id.message_qx:
                            finish();
                        break;
                        //开启语音权限

                        default:break;
                }
        }
public  void  initWidget()
{
    FullyGridLayoutManager manager = new FullyGridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false);
    recycler.setLayoutManager(manager);
    adapter = new GridImageAdapter(this, onAddPicClickListener);
    adapter.setList(selectList);
    adapter.setSelectMax(maxSelectNum);
    recycler.setAdapter(adapter);
    adapter.setOnItemClickListener(new GridImageAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(int position, View v) {
            if (selectList.size() > 0) {
                LocalMedia media = selectList.get(position);
                String pictureType = media.getPictureType();
                int mediaType = PictureMimeType.pictureToVideo(pictureType);
                switch (mediaType) {
                    case 1:
                        // 预览图片 可自定长按保存路径
                        //PictureSelector.create(MainActivity.this).externalPicturePreview(position, "/custom_file", selectList);
                        PictureSelector.create(NewsMessageActivity.this).externalPicturePreview(position, selectList);
                        Log.i("aaaaaa地址",selectList+"");
                        break;
                    case 2:
                        // 预览视频
                        PictureSelector.create(NewsMessageActivity.this).externalPictureVideo(media.getPath());
                        break;
                    case 3:
                        // 预览音频

                        PictureSelector.create(NewsMessageActivity.this).externalPictureAudio(media.getPath());
                        break;
                }
            }
        }
    });
}
    private GridImageAdapter.onAddPicClickListener onAddPicClickListener = new GridImageAdapter.onAddPicClickListener() {

        @Override
        public void onAddPicClick() {

            //第一种方式，弹出选择和拍照的dialog
            showPop();

            //第二种方式，直接进入相册，但是 是有拍照得按钮的
            //参数很多，根据需要添加

//            PictureSelector.create(MainActivity.this)
//                    .openGallery(PictureMimeType.ofImage())// 全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
//                    .maxSelectNum(maxSelectNum)// 最大图片选择数量
//                    .minSelectNum(1)// 最小选择数量
//                    .imageSpanCount(4)// 每行显示个数
//                    .selectionMode(PictureConfig.MULTIPLE)// 多选 or 单选PictureConfig.MULTIPLE : PictureConfig.SINGLE
//                    .previewImage(true)// 是否可预览图片
//                    .compressGrade(Luban.THIRD_GEAR)// luban压缩档次，默认3档 Luban.FIRST_GEAR、Luban.CUSTOM_GEAR
//                    .isCamera(true)// 是否显示拍照按钮
//                    .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
//                    //.setOutputCameraPath("/CustomPath")// 自定义拍照保存路径
//                    .enableCrop(true)// 是否裁剪
//                    .compress(true)// 是否压缩
//                    .compressMode(LUBAN_COMPRESS_MODE)//系统自带 or 鲁班压缩 PictureConfig.SYSTEM_COMPRESS_MODE or LUBAN_COMPRESS_MODE
//                    //.sizeMultiplier(0.5f)// glide 加载图片大小 0~1之间 如设置 .glideOverride()无效
//                    .glideOverride(160, 160)// glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
//                    .withAspectRatio(1, 1)// 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
//                    //.selectionMedia(selectList)// 是否传入已选图片
//                    //.previewEggs(false)// 预览图片时 是否增强左右滑动图片体验(图片滑动一半即可看到上一张是否选中)
//                    //.cropCompressQuality(90)// 裁剪压缩质量 默认100
//                    //.compressMaxKB()//压缩最大值kb compressGrade()为Luban.CUSTOM_GEAR有效
//                    //.compressWH() // 压缩宽高比 compressGrade()为Luban.CUSTOM_GEAR有效
//                    //.cropWH()// 裁剪宽高比，设置如果大于图片本身宽高则无效
//                    .rotateEnabled(false) // 裁剪是否可旋转图片
//                    //.scaleEnabled()// 裁剪是否可放大缩小图片
//                    //.recordVideoSecond()//录制视频秒数 默认60s
//                    .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
        }
    };

    private void showPop() {
        View bottomView = View.inflate(NewsMessageActivity.this, R.layout.layout_bottom_dialog, null);
        TextView mAlbum = (TextView) bottomView.findViewById(R.id.tv_album);
        TextView mCamera = (TextView) bottomView.findViewById(R.id.tv_camera);
        TextView mCancel = (TextView) bottomView.findViewById(R.id.tv_cancel);

        pop = new PopupWindow(bottomView, -1, -2);
        pop.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        pop.setOutsideTouchable(true);
        pop.setFocusable(true);
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 0.5f;
        getWindow().setAttributes(lp);
        pop.setOnDismissListener(new PopupWindow.OnDismissListener() {

            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 1f;
                getWindow().setAttributes(lp);
            }
        });
        pop.setAnimationStyle(R.style.main_menu_photo_anim);
        pop.showAtLocation(getWindow().getDecorView(), Gravity.BOTTOM, 0, 0);

        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.tv_album:
                        //相册
                        PictureSelector.create(NewsMessageActivity.this)
                                .openGallery(PictureMimeType.ofImage())
                                .maxSelectNum(maxSelectNum)
                                .minSelectNum(1)
                                .imageSpanCount(4)
                                .selectionMode(PictureConfig.MULTIPLE)
                                .forResult(PictureConfig.CHOOSE_REQUEST);
                        break;
                    case R.id.tv_camera:
                        //拍照
                        PictureSelector.create(NewsMessageActivity.this)
                                .openCamera(PictureMimeType.ofImage())
                                .forResult(PictureConfig.CHOOSE_REQUEST);
                        break;
                    case R.id.tv_cancel:
                        //取消
                        //closePopupWindow();
                        break;
                }
                closePopupWindow();
            }
        };

        mAlbum.setOnClickListener(clickListener);
        mCamera.setOnClickListener(clickListener);
        mCancel.setOnClickListener(clickListener);
    }

    public void closePopupWindow() {
        if (pop != null && pop.isShowing()) {
            pop.dismiss();
            pop = null;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        List<LocalMedia> images;
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片选择结果回调

                    images = PictureSelector.obtainMultipleResult(data);
                    selectList.addAll(images);

//                    selectList = PictureSelector.obtainMultipleResult(data);
                    // 例如 LocalMedia 里面返回三种path
                    // 1.media.getPath(); 为原图path
                    // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true
                    // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true
                    // 如果裁剪并压缩了，以取压缩路径为准，因为是先裁剪后压缩的

                    for (int i = 0; i < selectList.size(); i++) {
                        path = selectList.get(i).getPath();
                        files.add(new File(path));
                    }
                    adapter.setList(selectList);
                    adapter.notifyDataSetChanged();
                    break;
            }
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
//    private void getpop() {
//        View view = View.inflate(this, R.layout.alert_message_icon, null);
//        LinearLayout photo = view.findViewById(R.id.phone);
//        LinearLayout camera = view.findViewById(R.id.camera);
//        LinearLayout xjqx = view.findViewById(R.id.xjqx);
//
//        popupWindow=new PopupWindow(view,ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT,true);
//        //点击外部 pop消失
//
//        popupWindow.setTouchable(true);
//        popupWindow.setOutsideTouchable(true);
//
//        popupWindow.setBackgroundDrawable(new BitmapDrawable());
//        //位于相册下方
//        popupWindow.showAtLocation(view,Gravity.BOTTOM,0,0);
//        //点击吊起相册
//        photo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(Intent.ACTION_PICK);
//                intent.setType("video/*;image/*");
//                startActivityForResult(intent,REQUEST_PICK);
//                popupWindow.dismiss();
//            }
//        });
//        //点击吊起相几
//        camera.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(path)));
//                startActivityForResult(intent, REQUEST_CAMEAR);
//                popupWindow.dismiss();
//            }
//        });
//        //点击取消
//        xjqx.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            popupWindow.dismiss();
//
//            }
//        });
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == REQUEST_CAMEAR) {
//            //打开裁剪
//            Intent intent = new Intent("com.android.camera.action.CROP");
//            //将图片设置给裁剪
//            intent.setDataAndType(Uri.fromFile(new File(path)), "image/*");
//            //设置是否支持裁剪
//            intent.putExtra("CROP", true);
//            //设置宽高比
//            intent.putExtra("aspectX", 1);
//            intent.putExtra("aspectY", 1);
//            //设置输出后图片大小
//            intent.putExtra("outputX", 100);
//            intent.putExtra("outputY", 100);
//            //返回到data
//            intent.putExtra("return-data", true);
//            //启动
//            startActivityForResult(intent, REQUEST_PICTRUE);
//        }
//        if(requestCode==REQUEST_PICK)
//        {
//            Intent intent=new Intent("com.android.camera.action.CROP");
//            Uri uri=data.getData();
//            intent.setDataAndType(uri,"image/*");
//            //设置是否可裁剪
//            intent.putExtra("CROP", true);
//            //设置宽高比
//            intent.putExtra("aspectX", 1);
//            intent.putExtra("aspectY", 1);
//            //设置输出
//            intent.putExtra("outputX", 100);
//            intent.putExtra("outputY", 100);
//            //返回data
//            intent.putExtra("return-data", true);
//            startActivityForResult(intent,REQUEST_PICTRUE);
//        }
//
//        if (requestCode == REQUEST_PICTRUE) {
//            Bitmap bitmap = data.getParcelableExtra("data");
//            try {
//                MyApplication.saveBitmap(bitmap, PATH_FILE, 50);
//            } catch (IOException e) {
//                e.printStackTrace();
//                Log.i("TAG", e.getMessage());
//            }
//            message_icon.setImageBitmap(bitmap);
//        }
//    }
//

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
