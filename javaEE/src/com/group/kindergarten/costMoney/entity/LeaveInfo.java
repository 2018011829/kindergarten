package com.group.kindergarten.costMoney.entity;

public class LeaveInfo {
	private int leaveDay;
	private int monthDay;
	
	public LeaveInfo() {
		super();
	}

	public LeaveInfo(int leaveDay, int monthDay) {
		super();
		this.leaveDay = leaveDay;
		this.monthDay = monthDay;
	}

	public int getLeaveDay() {
		return leaveDay;
	}

	public void setLeaveDay(int leaveDay) {
		this.leaveDay = leaveDay;
	}

	public int getMonthDay() {
		return monthDay;
	}

	public void setMonthDay(int monthDay) {
		this.monthDay = monthDay;
	}

	@Override
	public String toString() {
		return "LeaveInfo [leaveDay=" + leaveDay + ", monthDay=" + monthDay + "]";
	}
	
}
