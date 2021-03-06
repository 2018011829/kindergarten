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
	 * 查找该孩子是否在幼儿园已经报名
	 * @param idNum
	 * @return
	 */
	public boolean searchIdNumIsRight(String idNum) {
		boolean b=childrenDao.searchIdNumIsRight(idNum);
		
		return b;
	}
	
	/**
	 * 查询某电话号码下的所有孩子信息
	 * @return 
	 * */
	
	public List<Child> queryChildrenByPhone(String phone){
		
		return childrenDao.queryChildrenByPhone(phone);
		
	}

	/**
	 * 判断孩子名字与身份证号是否吻合
	 * @param name
	 * @param idNum
	 * @return
	 */
	public boolean searchNameAndId(String name, String idNum, String sClass) {
		boolean b=childrenDao.searchNameAndId(name, idNum, sClass);
		
		return b;
	}
	
	/**
	 * 判断孩子名字与身份证号是否吻合
	 * @param name
	 * @param idNum
	 * @return
	 */
	public boolean searchNameAndId(String name, String idNum) {
		boolean b=childrenDao.searchNameAndId(name, idNum);
		
		return b;
	}
	
	
	/**
	 * 判断用户输入的原密码是否正确
	 * 
	 * @param phone
	 * @param password
	 * @return
	 */
	public boolean isExistUser(String phone, String password) {
		
		return childrenDao.isExistUser(phone, password);
	}
	
	
	public boolean updatePwd(String phone,String password) {
		
		return childrenDao.updatePwd(phone, password);
	}
	
	/**
	 * 从数据库中获取所有的班级信息
	 * @return
	 */
	public List<String> searchClassInfo(){
		List<String> list=childrenDao.searchClassInfo();
		
		return list;
	}
}
