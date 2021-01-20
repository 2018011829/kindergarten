package com.example.myapplication.school.entity;

public class TwoPicture {

    private String description;
    private String picture1;
    private String picture2;

    public TwoPicture() {
    }

    public TwoPicture(String description, String picture1, String picture2) {
        this.description = description;
        this.picture1 = picture1;
        this.picture2 = picture2;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicture1() {
        return picture1;
    }

    public void setPicture1(String picture1) {
        this.picture1 = picture1;
    }

    public String getPicture2() {
        return picture2;
    }

    public void setPicture2(String picture2) {
        this.picture2 = picture2;
    }

    @Override
    public String toString() {
        return "TwoPicture{" +
                "description='" + description + '\'' +
                ", picture1='" + picture1 + '\'' +
                ", picture2='" + picture2 + '\'' +
                '}';
    }
}
