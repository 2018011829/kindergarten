package com.group.kindergarten.my.service;

import java.util.List;

import com.group.kindergarten.my.dao.ChildrenDao;
import com.group.kindergarten.my.entity.Child;

public class ChildrenService {
	private static ChildrenService childrenService;
	private static ChildrenDao childrenDao;
	
	
	/**
	 * 得到一个childrenService实例
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
	 * 添加孩子信息
	 * */
	public Boolean addChild(String name,String grade,String sex,String parentPhone) {
		
		return childrenDao.addChild(name, grade, sex, parentPhone);
		
	}
	
	/**
	 * 
	 * @param name
	 * @param parentPhone
	 * @return true: 该孩子已存在
	 */
	public boolean searchChild(String name,String parentPhone) {
		boolean b=childrenDao.searchChild(name, parentPhone);
		
		return b;
	}
	
	/**
	 * 查询某电话号码下的所有孩子信息
	 * @return 
	 * */
	
	public List<Child> queryChildrenByPhone(String phone){
		
		return childrenDao.queryChildrenByPhone(phone);
		
	}
}
