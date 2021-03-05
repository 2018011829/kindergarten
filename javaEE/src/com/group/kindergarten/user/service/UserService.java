package com.group.kindergarten.user.service;

import java.util.List;

import com.google.gson.Gson;
import com.group.kindergarten.user.dao.UserDaoImpl;
import com.group.kindergarten.user.entity.User;
import com.group.kindergarten.util.Page;

public class UserService {
	private static UserService userService;
	private static UserDaoImpl userDao;
	private static Gson gson;

	/**
	 * 得到一个userService实例
	 * 
	 * @return
	 */
	public static UserService getInstance() {
		if (null == userService) {
			userService = new UserService();
		}
		if (null == userDao) {
			userDao = UserDaoImpl.getInstance();
		}
		if (null == gson) {
			gson = new Gson();
		}
		return userService;
	}

	/**
	 * 注册用户
	 * 
	 * @param phone
	 * @param nickname
	 * @param password
	 * @return true:手机号码注册成功; false:手机号码注册失败
	 */
	public boolean resigter(String phone, String nickname, String password,String identity) {
		boolean b = false;
		b = userDao.addUser(phone, nickname, password,identity);

		return b;
	}

	/**
	 * 查找用户的手机号是否已经注册
	 * 
	 * @param phone
	 * @return
	 */
	public boolean isExistPhone(String phone) {
		boolean b = false;
		if (userDao.isExist(phone)) {
			b = true;
		}
		return b;
	}

	/**
	 * 判断用户名和密码是否匹配 即用户是否存在
	 * 
	 * @param phone
	 * @param password
	 * @return
	 */
	public boolean isExistUser(String phone, String password) {
		boolean b = false;
		if (userDao.isExistUser(phone, password)) {
			b = true;
		}
		return b;
	}

	/**
	 * 返回某个用户信息的Json串
	 * 
	 * @param phone
	 * @return json串
	 */
	public String getOneUserInfo(String phone) {
		return new Gson().toJson(userDao.selectOneUser(phone));
	}
	/**
	 * 返回用户信息
	 * @param id
	 * @return
	 */
	public String getOneUserInfoById(int id) {
		return new Gson().toJson(userDao.selectOneUserById(id));
	}

	public String searchUsersByPhone(String query) {
		return new Gson().toJson(userDao.queryUsersByPhone(query));

	}

	/**
	 * 根据手机号更新指定用户信息
	 * 
	 * @return 更新是否成功
	 */
	public Boolean updateUserMessage(String phone, String nickName, String headName,String avatar) {

		return userDao.updateUserMessage(phone, nickName, headName,avatar);

	}

	/**
	 * 获取一页的用户信息，用于后台管理系统展示
	 * 
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public Page<User> getPage(int pageNum, int pageSize) {
		Page<User> page = new Page<User>(pageNum, pageSize);
		int count = UserDaoImpl.countAll();
		List<User> list = UserDaoImpl.selectPage(pageNum, pageSize);
		page.setList(list);
		page.setTotalCount(count);
		System.out.println("测试page中的count" + count);
		System.out.println("测试page中的list" + list.size());
		System.out.println(list.get(2).getNickname());
		return page;
	}

	public Page<User> getPage(int pageNum, int pageSize, String searchInfo) {
		Page<User> page = new Page<User>(pageNum, pageSize);
		int count = UserDaoImpl.countAll(searchInfo);
		List<User> list = UserDaoImpl.selectPage(pageNum, pageSize, searchInfo);
		page.setList(list);
		page.setTotalCount(count);
		System.out.println("测试page中的count" + count);
		System.out.println("测试page中的list" + list.size());
//		System.out.println(list.get(2).getNickname());
		return page;
	}

	/**
	 * 通过手机号码获取密码，用来客户端使用手机验证码登录
	 * 
	 * @param phone
	 * @return
	 */
	public String getPassword(String phone) {
		return userDao.selectPasswordByPhone(phone);
	}

}
