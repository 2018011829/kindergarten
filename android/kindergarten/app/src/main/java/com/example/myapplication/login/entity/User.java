package com.example.myapplication.login.entity;


public class User {
    private int id;
    private String phone;
    private String password;
    private String nickname;
    private String avatar;
    private String identity;

    public User() {
        super();
    }

    public User(int id, String phone, String password, String nickName, String avatar, String identity) {
        super();
        this.id = id;
        this.phone = phone;
        this.password = password;
        this.nickname = nickName;
        this.avatar = avatar;
        this.identity = identity;
    }

    public User(String phone, String password, String nickName, String avatar, String identity) {
        super();
        this.phone = phone;
        this.password = password;
        this.nickname = nickName;
        this.avatar = avatar;
        this.identity = identity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickName) {
        this.nickname = nickName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", phone=" + phone + ", password=" + password + ", nickname=" + nickname + ", avatar="
                + avatar + ", identity=" + identity + "]";
    }

}
