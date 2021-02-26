package com.group.kindergarten.costMoney.service;

import java.util.List;

import com.group.kindergarten.costMoney.dao.CostMoneyDao;
import com.group.kindergarten.costMoney.entity.SchoolSemester;

public class CostMoneyService {

	private static CostMoneyService costMoneyService;
	private static CostMoneyDao costMoneyDao;
	
	/**
	 * 得到一个childrenService实例
	 * 
	 * @return ChildrenService
	 */
	public static CostMoneyService getInstance() {
		if (null == costMoneyService) {
			costMoneyService = new CostMoneyService();
		}
		if (null == costMoneyDao) {
			costMoneyDao = CostMoneyDao.getInstance();
		}
		return costMoneyService;
	}
	
	/**
	 * 查找数据库中是否进行了上课时间的规定
	 * @return false为空：未进行时间的规定
	 */
	public boolean isClassTimeExist() {
		boolean b=costMoneyDao.isClassTimeExist();
		
		return b;
	}
	
	/**
	 * 从数据库中获取设置的学期信息
	 * @return
	 */
	public List<SchoolSemester> searchSemesterInfo() {
		List<SchoolSemester> list=costMoneyDao.searchSemesterInfo();
		
		return list;
	}
	
	/**
	 * 判断月份在数据库中是否存在，存在则进行修改，不存在进行保存
	 * @param monthNum
	 * @return 
	 */
	public boolean justAndPreserve(int monthNum,int dayNum) {
		boolean b=costMoneyDao.justAndPreserve(monthNum, dayNum);
		
		return b;
	}
	
	/**
	 * 查询孩子姓名是否存在
	 * @param stuName
	 * @return
	 */
	public boolean isExistChildName(String stuName) {
		boolean b=costMoneyDao.isExistChildName(stuName);
		
		return b;
	}
	
	/**
	 * 查找孩子的id
	 * @param name
	 * @param parentPhone
	 * @return
	 */
	public int returnChildId(String name,String parentPhone) {
		int id=costMoneyDao.returnChildId(name, parentPhone);
		
		return id;
	}
	
	/**
	 * 在数据库中更新孩子的到课天数
	 * @param id
	 * @param parentPhone
	 * @param dayNum
	 * @return
	 */
	public boolean updateLeaveInfo(int id,String parentPhone,int dayNum) {
		boolean b=costMoneyDao.updateLeaveInfo(id, parentPhone, dayNum);
		
		return b;
	}
	

	/**
	 * 返回孩子上个月的出勤天数
	 * @param name
	 * @param parentPhone
	 * @return
	 */
	public int childAttendLastMonth(String name,String parentPhone) {
		int day=costMoneyDao.childAttendLastMonth(name, parentPhone);
		
		return day;
	}

	/**
	 * 计算上个月的余额
	 * @param preMonth
	 * @return
	 */
	public double caculateLastMoney(int preMonth,String name,String parentPhone) {
		double money=0;
		//获取上个月要上学的天数
		int day=0;
		day=costMoneyDao.getOneMonthAboutDayNum(preMonth);
		//计算应交的保育费和餐费，先判断天数的多少（0, 1-10 ，11及以上）
		double shouldMoney=0;
		if(day==0) {
			shouldMoney=0;
		}else if(day>=1 && day<=10) {//1-10
			shouldMoney=1000*0.5+18*day;
		}else {//11及以上
			shouldMoney=1000+18*day;
		}
		//计算实际应支付的费用（上个月出勤天数计算）
		double actualMoney=0;
		int attendenceDay=costMoneyDao.getChildLastAttendenceDay(name, parentPhone);
		if(attendenceDay==0) {
			actualMoney=0;
		}else if(attendenceDay>=1 && attendenceDay<=10) {//1-10
			actualMoney=1000*0.5+18*attendenceDay;
		}else {//11及以上
			actualMoney=1000+18*attendenceDay;
		}
		//计算剩余的余额
		money=shouldMoney-actualMoney;
		
		return money;
	}
	
	/**
	 * 计算本月要交的钱
	 * @param nowMonth
	 * @return
	 */
	public double caculateNowMoney(int nowMonth,String name,String parentPhone) {
		double money=0;
		int day=0;
		//获取本月要上学的天数
		day=costMoneyDao.getOneMonthAboutDayNum(nowMonth);//获取上个月月份 从0开始，0-11
		int preMonth=nowMonth-1;
		if(preMonth==0) {
			preMonth=12;
		}
		//获取上个月的余额
		double lastMonthMoney=caculateLastMoney(nowMonth-1, name, parentPhone);
		//计算应交的保育费和餐费，先判断天数的多少（0, 1-10 ，11及以上）
		double shouldMoney=0;
		if(day==0) {
			shouldMoney=0;
		}else if(day>=1 && day<=10) {//1-10
			shouldMoney=1000*0.5+18*day;
		}else {//11及以上
			shouldMoney=1000+18*day;
		}
		//计算本月实际应交的费用（本月费用减去上个月的余额）
		money=shouldMoney-lastMonthMoney;
		
		return money;
	}

	/**
	 * 计算请事假时间总天数，并请求数据库更新请假天数
	 * @param id
	 * @param phone
	 * @param dayStartNum
	 * @param dayEndNum
	 * @return
	 */
	public boolean updateLeaveInfo(String name, String phone, int dayStartNum, int dayEndNum) {
		boolean b=costMoneyDao.updateLeaveInfo(name, phone, dayStartNum, dayEndNum);
		
		return b;
	}
}
