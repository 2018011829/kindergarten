package com.example.myapplication.main.entity;

public class ChildConsumeInfo {
    private int day;
    private double money;
    public ChildConsumeInfo() {
        super();
    }
    public ChildConsumeInfo(int day, double money) {
        super();
        this.day = day;
        this.money = money;
    }
    public int getDay() {
        return day;
    }
    public void setDay(int day) {
        this.day = day;
    }
    public double getMoney() {
        return money;
    }
    public void setMoney(double money) {
        this.money = money;
    }
    @Override
    public String toString() {
        return "ChildConsumeInfo [day=" + day + ", money=" + money + "]";
    }
}
