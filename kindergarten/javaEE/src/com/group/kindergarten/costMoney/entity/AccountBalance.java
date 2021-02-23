package com.group.kindergarten.costMoney.entity;

public class AccountBalance {
	private int id;
	private String phone;
	private int child_id;
	private int money;

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

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public AccountBalance() {
		super();
	}

	public AccountBalance(int id, String phone, int child_id, int money) {
		super();
		this.id = id;
		this.phone = phone;
		this.child_id = child_id;
		this.money = money;
	}

	@Override
	public String toString() {
		return "AccountBalance [id=" + id + ", phone=" + phone + ", child_id=" + child_id + ", money=" + money + "]";
	}

}
