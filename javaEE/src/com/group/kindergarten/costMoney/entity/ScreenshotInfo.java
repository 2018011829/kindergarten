package com.group.kindergarten.costMoney.entity;

public class ScreenshotInfo {
	private int id;
    private String babyGrade;
    private String babyClass;
    private String babyName;
    private String phone;
    private String photoName;
    
	public ScreenshotInfo() {
		super();
	}
	public ScreenshotInfo(int id, String babyGrade, String babyClass, String babyName, String phone, String photoName) {
		super();
		this.id = id;
		this.babyGrade = babyGrade;
		this.babyClass = babyClass;
		this.babyName = babyName;
		this.phone = phone;
		this.photoName = photoName;
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
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPhotoName() {
		return photoName;
	}
	public void setPhotoName(String photoName) {
		this.photoName = photoName;
	}
	@Override
	public String toString() {
		return "ScreenshotInfo [id=" + id + ", babyGrade=" + babyGrade + ", babyClass=" + babyClass + ", babyName="
				+ babyName + ", phone=" + phone + ", photoName=" + photoName + "]";
	}
    
}
