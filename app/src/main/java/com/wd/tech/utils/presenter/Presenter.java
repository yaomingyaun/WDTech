package com.wd.tech.utils.presenter;

import com.wd.tech.utils.model.MCallBack;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface Presenter {
    //get请求
    void getRequest(String url, Class clazz);
    //post请求
    void postRequest(String url, Map<String, String> map, Class clazz);
    //delete请求
    void deleteRequest(String url, Class clazz);
    //put请求
    void putRequest(String url, Map<String, String> map, Class clazz);
    //上传头像
    void  postFile(String url, Map<String, String> map, Class clazz);

    void  postData(String url, Map<String, String> map, Class clazz);
    //图文上传
    void postImageConRequestIpresenter(String url, Map<String,String> params , File file, Class clazz);

    //添加请求
    void postaddfrient(String url,int firenduid,String remarks, Class clazz);
    //上传图文
    void imagepost(String url, HashMap<String, String> map, List<File> file , Class clazz);
}
