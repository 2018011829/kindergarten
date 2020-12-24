package com.group.kindergarten.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @clsName DBUtil
 * @description 数据库工具类，用于打开或关闭连接
 * @author lrf
 *
 */
public class DBUtil {
	
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取数据库连接
	 * @return 数据库连接对象
	 */
	public static Connection getConnection() {
		try {
			return DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/kindergarten?useUnicode=true&characterEncoding=utf-8", "root", "");
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 关闭数据库连接
	 * @param rs
	 * @param pstm
	 * @param con
	 */
	public static void close(ResultSet rs, PreparedStatement pstm, Connection con) {
		try {
			if(rs!=null)
				rs.close();
			if(pstm!=null)
				pstm.close();
			if(con!=null)
				con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
