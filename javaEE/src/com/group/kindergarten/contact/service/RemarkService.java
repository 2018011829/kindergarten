package com.group.kindergarten.contact.service;

import java.util.List;

import com.google.gson.Gson;
import com.group.kindergarten.contact.dao.RemarkDaoImpl;
import com.group.kindergarten.contact.entity.LCChatKitUser;
import com.group.kindergarten.user.entity.User;
import com.group.kindergarten.user.service.UserService;

public class RemarkService {
	private static RemarkService remarkService;
	private static RemarkDaoImpl remarkDaoImpl;
	private static Gson gson;

	/**
	 * 得到一个parentService实例
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
	 * 获取相关联系人
	 * 
	 * @param phone
	 * @return
	 */
	public List<LCChatKitUser> getContactByPhone(String phone) {

		return new RemarkDaoImpl().getContactByPhone(phone);
	}

	/**
	 * 添加备注（确立好友关系）
	 * 
	 * @param fromUser
	 * @param toUSer
	 * @return
	 */
	public boolean addRemark(int fromUser, int toUser, String remarkStr) {
		// 若备注为空，则用nikename代替
		if (remarkStr == null) {
			String userStr = UserService.getInstance().getOneUserInfoById(toUser);
			User user = gson.fromJson(userStr, User.class);
			remarkStr = user.getNickname();
		}
		return remarkDaoImpl.addRemark(fromUser, toUser, remarkStr);
	}

	/**
	 * 删除好友（双向删除）
	 * 
	 * @param fromUser
	 * @param toUser
	 * @return
	 */
	public boolean delRemark(int fromUser, int toUser) {
		if (remarkDaoImpl.delRemark(fromUser, toUser)) {
			return remarkDaoImpl.delRemark(toUser, fromUser);
		}
		return false;
	}

}
