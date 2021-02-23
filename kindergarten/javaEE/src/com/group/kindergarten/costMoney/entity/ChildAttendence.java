package com.group.kindergarten.costMoney.entity;

public class ChildAttendence {
	private int id;
	private String phone;
	private int child_id;
	private int last_attendence_day;
	private int next_attendence_day;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getChild_id() {
		return child_id;
	}

	public void setChild_id(int child_id) {
		this.child_id = child_id;
	}

	public int getLast_attendence_day() {
		return last_attendence_day;
	}

	public void setLast_attendence_day(int last_attendence_day) {
		this.last_attendence_day = last_attendence_day;
	}

	public int getNext_attendence_day() {
		return next_attendence_day;
	}

	public void setNext_attendence_day(int next_attendence_day) {
		this.next_attendence_day = next_attendence_day;
	}

	public ChildAttendence() {
		super();
	}

	public ChildAttendence(int id, String phone, int child_id, int last_attendence_day, int next_attendence_day) {
		super();
		this.id = id;
		this.phone = phone;
		this.child_id = child_id;
		this.last_attendence_day = last_attendence_day;
		this.next_attendence_day = next_attendence_day;
	}

	@Override
	public String toString() {
		return "ChildAttendence [id=" + id + ", phone=" + phone + ", child_id=" + child_id + ", last_attendence_day="
				+ last_attendence_day + ", next_attendence_day=" + next_attendence_day + "]";
	}

}
