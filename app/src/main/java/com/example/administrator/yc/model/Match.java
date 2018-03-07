package com.example.administrator.yc.model;

/**
 * Created by a on 2018/3/7.
 */

public class Match {
    int id;
    String creator;
    String type;
    int field_id;
    String field_name;
    String time;
    int total;
    int currentNum;
    String status;
    String result;
    public Match(int id,int total,String creator,String type,int field_id,String field_name,String time,
                 int currentNum,String status,String result){
        this.id=id;
        this.total=total;
        this.creator=creator;
        this.type=type;
        this.field_id=field_id;
        this.field_name=field_name;
        this.time=time;
        this.currentNum=currentNum;
        this.status=status;
        this.result=result;
    }

    public int getField_id() {
        return field_id;
    }

    public int getId() {
        return id;
    }

    public int getTotal() {
        return total;
    }

    public String getCreator() {
        return creator;
    }

    public int getCurrentNum() {
        return currentNum;
    }

    public String getField_name() {
        return field_name;
    }

    public String getResult() {
        return result;
    }

    public String getStatus() {
        return status;
    }

    public String getTime() {
        return time;
    }

    public String getType() {
        return type;
    }
}
