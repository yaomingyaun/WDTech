package com.wd.tech.utils.presenter;

import android.util.Log;

import com.wd.tech.utils.model.IModelmpl;
import com.wd.tech.utils.model.MCallBack;
import com.wd.tech.utils.view.IView;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IPresentermpl implements Presenter{
    private IView iView;
    private IModelmpl iModel;

    public IPresentermpl(IView iView) {
        this.iView = iView;
        iModel=new IModelmpl();
    }
    //get请求
    @Override
    public void getRequest(String url, Class clazz) {
        iModel.getRequest(url, clazz, new MCallBack() {
            @Override
            public void success(Object object) {
                iView.success(object);
            }

            @Override
            public void failure(String error) {
                iView.failure(error);
            }
        });
    }
    //post请求
    @Override
    public void postRequest(String url, Map<String, String> map, Class clazz) {
        iModel.postRequest(url, map, clazz, new MCallBack() {
            @Override
            public void success(Object object) {
                iView.success(object);
            }

            @Override
            public void failure(String error) {
                iView.failure(error);
            }
        });
    }
    //delete请求
    @Override
    public void deleteRequest(String url, Class clazz) {
        iModel.deleteRequest(url, clazz, new MCallBack() {
            @Override
            public void success(Object object) {
                iView.success(object);
            }
            @Override
            public void failure(String error) {
                iView.failure(error);
            }
        });
    }
    //put请求
    @Override
    public void putRequest(String url, Map<String, String> map, Class clazz) {
        iModel.putRequest(url, map, clazz, new MCallBack() {
            @Override
            public void success(Object object) {
                iView.success(object);
            }

            @Override
            public void failure(String error) {
                iView.failure(error);
            }
        });
    }
    //头像上传
    @Override
    public void postFile(String url, Map<String, String> map, Class clazz) {
        iModel.postFile(url, map, clazz, new MCallBack() {
            @Override
            public void success(Object object) {
                iView.success(object);
            }

            @Override
            public void failure(String error) {
                iView.failure(error);
            }
        });
    }

    @Override
    public void postData(String url, Map<String, String> map, Class clazz) {
        iModel.postData(url, map, clazz, new MCallBack() {
            @Override
            public void success(Object object) {
                iView.success(object);
            }

            @Override
            public void failure(String error) {
                iView.failure(error);
            }
        });
    }

    @Override
    public void postImageConRequestIpresenter(String url, Map<String, String> params, File file, Class clazz) {
        iModel.postImageConRequestModel(url, params, file, clazz, new MCallBack() {
            @Override
            public void success(Object object) {
                iView.success(object);
            }

            @Override
            public void failure(String error) {
            iView.failure(error);
            }
        });
    }


    @Override
    public void postaddfrient(String url,int firenduid,String remarks, Class clazz) {
        iModel.postaddfriend(url, firenduid, remarks, clazz, new MCallBack() {
            @Override
            public void success(Object object) {
                Log.e("wzx", "chenggong " );
                iView.success(object);
            }

            @Override
            public void failure(String error) {
                iView.failure(error);
            }
        });
    }

    @Override
    public void imagepost(String url, HashMap<String, String> map, List<File> file, Class clazz) {
        iModel.imagepost(url, map, file, clazz, new MCallBack() {
            @Override
            public void success(Object object) {
                iView.success(object);
            }

            @Override
            public void failure(String error) {
                iView.failure(error);

            }
        });
    }

    //防止内存泄漏
    public void deatch(){
      if(iModel!=null){
          iModel=null;
      }
      if(iView!=null){
          iView=null;
      }
    }
}
