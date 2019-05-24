package com.wd.tech.user.activity;

import android.app.AlertDialog;
import android.content.ContentUris;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.wd.tech.R;

import com.wd.tech.ShowActivity;

import com.wd.tech.app.MyApplication;
import com.wd.tech.bean.LoginBean;

import com.wd.tech.face.DetecterActivity;
import com.wd.tech.face.FaceActivity;
import com.wd.tech.face.RegisterActivity;
import com.wd.tech.utils.base.RsaCoder;
import com.wd.tech.utils.netWork.Api;
import com.wd.tech.utils.presenter.IPresentermpl;
import com.wd.tech.utils.view.IView;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTouch;

public class LoginActivity extends AppCompatActivity implements IView {
    @BindView(R.id.text_sign)
    TextView text_sign;
    @BindView(R.id.login_phone_num)
    EditText login_phone_num;
    @BindView(R.id.login_phone_pwd)
    EditText login_phone_pwd;
    @BindView(R.id.login)
    Button login;
    @BindView(R.id.image_eye)
    ImageView image_eye;
    @BindView(R.id.weixin)
            ImageView weixin;
    @BindView(R.id.renlian)
            ImageView renlian;
    String pwd2;
    IPresentermpl iPresentermpl;
    private String APP_ID="wx4c96b6b8da494224";
    private IWXAPI api;
    String a;
    private final String TAG = this.getClass().toString();

    private static final int REQUEST_CODE_IMAGE_CAMERA = 1;
    private static final int REQUEST_CODE_IMAGE_OP = 2;
    private static final int REQUEST_CODE_OP = 3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        iPresentermpl=new IPresentermpl(this);
        api = WXAPIFactory.createWXAPI(this, APP_ID, false);
        api.registerApp(APP_ID);

    }
    @OnClick({R.id.text_sign,R.id.login,R.id.weixin,R.id.renlian})
    public  void  setLick(View view)
    {
        switch (view.getId())
        {
            case R.id.text_sign:
                Intent intent=new Intent(LoginActivity.this,SingActivity.class);
                startActivity(intent);
                break;
            case R.id.login:
                String phone=login_phone_num.getText().toString();
                String pwd=login_phone_pwd.getText().toString();
                try {
                    pwd2=RsaCoder.encryptByPublicKey(pwd);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Map<String,String> map=new HashMap<>();
                map.put("phone",phone);
                map.put("pwd",pwd2);
                iPresentermpl.postRequest(Api.LOGIN,map,LoginBean.class);
                break;


            case R.id.weixin:
                if (!api.isWXAppInstalled()) {
                    Toast.makeText(LoginActivity.this,"您还未安装微信客户端",Toast.LENGTH_LONG).show();
                    return;
                }
                //获取code
                SendAuth.Req req = new SendAuth.Req();
                req.scope = "snsapi_userinfo";
                req.state = "wechat_sdk";
                api.sendReq(req);
                break;
            case R.id.renlian:
                if( ((MyApplication)LoginActivity.this.getApplicationContext()).mFaceDB.mRegister==null) {
                    Toast.makeText(this, "没有注册人脸，请先注册！", Toast.LENGTH_SHORT).show();
                } else {
                    new AlertDialog.Builder(this)
                            .setTitle("请选择相机")
                            .setIcon(android.R.drawable.ic_dialog_info)
                            .setItems(new String[]{"后置相机", "前置相机"}, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    startDetector(which);
                                }
                            })
                            .show();
                }

                break;
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_IMAGE_OP && resultCode == RESULT_OK) {
            Uri mPath = data.getData();
            String file = getPath(mPath);
            Bitmap bmp = MyApplication.decodeImage(file);
            if (bmp == null || bmp.getWidth() <= 0 || bmp.getHeight() <= 0 ) {
                Log.e(TAG, "error");
            } else {
                Log.i(TAG, "bmp [" + bmp.getWidth() + "," + bmp.getHeight());
            }
            startRegister(bmp, file);
        } else if (requestCode == REQUEST_CODE_OP) {
            Log.i(TAG, "RESULT =" + resultCode);
            if (data == null) {
                return;
            }
            Bundle bundle = data.getExtras();
            String path = bundle.getString("imagePath");
            Log.i(TAG, "path="+path);
        } else if (requestCode == REQUEST_CODE_IMAGE_CAMERA && resultCode == RESULT_OK) {
            Uri mPath = ((MyApplication)(LoginActivity.this.getApplicationContext())).getCaptureImage();
            String file = getPath(mPath);
            Bitmap bmp = MyApplication.decodeImage(file);
            startRegister(bmp, file);
        }
    }
    /**
     * @param uri
     * @return
     */
    private String getPath(Uri uri) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (DocumentsContract.isDocumentUri(this, uri)) {
                // ExternalStorageProvider
                if (isExternalStorageDocument(uri)) {
                    final String docId = DocumentsContract.getDocumentId(uri);
                    final String[] split = docId.split(":");
                    final String type = split[0];

                    if ("primary".equalsIgnoreCase(type)) {
                        return Environment.getExternalStorageDirectory() + "/" + split[1];
                    }

                    // TODO handle non-primary volumes
                } else if (isDownloadsDocument(uri)) {

                    final String id = DocumentsContract.getDocumentId(uri);
                    final Uri contentUri = ContentUris.withAppendedId(
                            Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));

                    return getDataColumn(this, contentUri, null, null);
                } else if (isMediaDocument(uri)) {
                    final String docId = DocumentsContract.getDocumentId(uri);
                    final String[] split = docId.split(":");
                    final String type = split[0];

                    Uri contentUri = null;
                    if ("image".equals(type)) {
                        contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                    } else if ("video".equals(type)) {
                        contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                    } else if ("audio".equals(type)) {
                        contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                    }

                    final String selection = "_id=?";
                    final String[] selectionArgs = new String[] {
                            split[1]
                    };

                    return getDataColumn(this, contentUri, selection, selectionArgs);
                }
            }
        }
        String[] proj = { MediaStore.Images.Media.DATA };
        Cursor actualimagecursor = this.getContentResolver().query(uri, proj, null, null, null);
        int actual_image_column_index = actualimagecursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        actualimagecursor.moveToFirst();
        String img_path = actualimagecursor.getString(actual_image_column_index);
        String end = img_path.substring(img_path.length() - 4);
        if (0 != end.compareToIgnoreCase(".jpg") && 0 != end.compareToIgnoreCase(".png")) {
            return null;
        }
        return img_path;
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is ExternalStorageProvider.
     */
    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is DownloadsProvider.
     */
    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is MediaProvider.
     */
    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    /**
     * Get the value of the data column for this Uri. This is useful for
     * MediaStore Uris, and other file-based ContentProviders.
     *
     * @param context The context.
     * @param uri The Uri to query.
     * @param selection (Optional) Filter used in the query.
     * @param selectionArgs (Optional) Selection arguments used in the query.
     * @return The value of the _data column, which is typically a file path.
     */
    public static String getDataColumn(Context context, Uri uri, String selection,
                                       String[] selectionArgs) {

        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {
                column
        };

        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs,
                    null);
            if (cursor != null && cursor.moveToFirst()) {
                final int index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(index);
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return null;
    }

    /**
     * @param mBitmap
     */
    private void startRegister(Bitmap mBitmap, String file) {
        Intent it = new Intent(LoginActivity.this, RegisterActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("imagePath", file);
        it.putExtras(bundle);
        startActivityForResult(it, REQUEST_CODE_OP);
    }
    private void startDetector(int camera) {
        Intent it = new Intent(LoginActivity.this, DetecterActivity.class);
        it.putExtra("Camera", camera);
        startActivityForResult(it, REQUEST_CODE_OP);
    }

    //微信登录
    @OnClick(R.id.weixin)
    public void setWX(){

        if (!api.isWXAppInstalled()) {
            Toast.makeText(LoginActivity.this,"您还未安装微信客户端",Toast.LENGTH_LONG).show();
            return;
        }
        //获取code
        SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = "wechat_sdk";
        api.sendReq(req);

    }
    //密码显示或隐藏
    @OnTouch(R.id.image_eye)
    public  boolean setLogin_pass(View v,MotionEvent event)
    {

        //按下时  密码显示
        if(event.getAction()==MotionEvent.ACTION_DOWN)
        {
            login_phone_pwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());

        }
        //抬起时  密码隐藏
        else if(event.getAction()==MotionEvent.ACTION_UP)
        {
            login_phone_pwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
        }
        return true;
    }
    @Override
    public void success(Object data) {
        if(data instanceof LoginBean)
        {
            LoginBean loginBean= (LoginBean) data;
            if(loginBean.getMessage().equals("登录成功"))
            {
                SharedPreferences sharedPreferences=getSharedPreferences("User",MODE_PRIVATE);
                sharedPreferences.edit().putString("userId",loginBean.getResult().getUserId()).putString("sessionId",loginBean.getResult().getSessionId()).commit();
                Log.i("TTTTATTATATTAT",loginBean.getResult().getUserId()+"");
                Log.i("TTTTATTATATTAT",loginBean.getResult().getSessionId()+"");
                startActivity(new Intent(this,ShowActivity.class));
                Toast.makeText(this, loginBean.getMessage()+"", Toast.LENGTH_SHORT).show();
            }else
            {
                Toast.makeText(this, loginBean.getMessage()+"", Toast.LENGTH_SHORT).show();
            }
        }
    }
    @Override
    public void failure(String error) {

    }
}
