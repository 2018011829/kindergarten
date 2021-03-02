package com.group.kindergarten.server.user.service;

import java.util.List;

import com.group.kindergarten.server.user.dao.UserDaoImpl;
import com.group.kindergarten.user.entity.User;
import com.group.kindergarten.util.Page;

public class UserServiceImpl {
	
	/**
	 * 分页查询用户信息
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public Page<User> listByPage(int pageNum, int pageSize){
		Page<User> page = new Page<User>(pageNum,pageSize);
		UserDaoImpl userDaoImpl = new UserDaoImpl();
		int count = userDaoImpl.countByPage();
		List<User> list = userDaoImpl.findByPage(pageNum, pageSize);
		page.setList(list);
		page.setTotalCount(count);
		return page;
	}
	
	/**
	 * 
	 * @param pageNum
	 * @param pageSize
	 * @param phone
	 * @return
	 */
	public Page<User> listByPhondService(int pageNum, int pageSize,String phone) {
		Page<User> page = new Page<User>(pageNum,pageSize);
		UserDaoImpl userDaoImpl = new UserDaoImpl();
		int count = userDaoImpl.countByPageAndPhone(phone);
		List<User> list = userDaoImpl.findByPageAndPhone(pageNum, pageSize, phone);
		page.setList(list);
		page.setTotalCount(count);
		return page;
	}
	
	/**
	 * 根据id删除用户
	 * @param id
	 * @return
	 */
	public boolean deleteUserService(int id) {
		return new UserDaoImpl().deleteUser(id);
	}
}
