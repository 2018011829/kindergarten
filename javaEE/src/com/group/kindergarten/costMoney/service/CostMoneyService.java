package com.group.kindergarten.costMoney.service;

import java.util.List;

import com.group.kindergarten.costMoney.dao.CostMoneyDao;
import com.group.kindergarten.costMoney.entity.SchoolSemester;

public class CostMoneyService {

	private static CostMoneyService costMoneyService;
	private static CostMoneyDao costMoneyDao;
	
	/**
	 * �õ�һ��childrenServiceʵ��
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
	 * �������ݿ����Ƿ�������Ͽ�ʱ��Ĺ涨
	 * @return falseΪ�գ�δ����ʱ��Ĺ涨
	 */
	public boolean isClassTimeExist() {
		boolean b=costMoneyDao.isClassTimeExist();
		
		return b;
	}
	
	/**
	 * �����ݿ��л�ȡ���õ�ѧ����Ϣ
	 * @return
	 */
	public List<SchoolSemester> searchSemesterInfo() {
		List<SchoolSemester> list=costMoneyDao.searchSemesterInfo();
		
		return list;
	}
	
	/**
	 * �ж��·������ݿ����Ƿ���ڣ�����������޸ģ������ڽ��б���
	 * @param monthNum
	 * @return 
	 */
	public boolean justAndPreserve(int monthNum,int dayNum) {
		boolean b=costMoneyDao.justAndPreserve(monthNum, dayNum);
		
		return b;
	}
	
	/**
	 * ��ѯ���������Ƿ����
	 * @param stuName
	 * @return
	 */
	public boolean isExistChildName(String stuName) {
		boolean b=costMoneyDao.isExistChildName(stuName);
		
		return b;
	}
	
	/**
	 * ���Һ��ӵ�id
	 * @param name
	 * @param parentPhone
	 * @return
	 */
	public int returnChildId(String name,String parentPhone) {
		int id=costMoneyDao.returnChildId(name, parentPhone);
		
		return id;
	}
	
	/**
	 * �����ݿ��и��º��ӵĵ�������
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
	 * ���غ����ϸ��µĳ�������
	 * @param name
	 * @param parentPhone
	 * @return
	 */
	public int childAttendLastMonth(String name,String parentPhone) {
		int day=costMoneyDao.childAttendLastMonth(name, parentPhone);
		
		return day;
	}

	/**
	 * �����ϸ��µ����
	 * @param preMonth
	 * @return
	 */
	public double caculateLastMoney(int preMonth,String name,String parentPhone) {
		double money=0;
		//��ȡ�ϸ���Ҫ��ѧ������
		int day=0;
		day=costMoneyDao.getOneMonthAboutDayNum(preMonth);
		//����Ӧ���ı����ѺͲͷѣ����ж������Ķ��٣�0, 1-10 ��11�����ϣ�
		double shouldMoney=0;
		if(day==0) {
			shouldMoney=0;
		}else if(day>=1 && day<=10) {//1-10
			shouldMoney=1000*0.5+18*day;
		}else {//11������
			shouldMoney=1000+18*day;
		}
		//����ʵ��Ӧ֧���ķ��ã��ϸ��³����������㣩
		double actualMoney=0;
		int attendenceDay=costMoneyDao.getChildLastAttendenceDay(name, parentPhone);
		if(attendenceDay==0) {
			actualMoney=0;
		}else if(attendenceDay>=1 && attendenceDay<=10) {//1-10
			actualMoney=1000*0.5+18*attendenceDay;
		}else {//11������
			actualMoney=1000+18*attendenceDay;
		}
		//����ʣ������
		money=shouldMoney-actualMoney;
		
		return money;
	}
	
	/**
	 * ���㱾��Ҫ����Ǯ
	 * @param nowMonth
	 * @return
	 */
	public double caculateNowMoney(int nowMonth,String name,String parentPhone) {
		double money=0;
		int day=0;
		//��ȡ����Ҫ��ѧ������
		day=costMoneyDao.getOneMonthAboutDayNum(nowMonth);//��ȡ�ϸ����·� ��0��ʼ��0-11
		int preMonth=nowMonth-1;
		if(preMonth==0) {
			preMonth=12;
		}
		//��ȡ�ϸ��µ����
		double lastMonthMoney=caculateLastMoney(nowMonth-1, name, parentPhone);
		//����Ӧ���ı����ѺͲͷѣ����ж������Ķ��٣�0, 1-10 ��11�����ϣ�
		double shouldMoney=0;
		if(day==0) {
			shouldMoney=0;
		}else if(day>=1 && day<=10) {//1-10
			shouldMoney=1000*0.5+18*day;
		}else {//11������
			shouldMoney=1000+18*day;
		}
		//���㱾��ʵ��Ӧ���ķ��ã����·��ü�ȥ�ϸ��µ���
		money=shouldMoney-lastMonthMoney;
		
		return money;
	}

	/**
	 * �������¼�ʱ�������������������ݿ�����������
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
