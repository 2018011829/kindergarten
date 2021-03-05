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
	 * ��ȡrequestServiceʵ��
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
	 * ��ȡ�����ҵ�
	 * 
	 * @param id
	 * @return
	 */
	public String getRequestsById(int id) {
		List<RequestEntity> entities = daoImpl.getRequestsById(id);
		if (entities.size() == 0) {
			return "û������ĺ���";
		} else {
			return gson.toJson(entities);
		}
	}

	/**
	 * ��ȡ�ҵ�����
	 * 
	 * @param id
	 * @return
	 */
	public String getMyRequestsById(int id) {
		List<RequestEntity> entities = daoImpl.getMyRequestsById(id);
		if (entities.size() == 0) {
			return "û�з�������";
		} else {
			return gson.toJson(entities);
		}
	}

	/**
	 * �������
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
	 * ��������
	 * 
	 * @param reqId
	 * @param respId
	 * @param status
	 * @param remark
	 * @return boolean
	 */
	public boolean handleRequest(int reqId, int respId, int status, String remark) {

		if (status == 1) {// ͬ������
			System.out.println(reqId + "����" + respId + "���޸�״̬Ϊ" + status);
			if (status == 1) {// ͬ��������Ӻ���
				RemarkService remarkService = RemarkService.getInstance();
				RequestEntity entity = daoImpl.getRequestEntity(reqId, respId);
				// ˫����Ӻ���
				boolean a1 = remarkService.addRemark(reqId, respId, entity.getRemark());
				if (a1) {
					System.out.println(reqId + "���" + respId + "Ϊ����");
				} else {
					System.out.println(reqId + "���" + respId + "Ϊ����ʧ�ܣ�");
					return false;
				}

				boolean a2 = remarkService.addRemark(respId, reqId, remark);
				if (a2) {
					System.out.println(respId + "���" + reqId + "Ϊ����");
				} else {
					System.out.println(respId + "���" + reqId + "Ϊ����ʧ�ܣ�");
					return false;
				}
			}
		}
		return daoImpl.handleRequest(reqId, respId, status, remark);
	}
}
