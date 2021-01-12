package com.group.kindergarten.my.service;

import java.util.List;

import com.group.kindergarten.my.dao.ChildrenDao;
import com.group.kindergarten.my.entity.Child;

public class ChildrenService {
	private static ChildrenService childrenService;
	private static ChildrenDao childrenDao;
	
	
	/**
	 * �õ�һ��childrenServiceʵ��
	 * 
	 * @return ChildrenService
	 */
	public static ChildrenService getInstance() {
		if (null == childrenService) {
			childrenService = new ChildrenService();
		}
		if (null == childrenDao) {
			childrenDao = ChildrenDao.getInstance();
		}
		return childrenService;
	}
	
	/**
	 * ��Ӻ�����Ϣ
	 * */
	public Boolean addChild(String name,String grade,String sex,String parentPhone) {
		
		return childrenDao.addChild(name, grade, sex, parentPhone);
		
	}
	
	/**
	 * 
	 * @param name
	 * @param parentPhone
	 * @return true: �ú����Ѵ���
	 */
	public boolean searchChild(String name,String parentPhone) {
		boolean b=childrenDao.searchChild(name, parentPhone);
		
		return b;
	}
	
	/**
	 * ��ѯĳ�绰�����µ����к�����Ϣ
	 * @return 
	 * */
	
	public List<Child> queryChildrenByPhone(String phone){
		
		return childrenDao.queryChildrenByPhone(phone);
		
	}
}
