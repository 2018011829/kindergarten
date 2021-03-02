package com.group.kindergarten.costMoney.entity;

public class Charge {
	private int id;
	private String babyClass;//�༶
	private String teacher;//�ð༶��ʦ
	private String weChat;//΢���տ���ͼƬ·��
	private String alipay;//֧�����տ���ͼƬ·��
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
