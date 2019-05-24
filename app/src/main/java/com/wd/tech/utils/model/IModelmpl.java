package com.wd.tech.utils.model;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.google.gson.Gson;
import com.wd.tech.app.MyApplication;
import com.wd.tech.utils.netWork.RetorfitManager;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.security.auth.callback.Callback;

import retrofit2.Retrofit;

public class IModelmpl implements Model{
//    Context mBase;

    //get请求
    @Override
    public void getRequest(String url, final Class clazz, final MCallBack mCallBack) {
//        if (!isNetWork()){
//            mCallBack.failure("网络状态不可用");
//            return;
//        }
//        connectionReceiver=connectionReceiver;
//        IntentFilter intentFilter = new IntentFilter();
//        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
//        registerReceiver(connectionReceiver, intentFilter);
        RetorfitManager.getInstace().getRequest(url, new RetorfitManager.ICallBack() {
            @Override
            public void success(String result) {
                Object o = new Gson().fromJson(result, clazz);
                mCallBack.success(o);
            }

            @Override
            public void failure(String error) {
              mCallBack.failure(error);
            }
        });
    }

    //post请求
    @Override
    public void postRequest(String url, Map<String, String> map, final Class clazz, final MCallBack mCallBack) {
//        if (!isNetWork()){
//            mCallBack.failure("网络状态不可用");
//            return;
//        }
//        connectionReceiver=connectionReceiver;
//        IntentFilter intentFilter = new IntentFilter();
//        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
//        registerReceiver(connectionReceiver, intentFilter);
        RetorfitManager.getInstace().postRequest(url, map, new RetorfitManager.ICallBack() {
            @Override
            public void success(String result) {
                Object o = new Gson().fromJson(result, clazz);
                mCallBack.success(o);
            }

            @Override
            public void failure(String error) {
                mCallBack.failure(error);
            }
        });
    }
    //delete请求
    @Override
    public void deleteRequest(String url, final Class clazz, final MCallBack mCallBack) {
        if (!isNetWork()){
            mCallBack.failure("网络状态不可用");
            return;
        }
        RetorfitManager.getInstace().deleteRequest(url, new RetorfitManager.ICallBack() {
            @Override
            public void success(String result) {
                Object o = new Gson().fromJson(result, clazz);
                mCallBack.success(o);
            }

            @Override
            public void failure(String error) {
                mCallBack.failure(error);
            }
        });
    }
    //put请求
    @Override
    public void putRequest(String url, Map<String, String> map, final Class clazz, final MCallBack mCallBack) {
//        if (!isNetWork()){
//            mCallBack.failure("网络状态不可用");
//            return;
//        }
//        connectionReceiver=connectionReceiver;
//        IntentFilter intentFilter = new IntentFilter();
//        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
//        registerReceiver(connectionReceiver, intentFilter);
        RetorfitManager.getInstace().putRequest(url, map, new RetorfitManager.ICallBack() {
            @Override
            public void success(String result) {
                Object o = new Gson().fromJson(result, clazz);
                mCallBack.success(o);
            }

            @Override
            public void failure(String error) {
                mCallBack.failure(error);
            }
        });
    }
    //头像上传
    @Override
    public void postFile(String url, Map<String, String> map, final Class clazz, final MCallBack callBack) {
        if (!isNetWork()){
            callBack.failure("网络状态不可用");
            return;
        }
        RetorfitManager.getInstace().postFile(url, map, new RetorfitManager.ICallBack() {
            @Override
            public void success(String result) {
                Object o = new Gson().fromJson(result, clazz);
                callBack.success(o);
            }

            @Override
            public void failure(String error) {
                callBack.failure(error);
            }
        });
    }

    @Override
    public void postData(String url, Map<String, String> map, final Class clazz, final MCallBack mCallBack) {
        RetorfitManager.getInstace().postData(url, map, new RetorfitManager.ICallBack() {
            @Override
            public void success(String result) {
                Object o = new Gson().fromJson(result, clazz);
                mCallBack.success(o);
            }

            @Override
            public void failure(String error) {
                mCallBack.failure(error);
            }
        });
    }

    @Override
    public void postImageConRequestModel(String url, Map<String, String> params, File file, final Class clazz, final MCallBack callBack) {
        RetorfitManager.getInstace().postimagecon(url,params ,file, new RetorfitManager.ICallBack() {
            @Override
            public void success(String result) {
                Object o = new Gson().fromJson(result, clazz);
                callBack.success(o);
            }

            @Override
            public void failure(String error) {
                callBack.failure(error);
            }
        });

    }

//添加好友
    @Override
    public void postaddfriend(String url,int firenduid,String remarks, final Class clazz, final MCallBack mCallBack) {
        if (!isNetWork()){
            mCallBack.failure("网络状态不可用");
            return;
        }
        RetorfitManager.getInstace().postaddfriend(url, firenduid, remarks, new RetorfitManager.ICallBack() {
            @Override
            public void success(String result) {
                Object o = new Gson().fromJson(result, clazz);
                mCallBack.success(o);
            }

            @Override
            public void failure(String error) {

            }
        });
    }

    @Override
    public void imagepost(String url, HashMap<String, String> map, List<File> file, final Class clazz, final MCallBack callBack) {
        RetorfitManager.getInstace().imagepost(url, map, file, new RetorfitManager.ICallBack() {
            @Override
            public void success(String result) {
                Object o = new Gson().fromJson(result, clazz);
                callBack.success(o);
            }

            @Override
            public void failure(String error) {

            }
        });
    }

    //判断网络状态
    public static boolean isNetWork(){
        ConnectivityManager cm = (ConnectivityManager) MyApplication.instance.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = cm.getActiveNetworkInfo();
        return activeNetworkInfo!=null && activeNetworkInfo.isAvailable();
    }
}
