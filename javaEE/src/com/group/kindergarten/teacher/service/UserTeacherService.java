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
	 * �õ�һ��teacherServiceʵ��
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
	 * ע���û�
	 * 
	 * @param phone
	 * @param nickname
	 * @param password
	 * @return true:�ֻ�����ע��ɹ�; false:�ֻ�����ע��ʧ��
	 */
	public boolean resigter(String phone, String nickname, String password) {
		boolean b = false;
		b = teacherDao.addParent(phone, nickname, password);

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
		if (teacherDao.isExist(phone)) {
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
		if (teacherDao.isExistUser(phone, password)) {
			b = true;
		}
		return b;
	}

	/**
	 * ����ĳ���̎���Ϣ��Json��
	 * 
	 * @param phone
	 * @return json��
	 */
	public String getOneParentInfo(String phone) {
		return new Gson().toJson(teacherDao.selectOneParent(phone));
	}

	public String searchParentsByPhone(String query) {
		return new Gson().toJson(teacherDao.queryParentsByPhone(query));

	}

	/**
	 * �����ֻ��Ÿ���ָ���ҳ���Ϣ
	 * 
	 * @return �����Ƿ�ɹ�
	 */
	public Boolean updateParentMessage(String phone, String nickName, String headName) {

		return teacherDao.updateParentMessage(phone, nickName, headName);

	}

	/**
	 * ��ȡһҳ���û���Ϣ�����ں�̨����ϵͳչʾ
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
		System.out.println("����page�е�count" + count);
		System.out.println("����page�е�list" + list.size());
		System.out.println(list.get(2).getNickname());
		return page;
	}

	public Page<UserTeacher> getPage(int pageNum, int pageSize, String searchInfo) {
		Page<UserTeacher> page = new Page<UserTeacher>(pageNum, pageSize);
		int count = UserTeacherDaoImpl.countAll(searchInfo);
		List<UserTeacher> list = UserTeacherDaoImpl.selectPage(pageNum, pageSize, searchInfo);
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
		return teacherDao.selectPasswordByPhone(phone);
	}

}
