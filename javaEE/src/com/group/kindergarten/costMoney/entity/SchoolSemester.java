package com.group.kindergarten.costMoney.entity;

public class SchoolSemester {

	private int id;
	private int month;
	private int dayNum;

	public SchoolSemester() {
		super();
	}

	public SchoolSemester(int id, int month, int dayNum) {
		super();
		this.id = id;
		this.month = month;
		this.dayNum = dayNum;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getDayNum() {
		return dayNum;
	}

	public void setDayNum(int dayNum) {
		this.dayNum = dayNum;
	}

	@Override
	public String toString() {
		return "SchoolSemester [id=" + id + ", month=" + month + ", dayNum=" + dayNum + "]";
	}

}
