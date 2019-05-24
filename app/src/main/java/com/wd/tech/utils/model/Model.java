package com.wd.tech.utils.model;

import java.io.File;
import java.util.Map;

/**
 *
 * @详情 M层
 *
 * @创建日期 2019/5/9 9:30
 *
 */
public interface Model {
    //get请求
    void getRequest(String url, Class clazz, MCallBack mCallBack);
    //post请求
    void postRequest(String url, Map<String, String> map, Class clazz, MCallBack mCallBack);
    //delete请求
    void deleteRequest(String url, Class clazz, MCallBack mCallBack);
    //put请求
    void putRequest(String url, Map<String, String> map, Class clazz, MCallBack mCallBack);
    //上传头像
    void  postFile(String url, Map<String, String> map, Class clazz, MCallBack callBack);
    //post
    void  postData(String url, Map<String, String> map, Class clazz, MCallBack mCallBack);

    //上传图文
    void postImageConRequestModel(String url, Map<String,String> params, File file , Class clazz , MCallBack callBack);

    //添加好友
    void postaddfriend(String url,int firenduid,String remarks, Class clazz, MCallBack mCallBack);
}
