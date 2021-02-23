package com.group.kindergarten.schoolInfo.entity;

public class BasicInfo {
	private int id;
	private String introduceFile;
	private String address;
	public BasicInfo() {
	}
	public BasicInfo(int id, String introduceFile, String address) {
		super();
		this.id = id;
		this.introduceFile = introduceFile;
		this.address = address;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getIntroduceFile() {
		return introduceFile;
	}
	public void setIntroduceFile(String introduceFile) {
		this.introduceFile = introduceFile;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	

}
