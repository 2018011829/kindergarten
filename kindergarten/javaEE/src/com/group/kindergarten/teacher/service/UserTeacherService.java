package com.group.kindergarten.teacher.service;

import java.util.List;

import com.google.gson.Gson;
import com.group.kindergarten.teacher.dao.UserTeacherDaoImpl;
import com.group.kindergarten.teacher.entity.UserTeacher;
import com.group.kindergarten.util.Page;

public class UserTeacherService {
	private static UserTeacherService teacherService;
	private static UserTeacherDaoImpl teacherDao;
	private static Gson gson;

	/**
	 * 得到一个teacherService实例
	 * 
	 * @return
	 */
	public static UserTeacherService getInstance() {
		if (null == teacherService) {
			teacherService = new UserTeacherService();
		}
		if (null == teacherDao) {
			teacherDao = UserTeacherDaoImpl.getInstance();
		}
		if (null == gson) {
			gson = new Gson();
		}
		return teacherService;
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
		b = teacherDao.addParent(phone, nickname, password);

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
		if (teacherDao.isExist(phone)) {
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
		if (teacherDao.isExistUser(phone, password)) {
			b = true;
		}
		return b;
	}

	/**
	 * 返回某个教師信息的Json串
	 * 
	 * @param phone
	 * @return json串
	 */
	public String getOneParentInfo(String phone) {
		return new Gson().toJson(teacherDao.selectOneParent(phone));
	}

	public String searchParentsByPhone(String query) {
		return new Gson().toJson(teacherDao.queryParentsByPhone(query));

	}

	/**
	 * 根据手机号更新指定家长信息
	 * 
	 * @return 更新是否成功
	 */
	public Boolean updateParentMessage(String phone, String nickName, String headName) {

		return teacherDao.updateParentMessage(phone, nickName, headName);

	}

	/**
	 * 获取一页的用户信息，用于后台管理系统展示
	 * 
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public Page<UserTeacher> getPage(int pageNum, int pageSize) {
		Page<UserTeacher> page = new Page<UserTeacher>(pageNum, pageSize);
		int count = UserTeacherDaoImpl.countAll();
		List<UserTeacher> list = UserTeacherDaoImpl.selectPage(pageNum, pageSize);
		page.setList(list);
		page.setTotalCount(count);
		System.out.println("测试page中的count" + count);
		System.out.println("测试page中的list" + list.size());
		System.out.println(list.get(2).getNickname());
		return page;
	}

	public Page<UserTeacher> getPage(int pageNum, int pageSize, String searchInfo) {
		Page<UserTeacher> page = new Page<UserTeacher>(pageNum, pageSize);
		int count = UserTeacherDaoImpl.countAll(searchInfo);
		List<UserTeacher> list = UserTeacherDaoImpl.selectPage(pageNum, pageSize, searchInfo);
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
		return teacherDao.selectPasswordByPhone(phone);
	}

}
