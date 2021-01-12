package com.group.kindergarten.my.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.group.kindergarten.my.entity.Child;
import com.group.kindergarten.util.DBUtil;


public class ChildrenDao {
	public static Connection connection;
	public static ChildrenDao childrenDao;
	public static PreparedStatement preparedStatement;
	
	/**
	 * 获取ChildrenDao实例
	 * @return ChildrentDao
	 */
	public static ChildrenDao getInstance() {
		if(null==childrenDao) {
			childrenDao=new ChildrenDao();
		}
		if(null==connection) {
			connection= DBUtil.getConnection();
		}
		return childrenDao;
	}
	
	/**
	 * 向数据库添加一个孩子信息
	 * @param name 姓名
	 * @param grade 年级
	 * @param sex 性别
	 * @return 添加是否成功
	 */
	public boolean addChild(String name,String grade,String sex,String parentPhone) {
		boolean b=false;
		try {
			preparedStatement=connection.prepareStatement("insert into child(name,grade,sex,parentPhone) values(?,?,?,?)");
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, grade);
			preparedStatement.setString(3, sex);
			preparedStatement.setString(4, parentPhone);
			int rows=preparedStatement.executeUpdate();
			if(rows>0) {
				b=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return b;
	}
	
	/**
	 * 
	 * @param name
	 * @param parentPhone
	 * @return true: 该孩子已存在
	 */
	public boolean searchChild(String name,String parentPhone) {
		boolean b=false;
		try {
			preparedStatement=connection.prepareStatement("select * from child where parentPhone=? and name=?");
			preparedStatement.setString(2, name);
			preparedStatement.setString(1, parentPhone);
			ResultSet rs=preparedStatement.executeQuery();
			if(rs.next()) {
				b=true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return b;
	}
	
	/**
	 * 根据家长电话查询孩子信息
	 * */
	public List<Child> queryChildrenByPhone(String query) {
		String sql="select * from child where parentPhone=?";
		List<Child> children=new ArrayList<Child>();
		try {
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, query);
			ResultSet resultSet=preparedStatement.executeQuery();
			while(resultSet.next()) {
				Child child=new Child();
				child.setId(resultSet.getInt(1));
				child.setName(resultSet.getString(2));
				child.setGrade(resultSet.getString(3));
				child.setSex(resultSet.getString(4));
				child.setParentPhone(resultSet.getString(5));
				children.add(child);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return children;
	}
}
