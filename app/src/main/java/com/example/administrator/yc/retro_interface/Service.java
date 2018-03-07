package com.example.administrator.yc.retro_interface;

import com.example.administrator.yc.model.Administrator;
import com.example.administrator.yc.model.Player;
import com.example.administrator.yc.model.ResultEntity;

import rx.Observable;

import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by a on 2018/3/6.
 */

public interface Service {
    @POST("RegisterAction?func=0")
    Observable<ResultEntity> aRegister(@Body Administrator administrator);

    @POST("RegisterAction?func=1")
    Observable<ResultEntity> pRegister(@Body Player player);
}
