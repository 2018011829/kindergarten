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
}
