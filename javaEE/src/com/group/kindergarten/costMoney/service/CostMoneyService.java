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
		int day=0;
		
		return day;
	}
	
	/**
	 * ���غ��ӱ���Ӧ�ý��ķ���
	 * @param name
	 * @param parentPhone
	 * @return
	 */
	public int childNeedMoney(String name,String parentPhone) {
		int day=0;
		
		return day;
	}
}
