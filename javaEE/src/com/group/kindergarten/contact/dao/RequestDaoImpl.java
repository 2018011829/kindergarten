package com.group.kindergarten.contact.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.group.kindergarten.contact.entity.RequestEntity;
import com.group.kindergarten.util.DBUtil;

public class RequestDaoImpl {
	public static Connection connection;
	public static RequestDaoImpl daoImpl;
	public static PreparedStatement preparedStatement;

	/**
	 * ��ȡdaoʾ����connection����
	 * 
	 * @return daoimpl
	 */
	public static RequestDaoImpl getInstance() {
		if (null == daoImpl) {
			daoImpl = new RequestDaoImpl();
		}
		if (null == connection) {
			connection = DBUtil.getConnection();
		}
		return daoImpl;
	}

	/**
	 * ��ȡ�����ҵ����� �����ƽ��
	 * 
	 * @return ��ѯ��� 0���޽�� 1��ͬ�� 2����ͬ��
	 */
	public List<RequestEntity> getRequestsById(int id) {
		List<RequestEntity> requestEntities = new ArrayList<RequestEntity>();
		String sql = "select from contact_request where resp_user=?";
		try {
			// ��ѯ��phone��Ӧ��id
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				RequestEntity entity = new RequestEntity();
				entity.setId(resultSet.getInt(1));
				entity.setReqUser(resultSet.getInt(2));
				entity.setRespUser(id);
				entity.setStatus(resultSet.getInt(4));
				entity.setMessage(resultSet.getString(5));
				entity.setRemark(resultSet.getString(6));
				requestEntities.add(entity);
			}
			System.out.println(requestEntities.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return requestEntities;
	}

	/**
	 * ��ȡ�ҵ�����
	 * 
	 * @param id
	 * @return ��ѯ��� 0���޽�� 1��ͬ�� 2����ͬ��
	 */
	public List<RequestEntity> getMyRequestsById(int id) {
		List<RequestEntity> requestEntities = new ArrayList<RequestEntity>();
		String sql = "select from contact_request where req_user=?)";
		try {
			// ��ѯ��phone��Ӧ��id
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				RequestEntity entity = new RequestEntity();
				entity.setId(resultSet.getInt(1));
				entity.setReqUser(resultSet.getInt(2));
				entity.setRespUser(resultSet.getInt(3));
				entity.setStatus(resultSet.getInt(4));
				entity.setMessage(resultSet.getString(5));
				entity.setRemark(resultSet.getString(6));
				requestEntities.add(entity);
			}
			System.out.println(requestEntities.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return requestEntities;
	}

	/**
	 * �������
	 * 
	 * @param reqId
	 * @param respId
	 * @param remark
	 * @return boolean
	 */
	public boolean addRequest(int reqId, int respId, String message, String remark) {
		boolean b = false;
		List<RequestEntity> requestEntities = new ArrayList<RequestEntity>();
		String sql = "insert into contact_request(req_user,resp_user,status,message,remark) values(?,?,0,?,?)";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, reqId);
			preparedStatement.setInt(2, respId);
			preparedStatement.setString(3, message);
			preparedStatement.setString(4, remark);
			int rows = preparedStatement.executeUpdate();
			if (rows > 0) {
				b = true;
				System.out.println(reqId + "�������" + respId + "Ϊ����");
			}
			System.out.println(requestEntities.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return b;
	}

	/**
	 * �޸�������
	 * 
	 * @param reqId
	 * @param respId
	 * @param status
	 * @param ͬ�ⷽ�����󷽵ı�ע
	 * @return
	 */
	public boolean handleRequest(int reqId, int respId, int status, String remark) {
		boolean b = false;
		String sql = "update contact_request set status=? where req_user=? and resp_user=?";
		try {
			// ��ѯ��phone��Ӧ��id
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, reqId);
			preparedStatement.setInt(2, respId);
			preparedStatement.setInt(3, status);

			int rows = preparedStatement.executeUpdate();
			if (rows > 0) {
				b = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return b;
	}

	/**
	 * ��ȡһ������
	 * 
	 * @param reqId
	 * @param respId
	 * @return RequestEntity
	 */
	public RequestEntity getRequestEntity(int reqId, int respId) {
		RequestEntity entity = new RequestEntity();
		String sql = "select from contact_request where req_user=? and resp_user=?";
		try {
			// ��ѯ��phone��Ӧ��id
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, reqId);
			preparedStatement.setInt(2, respId);

			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				entity.setId(resultSet.getInt(1));
				entity.setReqUser(resultSet.getInt(2));
				entity.setRespUser(resultSet.getInt(3));
				entity.setStatus(resultSet.getInt(4));
				entity.setMessage(resultSet.getString(5));
				entity.setRemark(resultSet.getString(6));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return entity;

	}

}
