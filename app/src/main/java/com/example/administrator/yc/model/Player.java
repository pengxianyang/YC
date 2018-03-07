package com.example.administrator.yc.model;

/**
 * Created by Administrator on 2018/3/7 0007.
 */

public class Player {
    int credit;
    String head;
    String username;
    String password;
    String nickname;
    String mail;
    String phone;
    String address;

    public Player(int credit,String head,String username,String password,String nickname,String mail,String phone,String address){
        this.credit = credit;
        this.head = head;
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.mail = mail;
        this.phone = phone;
        this.address = address;
    }

    public int getCredit() {
        return credit;
    }


    public String getHead() {
        return head;
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

    public String getAddress() {
        return address;
    }

    public String getNickname() {
        return nickname;
    }
}
