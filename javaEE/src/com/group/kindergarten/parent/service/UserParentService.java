package com.group.kindergarten.parent.service;

import java.util.List;

import com.google.gson.Gson;
import com.group.kindergarten.parent.dao.UserParentDaoImpl;

import com.group.kindergarten.parent.entity.UserParent;
import com.group.kindergarten.util.Page;

public class UserParentService {
	private static UserParentService parentService;
	private static UserParentDaoImpl parentDao;
	private static Gson gson;

	/**
	 * 得到一个parentService实例
	 * 
	 * @return
	 */
	public static UserParentService getInstance() {
		if (null == parentService) {
			parentService = new UserParentService();
		}
		if (null == parentDao) {
			parentDao = UserParentDaoImpl.getInstance();
		}
		if (null == gson) {
			gson = new Gson();
		}
		return parentService;
	}

	/**
	 * 注册用户
	 * 
	 * @param phone
	 * @param nickname
	 * @param password
	 * @return true:手机号码注册成功; false:手机号码注册失败
	 */
	public boolean resigter(String phone, String nickname, String password) {
		boolean b = false;
		b = parentDao.addParent(phone, nickname, password);

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
		if (parentDao.isExist(phone)) {
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
		if (parentDao.isExistUser(phone, password)) {
			b = true;
		}
		return b;
	}

	/**
	 * 返回某个父母信息的Json串
	 * 
	 * @param phone
	 * @return json串
	 */
	public String getOneParentInfo(String phone) {
		return new Gson().toJson(parentDao.selectOneParent(phone));
	}

	public String searchParentsByPhone(String query) {
		return new Gson().toJson(parentDao.queryParentsByPhone(query));

	}

	/**
	 * 根据手机号更新指定家长信息
	 * 
	 * @return 更新是否成功
	 */
	public Boolean updateParentMessage(String phone, String nickName, String headName) {

		return parentDao.updateParentMessage(phone, nickName, headName);

	}

	/**
	 * 获取一页的用户信息，用于后台管理系统展示
	 * 
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public Page<UserParent> getPage(int pageNum, int pageSize) {
		Page<UserParent> page = new Page<UserParent>(pageNum, pageSize);
		int count = UserParentDaoImpl.countAll();
		List<UserParent> list = UserParentDaoImpl.selectPage(pageNum, pageSize);
		page.setList(list);
		page.setTotalCount(count);
		System.out.println("测试page中的count" + count);
		System.out.println("测试page中的list" + list.size());
		System.out.println(list.get(2).getNickname());
		return page;
	}

	public Page<UserParent> getPage(int pageNum, int pageSize, String searchInfo) {
		Page<UserParent> page = new Page<UserParent>(pageNum, pageSize);
		int count = UserParentDaoImpl.countAll(searchInfo);
		List<UserParent> list = UserParentDaoImpl.selectPage(pageNum, pageSize, searchInfo);
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
		return parentDao.selectPasswordByPhone(phone);
	}

}
