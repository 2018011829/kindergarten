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
}
