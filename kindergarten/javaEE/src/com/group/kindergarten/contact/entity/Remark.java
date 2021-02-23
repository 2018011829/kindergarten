package com.group.kindergarten.contact.entity;

public class Remark {
	private int id;
	private int fromUser;
	private int toUser;
	private String remarkStr;

	public Remark() {
		super();
	}

	public Remark(int fromUser, int toUser, String remarkStr) {
		super();
		this.fromUser = fromUser;
		this.toUser = toUser;
		this.remarkStr = remarkStr;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getFromUser() {
		return fromUser;
	}

	public void setFromUser(int fromUser) {
		this.fromUser = fromUser;
	}

	public int getToUser() {
		return toUser;
	}

	public void setToUser(int toUser) {
		this.toUser = toUser;
	}

	public String getRemarkStr() {
		return remarkStr;
	}

	public void setRemarkStr(String remarkStr) {
		this.remarkStr = remarkStr;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "concatRemark [id=" + id + ", from_user=" + fromUser + ", to_user=" + toUser + ", remark_str="
				+ remarkStr + "]";
	}

}
