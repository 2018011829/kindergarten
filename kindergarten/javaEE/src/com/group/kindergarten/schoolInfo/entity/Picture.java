package com.group.kindergarten.schoolInfo.entity;

public class Picture {
	private int id;
	private String picture;
	private int descriptionId;
	public Picture() {
	}
	public Picture(int id, String picture, int descriptionId) {
		super();
		this.id = id;
		this.picture = picture;
		this.descriptionId = descriptionId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public int getDescriptionId() {
		return descriptionId;
	}
	public void setDescriptionId(int descriptionId) {
		this.descriptionId = descriptionId;
	}
	
	

}
