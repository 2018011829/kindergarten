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
	 * �õ�һ��userServiceʵ��
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
	 * ע���û�
	 * 
	 * @param phone
	 * @param nickname
	 * @param password
	 * @return true:�ֻ�����ע��ɹ�; false:�ֻ�����ע��ʧ��
	 */
	public boolean resigter(String phone, String nickname, String password,String identity) {
		boolean b = false;
		b = userDao.addUser(phone, nickname, password,identity);

		return b;
	}

	/**
	 * �����û����ֻ����Ƿ��Ѿ�ע��
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
	 * �ж��û����������Ƿ�ƥ�� ���û��Ƿ����
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
	 * ����ĳ���û���Ϣ��Json��
	 * 
	 * @param phone
	 * @return json��
	 */
	public String getOneUserInfo(String phone) {
		return new Gson().toJson(userDao.selectOneUser(phone));
	}
	/**
	 * �����û���Ϣ
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
	 * �����ֻ��Ÿ���ָ���û���Ϣ
	 * 
	 * @return �����Ƿ�ɹ�
	 */
	public Boolean updateUserMessage(String phone, String nickName, String headName,String avatar) {

		return userDao.updateUserMessage(phone, nickName, headName,avatar);

	}

	/**
	 * ��ȡһҳ���û���Ϣ�����ں�̨����ϵͳչʾ
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
		System.out.println("����page�е�count" + count);
		System.out.println("����page�е�list" + list.size());
		System.out.println(list.get(2).getNickname());
		return page;
	}

	public Page<User> getPage(int pageNum, int pageSize, String searchInfo) {
		Page<User> page = new Page<User>(pageNum, pageSize);
		int count = UserDaoImpl.countAll(searchInfo);
		List<User> list = UserDaoImpl.selectPage(pageNum, pageSize, searchInfo);
		page.setList(list);
		page.setTotalCount(count);
		System.out.println("����page�е�count" + count);
		System.out.println("����page�е�list" + list.size());
//		System.out.println(list.get(2).getNickname());
		return page;
	}

	/**
	 * ͨ���ֻ������ȡ���룬�����ͻ���ʹ���ֻ���֤���¼
	 * 
	 * @param phone
	 * @return
	 */
	public String getPassword(String phone) {
		return userDao.selectPasswordByPhone(phone);
	}

}
