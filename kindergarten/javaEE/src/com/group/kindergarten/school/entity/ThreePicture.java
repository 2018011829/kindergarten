package com.group.kindergarten.school.entity;

public class ThreePicture {

    private String description;
    private String picture1;
    private String picture2;
    private String picture3;

    public ThreePicture() {
    }

    public ThreePicture(String description, String picture1, String picture2, String picture3) {
        this.description = description;
        this.picture1 = picture1;
        this.picture2 = picture2;
        this.picture3 = picture3;
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

    public String getPicture3() {
        return picture3;
    }

    public void setPicture3(String picture3) {
        this.picture3 = picture3;
    }

    @Override
    public String toString() {
        return "ThreePicture{" +
                "description='" + description + '\'' +
                ", picture1='" + picture1 + '\'' +
                ", picture2='" + picture2 + '\'' +
                ", picture3='" + picture3 + '\'' +
                '}';
    }
}
