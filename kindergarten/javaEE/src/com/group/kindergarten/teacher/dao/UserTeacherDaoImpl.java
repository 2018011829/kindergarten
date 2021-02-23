package com.group.kindergarten.teacher.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.group.kindergarten.teacher.entity.UserTeacher;
import com.group.kindergarten.util.DBUtil;

public class UserTeacherDaoImpl {
	public static Connection connection;
	public static UserTeacherDaoImpl teacherDao;
	public static PreparedStatement preparedStatement;

	/**
	 * 获取teacherDao实例
	 * 
	 * @return teacherDao
	 */
	public static UserTeacherDaoImpl getInstance() {
		if (null == teacherDao) {
			teacherDao = new UserTeacherDaoImpl();
		}
		if (null == connection) {
			connection = DBUtil.getConnection(); 
		}
		return teacherDao;
	}

	/**
	 * 向数据库添加一个教師信息
	 * 
	 * @param phone    电话号码
	 * @param nickname 昵称
	 * @param password 密码
	 * @return 添加是否成功
	 */
	public boolean addParent(String phone, String nickname, String password) {
		boolean b = false;
		try {
			preparedStatement = connection
					.prepareStatement("insert into user_teacher(phone,nickname,password) values (?,?,?)");
			preparedStatement.setString(1, phone);
			preparedStatement.setString(2, nickname);
			preparedStatement.setString(3, password);
			int rows = preparedStatement.executeUpdate();
			if (rows > 0) {
				b = true;
				System.out.println("3.注册成功");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return b;
	}

	/**
	 * 判断该手机号码的父母是否存在
	 * 
	 * @param phone 手机号码
	 * @return 号码是否存在
	 */
	public boolean isExist(String phone) {
		String sql = "select * from user_teacher where phone = ?";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, phone);
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
	 * @param phone
	 * @param password
	 * @return
	 */
	public boolean isExistUser(String phone, String password) {
		boolean b = false;
		String sql = "select * from user_teacher where phone = ? and password = ?";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, phone);
			preparedStatement.setString(2, password);
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
	 * 返回某个Parent的信息
	 * 
	 * @param phone 根据号码查找该父母
	 * @return Parent对象
	 */
	public UserTeacher selectOneParent(String phone) {
		UserTeacher parent = new UserTeacher();
		String sql = "select * from user_teacher where phone=?";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, phone);
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			parent.setId(resultSet.getInt(1));
			parent.setPhone(resultSet.getString(2));
			parent.setPassword(resultSet.getString(3));
			parent.setNickname(resultSet.getString(4));
			parent.setAvator(resultSet.getString(5));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return parent;

	}
	
	/**
	 * 分页查询
	 * 查询所有的父母信息，用于向后台管理系统展示
	 * @return
	 */
	public List<UserTeacher> selectAllParent() {
		List<UserTeacher> parents=new ArrayList<UserTeacher>();
		String sql = "select * from user_teacher";
		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				UserTeacher parent = new UserTeacher();
				parent.setId(resultSet.getInt(1));
				parent.setPhone(resultSet.getString(2));
				parent.setPassword(resultSet.getString(3));
				parent.setNickname(resultSet.getString(4));
				parent.setAvator(resultSet.getString(5));
				parents.add(parent);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return parents;

	}

	public List<UserTeacher> queryParentsByPhone(String query) {
		String sql = "select * from user_teacher where phone=?";
		List<UserTeacher> parents = new ArrayList<UserTeacher>();
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, query);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				UserTeacher parent = new UserTeacher();
				parent.setId(resultSet.getInt(1));
				parent.setPhone(resultSet.getString(2));
				parent.setPassword(resultSet.getString(3));
				parent.setNickname(resultSet.getString(4));
				parent.setAvator(resultSet.getString(5));
				parents.add(parent);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return parents;
	}


	/**
	 * 根据手机号更新指定家长信息
	 * 
	 * @return 更新是否成功
	 */
	public Boolean updateParentMessage(String phone,String nickName,String headName) {
		boolean b=false;
		try {
			preparedStatement=connection.prepareStatement("update parent set avatar=?,nickname=? where phone=?");
			preparedStatement.setString(1, headName);
			preparedStatement.setString(2, nickName);
			preparedStatement.setString(3, phone);
			int rows=preparedStatement.executeUpdate();
			if(rows>0) {
				b=true;
				System.out.println("更新成功");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return b;

	}

	
	/**
	 * 查询数据库一共多少个数据
	 * @return
	 */
	public static int countAll() {
		int count=0;
		try {
			preparedStatement =connection.prepareStatement("select count(id) from user_teacher");
			ResultSet resultSet=preparedStatement.executeQuery();
			resultSet.next();
			count=resultSet.getInt(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}

	/**
	 * 查询一页的数据，
	 * @param pageNum 当前页数
	 * @param pageSize 每页的数据个数
	 * @return 返回picture的list集合
	 */
	public static List<UserTeacher> selectPage(int pageNum, int pageSize) {
		List<UserTeacher> list=new ArrayList<UserTeacher>();
		try {
			preparedStatement=connection.prepareStatement("select * from user_teacher limit ?,?");
			preparedStatement.setInt(1, (pageNum-1)*pageSize);
			preparedStatement.setInt(2, pageSize);
			ResultSet resultSet=preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				System.out.println("添加一个用户");
				UserTeacher parent=new UserTeacher();
				parent.setId(resultSet.getInt(1));
				parent.setPhone(resultSet.getString(2));
				parent.setPassword(resultSet.getString(3));
				parent.setNickname(resultSet.getString(4));
				parent.setAvator(resultSet.getString(5));
				list.add(parent);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public static int countAll(String searchInfo) {
		int count=0;
		Connection conn=DBUtil.getConnection();
		PreparedStatement pstamt=null;
		String sql="select count(*) from user_teacher where nickname like ? or phone like ?";
		try {
			pstamt=conn.prepareStatement(sql);
			String str="%"+searchInfo+"%";
			pstamt.setString(1, str);
			pstamt.setString(2, str);
			ResultSet rs=pstamt.executeQuery();
			if(rs.next()) {
				count=rs.getInt(1);
				System.out.println(count);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	public static List<UserTeacher> selectPage(int pageNum, int pageSize, String searchInfo) {
		List<UserTeacher> list=new ArrayList<UserTeacher>();
		try {
			preparedStatement=connection.prepareStatement("select * from user_teacher where nickname like ? or phone like ? limit ?,?");
			String str="%"+searchInfo+"%";
			preparedStatement.setString(1, str);
			preparedStatement.setString(2, str);
			preparedStatement.setInt(3, (pageNum-1)*pageSize);
			preparedStatement.setInt(4, pageSize);
			ResultSet resultSet=preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				System.out.println("添加一个用户");
				UserTeacher parent=new UserTeacher();
				parent.setId(resultSet.getInt(1));
				parent.setPhone(resultSet.getString(2));
				parent.setPassword(resultSet.getString(3));
				parent.setNickname(resultSet.getString(4));
				parent.setAvator(resultSet.getString(5));
				list.add(parent);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 在数据库表中查询某个用户的密码
	 * @param phone
	 * @return
	 */
	public String selectPasswordByPhone(String phone) {
		String password="";
		String sql="select password from user_teacher where phone=?";
		try {
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, phone);
			ResultSet resultSet=preparedStatement.executeQuery();
			if(resultSet.next()) {
				password=resultSet.getString(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return password;
	}
	
}