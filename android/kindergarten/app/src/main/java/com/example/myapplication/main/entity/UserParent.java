package com.example.myapplication.main.entity;

public class UserParent {
    private int id;
    private String phone;
    private String password;
    private String nickname;
    private String avatar;
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

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvator() {
        return avatar;
    }

    public void setAvator(String avator) {
        this.avatar = avator;
    }

    public UserParent() {
        super();
    }

    public UserParent(int id, String phone, String password, String nickname, String avator) {
        super();
        this.id = id;
        this.phone = phone;
        this.password = password;
        this.nickname = nickname;
        this.avatar = avator;
    }

    @Override
    public String toString() {
        return "UserParent [id=" + id + ", phone=" + phone + ", password=" + password + ", nickname=" + nickname
                + ", avator=" + avatar + "]";
    }

}
