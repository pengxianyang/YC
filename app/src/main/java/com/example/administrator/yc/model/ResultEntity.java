package com.example.administrator.yc.model;

/**
 * Created by a on 2018/3/6.
 */

public class ResultEntity {
    int code;
    String message;
    public ResultEntity(int code,String message){
        this.code=code;
        this.message=message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
