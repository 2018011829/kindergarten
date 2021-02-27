package com.example.myapplication.money.activity.bean;

public class MoneyPicture {
    private int id;
    private String babyGrade;
    private String babyClass;
    private String babyName;

    public MoneyPicture() {
    }
    public MoneyPicture(int id, String babyGrade, String babyClass, String babyName) {
        this.id = id;
        this.babyGrade = babyGrade;
        this.babyClass = babyClass;
        this.babyName = babyName;
    }

    public MoneyPicture(String babyGrade, String babyClass, String babyName) {
        this.babyGrade = babyGrade;
        this.babyClass = babyClass;
        this.babyName = babyName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBabyGrade() {
        return babyGrade;
    }

    public void setBabyGrade(String babyGrade) {
        this.babyGrade = babyGrade;
    }

    public String getBabyClass() {
        return babyClass;
    }

    public void setBabyClass(String babyClass) {
        this.babyClass = babyClass;
    }

    public String getBabyName() {
        return babyName;
    }

    public void setBabyName(String babyName) {
        this.babyName = babyName;
    }
}
