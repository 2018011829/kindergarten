package com.group.kindergarten.teacher.entity;

public class Teacher {
	private int id;
	private String name;
	private String position;
	private String phone;
	private String picture;
	private String motto;
	private int status;

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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Teacher() {
		super();
	}

	public Teacher(int id, String name, String position, String phone, String picture, String motto, int status) {
		super();
		this.id = id;
		this.name = name;
		this.position = position;
		this.phone = phone;
		this.picture = picture;
		this.motto = motto;
		this.status = status;
	}

	@Override
	public String toString() {
		return "Teacher [id=" + id + ", name=" + name + ", position=" + position + ", phone=" + phone + ", picture="
				+ picture + ", motto=" + motto + ", status=" + status + "]";
	}

}
