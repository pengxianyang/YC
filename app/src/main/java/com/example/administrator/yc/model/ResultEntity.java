package com.example.administrator.yc.model;

/**
 * Created by a on 2018/3/6.
 */
/*返回结果，code为结果码，message为结果
* 注册：0-注册失败，1-注册成功；
* 登录：0-用户名或密码错误，1-管理员登录成功，2-运动员登录成功；
* */
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
