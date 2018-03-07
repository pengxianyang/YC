package com.example.administrator.yc.retro_interface;

import com.example.administrator.yc.model.Administrator;
import com.example.administrator.yc.model.Match;
import com.example.administrator.yc.model.Player;
import com.example.administrator.yc.model.ResultEntity;

import java.util.Map;

import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import rx.Observable;

import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by a on 2018/3/6.
 */

public interface Service {

    @POST("RegisterAction?func=0")
    Observable<ResultEntity> aRegister(@Body Administrator administrator);

    /*登录：需要username,pass,func*/
    @POST("LoginAction")
    Observable<ResultEntity> Login(@QueryMap Map<String,String> map);

    @POST("RegisterAction?func=1")
    Observable<ResultEntity> pRegister(@Body Player player);

    @POST("GetFieldNameAction")
    Observable<ResultEntity>getFieldName(@Query("fieldId") String id );

    @POST("CreateMatchAction")
    Observable<ResultEntity>CreateMatch(@Body Match match);
}
