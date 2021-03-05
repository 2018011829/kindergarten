package com.group.kindergarten.contact.service;

import java.util.List;

import com.google.gson.Gson;
import com.group.kindergarten.contact.dao.RequestDaoImpl;
import com.group.kindergarten.contact.entity.RequestEntity;
import com.group.kindergarten.user.entity.User;
import com.group.kindergarten.user.service.UserService;

public class RequestService {
	private static RequestService requestService;
	private static RequestDaoImpl daoImpl;
	private static Gson gson;

	/**
	 * 获取requestService实例
	 * 
	 * @return
	 */
	public static RequestService getInstance() {
		if (null == requestService) {
			requestService = new RequestService();
		}
		if (null == daoImpl) {
			daoImpl = RequestDaoImpl.getInstance();
		}
		if (null == gson) {
			gson = new Gson();
		}
		return requestService;

	}

	/**
	 * 获取请求我的
	 * 
	 * @param id
	 * @return
	 */
	public String getRequestsById(int id) {
		List<RequestEntity> entities = daoImpl.getRequestsById(id);
		if (entities.size() == 0) {
			return "没有请求的好友";
		} else {
			return gson.toJson(entities);
		}
	}

	/**
	 * 获取我的请求
	 * 
	 * @param id
	 * @return
	 */
	public String getMyRequestsById(int id) {
		List<RequestEntity> entities = daoImpl.getMyRequestsById(id);
		if (entities.size() == 0) {
			return "没有发送请求";
		} else {
			return gson.toJson(entities);
		}
	}

	/**
	 * 添加请求
	 * 
	 * @param reqId
	 * @param resp
	 * @param message
	 * @return
	 */
	public boolean addRequest(int reqId, int respId, String message, String remark) {
		if (remark == null) {
			String userStr = UserService.getInstance().getOneUserInfoById(respId);
			User user = gson.fromJson(userStr, User.class);
			remark = user.getNickname();
		}
		return daoImpl.addRequest(reqId, respId, message, remark);
	}

	/**
	 * 处理请求
	 * 
	 * @param reqId
	 * @param respId
	 * @param status
	 * @param remark
	 * @return boolean
	 */
	public boolean handleRequest(int reqId, int respId, int status, String remark) {

		if (status == 1) {// 同意请求
			System.out.println(reqId + "请求" + respId + "的修改状态为" + status);
			if (status == 1) {// 同意请求，添加好友
				RemarkService remarkService = RemarkService.getInstance();
				RequestEntity entity = daoImpl.getRequestEntity(reqId, respId);
				// 双向添加好友
				boolean a1 = remarkService.addRemark(reqId, respId, entity.getRemark());
				if (a1) {
					System.out.println(reqId + "添加" + respId + "为好友");
				} else {
					System.out.println(reqId + "添加" + respId + "为好友失败！");
					return false;
				}

				boolean a2 = remarkService.addRemark(respId, reqId, remark);
				if (a2) {
					System.out.println(respId + "添加" + reqId + "为好友");
				} else {
					System.out.println(respId + "添加" + reqId + "为好友失败！");
					return false;
				}
			}
		}
		return daoImpl.handleRequest(reqId, respId, status, remark);
	}
}
