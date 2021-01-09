package com.group.kindergarten.applyinfo.service;

import com.group.kindergarten.applyinfo.dao.EnterDao;

public class EnterService {
	private static EnterService enterService;
	private static EnterDao enterDao;
	
	
	/**
	 * 得到一个enterService实例
	 * 
	 * @return EnterService
	 */
	public static EnterService getInstance() {
		if (null == enterService) {
			enterService = new EnterService();
		}
		if (null == enterDao) {
			enterDao = EnterDao.getInstance();
		}
		return enterService;
	}
	
	
	/**
	 * 添加孩子报名信息
	 * @return boolean
	 * */
	public boolean addChildApplyInformation(String userNumber,String babyName,String babyBirthday,String babySex,String babyIDnumber
			,String babyAddoAllergies,String parentName1,String relation1,String parentIDnumber1,String phoneNumber1,String workSpace1,
			String homeAddress1,String parentName2,String relation2,String parentIDnumber2,String phoneNumber2,String workSpace2,
			String homeAddress2) {
		
		return enterDao.addChildApplyInformation(userNumber,babyName, babyBirthday, babySex, babyIDnumber, babyAddoAllergies, 
				parentName1, relation1,parentIDnumber1, phoneNumber1, workSpace1, homeAddress1, 
				parentName2, relation2,parentIDnumber2, phoneNumber2, workSpace2, homeAddress2);
	}
}
