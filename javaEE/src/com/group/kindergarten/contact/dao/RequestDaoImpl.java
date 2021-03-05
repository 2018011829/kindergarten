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
	 * 获取dao示例和connection对象
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
	 * 获取请求我的请求 不限制结果
	 * 
	 * @return 查询结果 0：无结果 1：同意 2：不同意
	 */
	public List<RequestEntity> getRequestsById(int id) {
		List<RequestEntity> requestEntities = new ArrayList<RequestEntity>();
		String sql = "select from contact_request where resp_user=?";
		try {
			// 查询到phone对应的id
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
	 * 获取我的请求
	 * 
	 * @param id
	 * @return 查询结果 0：无结果 1：同意 2：不同意
	 */
	public List<RequestEntity> getMyRequestsById(int id) {
		List<RequestEntity> requestEntities = new ArrayList<RequestEntity>();
		String sql = "select from contact_request where req_user=?)";
		try {
			// 查询到phone对应的id
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
	 * 添加请求
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
				System.out.println(reqId + "请求添加" + respId + "为好友");
			}
			System.out.println(requestEntities.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return b;
	}

	/**
	 * 修改请求结果
	 * 
	 * @param reqId
	 * @param respId
	 * @param status
	 * @param 同意方对请求方的备注
	 * @return
	 */
	public boolean handleRequest(int reqId, int respId, int status, String remark) {
		boolean b = false;
		String sql = "update contact_request set status=? where req_user=? and resp_user=?";
		try {
			// 查询到phone对应的id
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
	 * 获取一条请求
	 * 
	 * @param reqId
	 * @param respId
	 * @return RequestEntity
	 */
	public RequestEntity getRequestEntity(int reqId, int respId) {
		RequestEntity entity = new RequestEntity();
		String sql = "select from contact_request where req_user=? and resp_user=?";
		try {
			// 查询到phone对应的id
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
