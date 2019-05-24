package com.wd.tech.utils.netWork;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;
import android.widget.GridView;

import com.wd.tech.app.MyApplication;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RetorfitManager {
    public static RetorfitManager retorfitManager;
    public final String BASE_URL="https://mobile.bwstudent.com/techApi/";
    public BaseApis baseApis;
    private MultipartBody build;
    public static synchronized RetorfitManager getInstace(){
        if(retorfitManager==null){
            retorfitManager=new RetorfitManager();
        }
        return retorfitManager;
    }
    private RetorfitManager(){
        //拦截器
        HttpLoggingInterceptor interceptor=new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient mClient=new OkHttpClient.Builder()
                //读取超时
                .readTimeout(10,TimeUnit.SECONDS)
                //连接超时
                .connectTimeout(10,TimeUnit.SECONDS)
                //写超时
                .writeTimeout(10,TimeUnit.SECONDS)
                //添加拦截器
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request original=chain.request();
                        //取出保存的userID，sessionID
                        SharedPreferences mSharedPreferences=MyApplication.getContext().getSharedPreferences("User",Context.MODE_PRIVATE);
                        String userId = mSharedPreferences.getString("userId","");
                        String sessionId = mSharedPreferences.getString("sessionId", "");
                        String akk="0110010010000";
                        String ak = mSharedPreferences.getString("ak", akk);
                        Request.Builder builder1 = original.newBuilder();
                        builder1.method(original.method(),original.body());
                        builder1.addHeader("ak",ak);
                        if(!TextUtils.isEmpty(userId)&&!TextUtils.isEmpty(sessionId)){
                            builder1.addHeader("userId",userId);
                            builder1.addHeader("sessionId",sessionId);
                            builder1.addHeader("ak",ak);
                        }

                        Request request = builder1.build();

                        return chain.proceed(request);
                    }
                })
                .build();

        //Retrofit的创建
        Retrofit mRetrofit=new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .client(mClient)
                .build();
        baseApis=mRetrofit.create(BaseApis.class);
    }
    //添加好友请求
    public RetorfitManager postaddfriend(String url, int firenduid,String remarks,ICallBack callBack){
        baseApis.postaddfriend(url,firenduid,remarks)
                //后执行在哪个线程
                .subscribeOn(Schedulers.io())
                //最终完成后执行在哪个线程
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getObserver(callBack));
        return retorfitManager;
    }
    //get请求
    public RetorfitManager getRequest(String url,ICallBack callBack){
        baseApis.getData(url)
                //后执行在哪个线程
                .subscribeOn(Schedulers.io())
                //最终完成后执行在哪个线程
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getObserver(callBack));
        return retorfitManager;
    }
    //post请求
    public RetorfitManager postRequest(String url, Map<String,String> map,ICallBack iCallBack){
        if (map==null){
            map=new HashMap<>();
        }
        baseApis.postRequest(url,map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getObserver(iCallBack));
        return retorfitManager;
    }

    //post
    public  RetorfitManager postData(String url, Map<String,String> map,ICallBack setOkHttp)
    {
        if(map==null)
        {
            map=new HashMap<>();
        }
        baseApis.postData(url, map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getObserver(setOkHttp));
        return  retorfitManager;
    }
    //delete请求
    public RetorfitManager deleteRequest(String url,ICallBack callBack){
        baseApis.deleteRequest(url)
                //后执行在哪个线程
                .subscribeOn(Schedulers.io())
                //最终完成后执行在哪个线程
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getObserver(callBack));
        return retorfitManager;

    }
    //put请求
    public RetorfitManager putRequest(String url, Map<String,String> map,ICallBack callBack){
        if (map==null){
            map=new HashMap<>();
        }
        baseApis.putRequest(url,map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getObserver(callBack));
        return retorfitManager;
    }
    //上传头像
    public void postFile(String url, Map<String, String> map, ICallBack iCallBack) {
        if (map == null) {
            map = new HashMap<>();
        }
        MultipartBody multipartBody = filesToMultipartBody(map);
        baseApis.postFile(url, multipartBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getObserver(iCallBack));
    }
    //上传
    public static MultipartBody filesToMultipartBody(Map<String,String> map) {
        MultipartBody.Builder builder = new MultipartBody.Builder();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            File file = new File(entry.getValue());
            builder.addFormDataPart(entry.getKey(), "图片1.png",
                    RequestBody.create(MediaType.parse("multipart/form-data"), file));
        }

        builder.setType(MultipartBody.FORM);
        Log.i("nnn",builder+"");
        MultipartBody multipartBody = builder.build();
        return multipartBody;
    }
    //图片文字上传
    //图文上传
    public void postimagecon(String url,Map<String,String> params, File file,ICallBack callBack){

        RequestBody requestBody=RequestBody.create(MediaType.parse("multipart/form-data"),file);
        MultipartBody.Part filePart=MultipartBody.Part.createFormData("image",file.getName(),requestBody);
        baseApis.postImageContent(url,params,filePart)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getObserver(callBack));


    }
    //发表帖子
    public void imagepost(String url, HashMap<String,String> map, List<File> file, ICallBack callBack){
        if(file.size()>0){
            MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
            for (int i = 0; i < file.size(); i++) {
                builder.addFormDataPart("file", file.get(i).getName(), RequestBody.create(MediaType.parse("image/*"), file.get(i)));
            }
            build = builder.build();
        }else{
            build=new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("","")
                    .build();
        }

        baseApis.imagepost(url, map,build)
                .subscribeOn(Schedulers.io())//io子线程
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getObserver(callBack));
    }

    private Observer getObserver(final ICallBack callBack) {
        //Rxjava
        Observer observer=new Observer<ResponseBody>() {

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                if (callBack!=null){
                    callBack.failure(e.getMessage());
                    Log.i("Tag",e.getMessage());
                }
            }

            @Override
            public void onNext(ResponseBody responseBody) {
                try {
                    String result = responseBody.string();
                    if (callBack!=null){
                        callBack.success(result);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    if (callBack!=null){
                        callBack.failure(e.getMessage());
                    }
                }
            }
        };
        return observer;
    }



    public interface ICallBack{
        void success(String result);
        void failure(String error);
    }
}
