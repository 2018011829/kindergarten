package com.group.kindergarten.user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.group.kindergarten.user.entity.User;
import com.group.kindergarten.util.DBUtil;

public class UserDaoImpl {

	public static Connection connection;
	public static UserDaoImpl userDao;
	public static PreparedStatement preparedStatement;

	/**
	 * ��ȡUserDaoʵ��
	 * 
	 * @return UserDao
	 */
	public static UserDaoImpl getInstance() {
		if (null == userDao) {
			userDao = new UserDaoImpl();
		}
		if (null == connection) {
			connection = DBUtil.getConnection();
		}
		return userDao;
	}

	/**
	 * �����ݿ����һ����ĸ��Ϣ
	 * 
	 * @param phone    �绰����
	 * @param nickname �ǳ�
	 * @param password ����
	 * @return ����Ƿ�ɹ�
	 */
	public boolean addUser(String phone, String nickname, String password,String identity) {
		boolean b = false;
		try {
			preparedStatement = connection
					.prepareStatement("insert into user(phone,nickname,password,identity) values (?,?,?,?)");
			preparedStatement.setString(1, phone);
			preparedStatement.setString(2, nickname);
			preparedStatement.setString(3, password);
			preparedStatement.setString(4, identity);
			int rows = preparedStatement.executeUpdate();
			if (rows > 0) {
				b = true;
				System.out.println("3.ע��ɹ�");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return b;
	}

	/**
	 * �жϸ��ֻ�������û��Ƿ����
	 * 
	 * @param phone �ֻ�����
	 * @return �����Ƿ����
	 */
	public boolean isExist(String phone) {
		String sql = "select * from user where phone = ?";
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
	 * �ж��û����������Ƿ�ƥ�� ���û��Ƿ����
	 * 
	 * @param phone
	 * @param password
	 * @return
	 */
	public boolean isExistUser(String phone, String password) {
		boolean b = false;
		String sql = "select * from user where phone = ? and password = ?";
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
	 * ����ĳ���û�����Ϣ
	 * 
	 * @param phone ���ݺ�����Ҹ��û�
	 * @return User����
	 */
	public User selectOneUser(String phone) {
		User user = new User();
		String sql = "select * from user where phone=?";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, phone);
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			user.setId(resultSet.getInt(1));
			user.setPhone(resultSet.getString(2));
			user.setPassword(resultSet.getString(3));
			user.setNickname(resultSet.getString(4));
			user.setAvatar(resultSet.getString(5));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;

	}

	/**
	 * ��ҳ��ѯ ��ѯ���еĸ�ĸ��Ϣ���������̨����ϵͳչʾ
	 * 
	 * @return
	 */
	public List<User> selectAllUsers() {
		List<User> users = new ArrayList<User>();
		String sql = "select * from user";
		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				User user = new User();
				user.setId(resultSet.getInt(1));
				user.setPhone(resultSet.getString(2));
				user.setPassword(resultSet.getString(3));
				user.setNickname(resultSet.getString(4));
				user.setAvatar(resultSet.getString(5));
				users.add(user);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return users;

	}

	public List<User> queryUsersByPhone(String query) {
		String sql = "select * from user where phone=?";
		List<User> users = new ArrayList<User>();
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, query);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				User user = new User();
				user.setId(resultSet.getInt(1));
				user.setPhone(resultSet.getString(2));
				user.setPassword(resultSet.getString(3));
				user.setNickname(resultSet.getString(4));
				user.setAvatar(resultSet.getString(5));
				users.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return users;
	}

	/**
	 * �����ֻ��Ÿ���ָ��User��Ϣ
	 * 
	 * @return �����Ƿ�ɹ�
	 */
	public Boolean updateUserMessage(String phone, String nickName, String headName,String avatar) {
		boolean b = false;
		try {
			preparedStatement = connection.prepareStatement("update user set avatar=?,nickname=? ,avatar=? where phone=?");
			preparedStatement.setString(1, headName);
			preparedStatement.setString(2, nickName);
			preparedStatement.setString(3, avatar);
			preparedStatement.setString(4, phone);
			int rows = preparedStatement.executeUpdate();
			if (rows > 0) {
				b = true;
				System.out.println("���³ɹ�");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return b;

	}

	/**
	 * ��ѯ���ݿ�һ�����ٸ�����
	 * 
	 * @return
	 */
	public static int countAll() {
		int count = 0;
		try {
			preparedStatement = connection.prepareStatement("select count(id) from user");
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			count = resultSet.getInt(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}

	/**
	 * ��ѯһҳ�����ݣ�
	 * 
	 * @param pageNum  ��ǰҳ��
	 * @param pageSize ÿҳ�����ݸ���
	 * @return ����picture��list����
	 */
	public static List<User> selectPage(int pageNum, int pageSize) {
		List<User> list = new ArrayList<User>();
		try {
			preparedStatement = connection.prepareStatement("select * from user limit ?,?");
			preparedStatement.setInt(1, (pageNum - 1) * pageSize);
			preparedStatement.setInt(2, pageSize);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				System.out.println("���һ���û�");
				User user = new User();
				user.setId(resultSet.getInt(1));
				user.setPhone(resultSet.getString(2));
				user.setPassword(resultSet.getString(3));
				user.setNickname(resultSet.getString(4));
				user.setAvatar(resultSet.getString(5));
				user.setIdentity(resultSet.getString(6));
				list.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public static int countAll(String searchInfo) {
		int count = 0;
		Connection conn = DBUtil.getConnection();
		PreparedStatement pstamt = null;
		String sql = "select count(*) from user where nickname like ? or phone like ?";
		try {
			pstamt = conn.prepareStatement(sql);
			String str = "%" + searchInfo + "%";
			pstamt.setString(1, str);
			pstamt.setString(2, str);
			ResultSet rs = pstamt.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
				System.out.println(count);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	public static List<User> selectPage(int pageNum, int pageSize, String searchInfo) {
		List<User> list = new ArrayList<User>();
		try {
			preparedStatement = connection
					.prepareStatement("select * from user where nickname like ? or phone like ? limit ?,?");
			String str = "%" + searchInfo + "%";
			preparedStatement.setString(1, str);
			preparedStatement.setString(2, str);
			preparedStatement.setInt(3, (pageNum - 1) * pageSize);
			preparedStatement.setInt(4, pageSize);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				System.out.println("���һ���û�");
				User user = new User();
				user.setId(resultSet.getInt(1));
				user.setPhone(resultSet.getString(2));
				user.setPassword(resultSet.getString(3));
				user.setNickname(resultSet.getString(4));
				user.setAvatar(resultSet.getString(5));
				list.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * �����ݿ���в�ѯĳ���û�������
	 * 
	 * @param phone
	 * @return
	 */
	public String selectPasswordByPhone(String phone) {
		String password = "";
		String sql = "select password from user where phone=?";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, phone);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				password = resultSet.getString(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return password;
	}

}
