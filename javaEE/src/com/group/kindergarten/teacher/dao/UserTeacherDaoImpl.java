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
	 * ��ȡteacherDaoʵ��
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
	 * �����ݿ����һ���̎���Ϣ
	 * 
	 * @param phone    �绰����
	 * @param nickname �ǳ�
	 * @param password ����
	 * @return ����Ƿ�ɹ�
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
				System.out.println("3.ע��ɹ�");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return b;
	}

	/**
	 * �жϸ��ֻ�����ĸ�ĸ�Ƿ����
	 * 
	 * @param phone �ֻ�����
	 * @return �����Ƿ����
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
	 * �ж��û����������Ƿ�ƥ�� ���û��Ƿ����
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
	 * ����ĳ��Parent����Ϣ
	 * 
	 * @param phone ���ݺ�����Ҹø�ĸ
	 * @return Parent����
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
	 * ��ҳ��ѯ
	 * ��ѯ���еĸ�ĸ��Ϣ���������̨����ϵͳչʾ
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
	 * �����ֻ��Ÿ���ָ���ҳ���Ϣ
	 * 
	 * @return �����Ƿ�ɹ�
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
	 * ��ѯһҳ�����ݣ�
	 * @param pageNum ��ǰҳ��
	 * @param pageSize ÿҳ�����ݸ���
	 * @return ����picture��list����
	 */
	public static List<UserTeacher> selectPage(int pageNum, int pageSize) {
		List<UserTeacher> list=new ArrayList<UserTeacher>();
		try {
			preparedStatement=connection.prepareStatement("select * from user_teacher limit ?,?");
			preparedStatement.setInt(1, (pageNum-1)*pageSize);
			preparedStatement.setInt(2, pageSize);
			ResultSet resultSet=preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				System.out.println("���һ���û�");
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
				System.out.println("���һ���û�");
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
	 * �����ݿ���в�ѯĳ���û�������
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