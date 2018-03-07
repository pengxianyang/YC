package com.example.administrator.yc.model;

/**
 * Created by a on 2018/3/6.
 */

public class Administrator {
    int credit;
    String head;
    String username;
    String password;
    String mail;
    String phone;
    String field;
    String license;
    public Administrator(int credit,String head,String username,
                         String password,String mail,String phone,String field,String license){
        this.credit=credit;
        this.head=head;
        this.username=username;
        this.password=password;
        this.mail=mail;
        this.phone=phone;
        this.field=field;
        this.license=license;
    }
    public int getCredit() {
        return credit;
    }

    public String getField() {
        return field;
    }

    public String getHead() {
        return head;
    }

    public String getLicense() {
        return license;
    }

    public String getMail() {
        return mail;
    }

    public String getPassword() {
        return password;
    }

    public String getPhone() {
        return phone;
    }

    public String getUsername() {
        return username;
    }
}
