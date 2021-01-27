package com.group.kindergarten.contact.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.group.kindergarten.util.DBUtil;

public class RemarkDaoImpl {
	public static Connection connection;
	public static RemarkDaoImpl remarkDaoImpl;
	public static PreparedStatement preparedStatement;

	/**
	 * 得到RemarkDaoImpl示例
	 * 
	 * @return RemarkDaoImpl
	 */
	public static RemarkDaoImpl getInstance() {
		if (null == remarkDaoImpl) {
			remarkDaoImpl = new RemarkDaoImpl();
		}
		if (null == connection) {
			connection = DBUtil.getConnection();
		}
		return remarkDaoImpl;
	}

	/**
	 * 添加备注
	 * 
	 * @param fromUser  添加者
	 * @param toUser    被添加者
	 * @param remarkStr 备注
	 * @return boolean
	 */
	public boolean addRemark(int fromUser, int toUser, String remarkStr) {
		boolean b = false;

		try {
			preparedStatement = connection
					.prepareStatement("insert into contact_remark(from_user,to_user,remark_str) values (?,?,?)");
			preparedStatement.setInt(1, fromUser);
			preparedStatement.setInt(2, toUser);
			preparedStatement.setString(3, remarkStr);
			int rows = preparedStatement.executeUpdate();
			if (rows > 0) {
				b = true;
				System.out.println("添加备注成功");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return b;
	}
	
	public String getContactByPhone(String phone) { 
		String sql = "select (id,nickname,avatar) from user where id is in(select to_user from contact_remark where ?='"+ phone
				+ "')";

		try {
			preparedStatement = connection
					.prepareStatement(sql);
		
			preparedStatement.setString(1,phone);
			ResultSet resultSet = preparedStatement.executeQuery();
			
//			if (rows > 0) {
//				System.out.println("添加备注成功");
//			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
