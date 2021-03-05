package com.group.kindergarten.contact.dao;

import java.sql.Connection;

import com.group.kindergarten.util.DBUtil;

import com.mysql.jdbc.PreparedStatement;

public class RequestDaoImpl {
	public static Connection connection;
	public static RequestDaoImpl daoImpl;
	public static PreparedStatement preparedStatement;
	
	/**
	 * 获取dao示例和connection对象
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
	
//	public
	
	
	

}
