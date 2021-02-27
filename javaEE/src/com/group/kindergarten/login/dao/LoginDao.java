package com.group.kindergarten.login.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.group.kindergarten.util.DBUtil;


public class LoginDao {
	public static Connection connection;
	public static LoginDao loginDao;
	public static PreparedStatement preparedStatement;

	/**
	 * 获取ParentDao实例
	 * 
	 * @return ParentDao
	 */
	public static LoginDao getInstance() {
		if (null == loginDao) {
			loginDao = new LoginDao();
		}
		if (null == connection) {
			connection = DBUtil.getConnection();
		}
		return loginDao;
	}
	
	/**
	 * 判断用户名是否存在
	 * 
	 * @param userName 用户名
	 * @return 是否存在
	 */
	public boolean isExist(String userName) {
		String sql = "select * from admin where user_name = ?";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, userName);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 判断用户名和密码是否匹配 即用户是否存在
	 * 
	 * @param userName
	 * @param userPwd
	 * @return
	 */
	public boolean isExistUser(String userName, String userPwd) {
		boolean b = false;
		String sql = "select * from admin where user_name = ? and password = ?";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, userName);
			preparedStatement.setString(2, userPwd);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				b = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return b;
	}
	
	/**
	 * 注册用户
	 * @param userName
	 * @param password
	 * @return
	 */
	public boolean registerUser(String userName,String password) {
		boolean b=false;
		String sql="insert into admin(user_name,password) values(?,?)";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, userName);
			preparedStatement.setString(2, password);
			int row=preparedStatement.executeUpdate();
			if(row>0) {
				b=true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return b;
	}

	public static String searchPwd(String userName) {
		String pwd=null;
		String sql = "select * from admin where user_name = ?";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, userName);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				pwd=resultSet.getString("password");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pwd;
	}

	public boolean updatePwd(String userName,String password) {
		boolean b=false;
		Connection conn=DBUtil.getConnection();
		PreparedStatement pstamt=null;
		String sql="update admin set password=? where user_name = ?";
		try {
			pstamt=conn.prepareStatement(sql);
			pstamt.setString(1, password);
			pstamt.setString(2, userName);
			int row=pstamt.executeUpdate();
			if(row>0) {
				b=true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return b;
	}
}
