package com.group.kindergarten.contact.service;

import java.util.List;

import com.google.gson.Gson;
import com.group.kindergarten.contact.dao.RemarkDaoImpl;
import com.group.kindergarten.contact.entity.LCChatKitUser;
import com.group.kindergarten.util.Page;

public class RemarkService {
	private static RemarkService remarkService;
	private static RemarkDaoImpl remarkDaoImpl;
	private static Gson gson;

	/**
	 * �õ�һ��parentServiceʵ��
	 * 
	 * @return
	 */
	public static RemarkService getInstance() {
		if (null == remarkService) {
			remarkService = new RemarkService();
		}
		if (null == remarkDaoImpl) {
			remarkDaoImpl = RemarkDaoImpl.getInstance();
		}
		if (null == gson) {
			gson = new Gson();
		}
		return remarkService;
	}
	
	/**
	 * ��ȡ�����ϵ��
	 * @param phone
	 * @return
	 */
	public List<LCChatKitUser> getContactByPhone(String phone) {
		
		return new RemarkDaoImpl().getContactByPhone(phone);
	}

//	/**
//	 * ע���û�
//	 * 
//	 * @param phone
//	 * @param nickname
//	 * @param password
//	 * @return true:�ֻ�����ע��ɹ�; false:�ֻ�����ע��ʧ��
//	 */
//	public boolean resigter(String phone, String nickname, String password) {
//		boolean b = false;
//		b = remarkDaoImpl.addRemark(phone, nickname, password);
//
//		return b;
//	}
//
//	/**
//	 * �����û����ֻ����Ƿ��Ѿ�ע��
//	 * 
//	 * @param phone
//	 * @return
//	 */
//	public boolean isExistPhone(String phone) {
//		boolean b = false;
//		if (parentDao.isExist(phone)) {
//			b = true;
//		}
//		return b;
//	}
//
//	/**
//	 * �ж��û����������Ƿ�ƥ�� ���û��Ƿ����
//	 * 
//	 * @param phone
//	 * @param password
//	 * @return
//	 */
//	public boolean isExistUser(String phone, String password) {
//		boolean b = false;
//		if (parentDao.isExistUser(phone, password)) {
//			b = true;
//		}
//		return b;
//	}
//
//	/**
//	 * ����ĳ����ĸ��Ϣ��Json��
//	 * 
//	 * @param phone
//	 * @return json��
//	 */
//	public String getOneParentInfo(String phone) {
//		return new Gson().toJson(parentDao.selectOneParent(phone));
//	}
//
//	public String searchParentsByPhone(String query) {
//		return new Gson().toJson(parentDao.queryParentsByPhone(query));
//
//	}
//
//	/**
//	 * �����ֻ��Ÿ���ָ���ҳ���Ϣ
//	 * 
//	 * @return �����Ƿ�ɹ�
//	 */
//	public Boolean updateParentMessage(String phone, String nickName, String headName) {
//
//		return parentDao.updateParentMessage(phone, nickName, headName);
//
//	}
//
//	/**
//	 * ��ȡһҳ���û���Ϣ�����ں�̨����ϵͳչʾ
//	 * 
//	 * @param pageNum
//	 * @param pageSize
//	 * @return
//	 */
//	public Page<UserParent> getPage(int pageNum, int pageSize) {
//		Page<UserParent> page = new Page<UserParent>(pageNum, pageSize);
//		int count = UserParentDaoImpl.countAll();
//		List<UserParent> list = UserParentDaoImpl.selectPage(pageNum, pageSize);
//		page.setList(list);
//		page.setTotalCount(count);
//		System.out.println("����page�е�count" + count);
//		System.out.println("����page�е�list" + list.size());
//		System.out.println(list.get(2).getNickname());
//		return page;
//	}
//
//	public Page<UserParent> getPage(int pageNum, int pageSize, String searchInfo) {
//		Page<UserParent> page = new Page<UserParent>(pageNum, pageSize);
//		int count = UserParentDaoImpl.countAll(searchInfo);
//		List<UserParent> list = UserParentDaoImpl.selectPage(pageNum, pageSize, searchInfo);
//		page.setList(list);
//		page.setTotalCount(count);
//		System.out.println("����page�е�count" + count);
//		System.out.println("����page�е�list" + list.size());
////		System.out.println(list.get(2).getNickname());
//		return page;
//	}
//
//	/**
//	 * ͨ���ֻ������ȡ���룬�����ͻ���ʹ���ֻ���֤���¼
//	 * 
//	 * @param phone
//	 * @return
//	 */
//	public String getPassword(String phone) {
//		return parentDao.selectPasswordByPhone(phone);
//	}

}
