package com.example.myapplication.teacher.bean;

public class Teacher {
    private int id;
    private String name;
    private String position;
    private String phone;
    private String picture;
    private String motto;

    public Teacher() {
    }

    public Teacher(int id, String name, String position, String phone, String picture, String motto) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.phone = phone;
        this.picture = picture;
        this.motto = motto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getMotto() {
        return motto;
    }

    public void setMotto(String motto) {
        this.motto = motto;
    }
}
