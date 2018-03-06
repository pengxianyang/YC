package com.example.administrator.yc.retro_interface;

import com.example.administrator.yc.model.Administrator;
import com.example.administrator.yc.model.ResultEntity;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by a on 2018/3/6.
 */

public class Retro {
    private Service service;
    public Retro() {
        String baseUrl="http://120.79.217.219:8080/Android/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        service=retrofit.create(Service.class);
    }
    public Observable<ResultEntity>aRegister(String username, String password, String mail,
                                             String phone){
        Administrator administrator=new Administrator(0,0,"",username,password,
                mail,phone,"","");
        return service.aRegister(administrator)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
