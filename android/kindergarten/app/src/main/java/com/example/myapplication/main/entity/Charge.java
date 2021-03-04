package com.example.myapplication.main.entity;

public class Charge {
    private int id;
    private String babyClass;//班级
    private String teacher;//该班级教师
    private String weChat;//微信收款码图片路径
    private String alipay;//支付宝收款码图片路径
    public Charge() {
    }
    public Charge(int id, String babyClass, String teacher, String weChat, String alipay) {
        super();
        this.id = id;
        this.babyClass = babyClass;
        this.teacher = teacher;
        this.weChat = weChat;
        this.alipay = alipay;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getBabyClass() {
        return babyClass;
    }
    public void setBabyClass(String babyClass) {
        this.babyClass = babyClass;
    }
    public String getTeacher() {
        return teacher;
    }
    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }
    public String getWeChat() {
        return weChat;
    }
    public void setWeChat(String weChat) {
        this.weChat = weChat;
    }
    public String getAlipay() {
        return alipay;
    }
    public void setAlipay(String alipay) {
        this.alipay = alipay;
    }
    @Override
    public String toString() {
        return "Charge [id=" + id + ", babyClass=" + babyClass + ", teacher=" + teacher + ", weChat=" + weChat
                + ", alipay=" + alipay + "]";
    }



}
