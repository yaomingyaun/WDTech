package com.wd.tech.utils.netWork;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;
import rx.Observable;

public interface BaseApis {
    @GET
    Observable<ResponseBody> getData(@Url String url);

    //表单
    @POST
    @FormUrlEncoded
    Observable<ResponseBody> postRequest(@Url String url, @FieldMap Map<String, String> map);

    //上传头像
    @POST
    Observable<ResponseBody> postFile(@Url String url, @Body MultipartBody multipartBody);

    @DELETE
    Observable<ResponseBody> deleteRequest(@Url String url);

    @PUT
    Observable<ResponseBody> putRequest(@Url String url, @QueryMap Map<String, String> parmas);


    @POST
    Observable<ResponseBody> postData(@Url String url, @QueryMap Map<String, String> map);

    //图片文字上传
    @POST
    @Multipart
    Observable<ResponseBody> postImageContent(@Url String url ,@QueryMap Map<String, String> map ,@Part MultipartBody.Part parts);


    Observable<ResponseBody> put(@Url String url, @QueryMap Map<String, String> parmas);
//添加好友
    @POST
    @FormUrlEncoded
    Observable<ResponseBody> postaddfriend(@Url String url, @Field("friendUid") int finenduid, @Field("remark") String remark );

}
