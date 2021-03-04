package com.group.kindergarten.student.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.group.kindergarten.applyinfo.dao.EnterDao;
import com.group.kindergarten.applyinfo.entity.ApplyInfo;
import com.group.kindergarten.student.entity.Students;
import com.group.kindergarten.util.DBUtil;

public class StudentsDao {
	private static Connection connection;
	private static StudentsDao studentsDao;
	private PreparedStatement preparedStatement;

	/**
	 * 获取EnterDao实例
	 * 
	 * @return EnterDao
	 */
	public static StudentsDao getInstance() {
		if (null == studentsDao) {
			studentsDao = new StudentsDao();
		}
		if (null == connection) {
			connection = DBUtil.getConnection();
		}
		return studentsDao;
	}

	public int countByPage() {
		ResultSet rs = null;
		int count = 0;
		try {
			String sql = "select count(id) from students";
			preparedStatement = connection.prepareStatement(sql);
			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

	public int searchCountByPage(String babyName) {
		ResultSet rs = null;
		int count = 0;
		try {
			String sql = "select count(id) from students where babyName like '%" + babyName + "%'";
			preparedStatement = connection.prepareStatement(sql);
			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

	/**
	 * 分页查询所有报名的孩子的所有信息
	 * 
	 * @return list包含孩子信息的集合
	 */
	public List<Students> searchChildByPage(int pageNum, int pageSize) {
		List<Students> list = null;
		String sql = "select * from students limit ?, ?";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, (pageNum - 1) * pageSize);
			preparedStatement.setInt(2, pageSize);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs != null) {
				list = new ArrayList<>();
				while (rs.next()) {
					Students students = new Students(rs.getInt("id"), rs.getString("userNumber"),
							rs.getString("babyName"), rs.getString("babyClass"), rs.getString("babyBirthday"),
							rs.getString("babySex"), rs.getString("babyIDnumber"), rs.getString("babyAddoAllergies"),
							rs.getString("parentName1"), rs.getString("relation1"), rs.getString("parentIDnumber1"),
							rs.getString("phoneNumber1"), rs.getString("workSpace1"), rs.getString("homeAddress1"),
							rs.getString("parentName2"), rs.getString("relation2"), rs.getString("parentIDnumber2"),
							rs.getString("phoneNumber2"), rs.getString("workSpace2"), rs.getString("homeAddress2"));
					list.add(students);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	/**
	 * 根据名字查询孩子报名信息
	 * 
	 * @return list包含孩子信息的集合
	 */
	public List<Students> searchChildByName(int pageNum, int pageSize, String babyName) {
		List<Students> list = null;
		String sql = "select * from students where babyName like '%" + babyName + "%' limit ?, ?";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, (pageNum - 1) * pageSize);
			preparedStatement.setInt(2, pageSize);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs != null) {
				list = new ArrayList<>();
				while (rs.next()) {
					Students students = new Students(rs.getInt("id"), rs.getString("userNumber"),
							rs.getString("babyName"), rs.getString("babyClass"), rs.getString("babyBirthday"),
							rs.getString("babySex"), rs.getString("babyIDnumber"), rs.getString("babyAddoAllergies"),
							rs.getString("parentName1"), rs.getString("relation1"), rs.getString("parentIDnumber1"),
							rs.getString("phoneNumber1"), rs.getString("workSpace1"), rs.getString("homeAddress1"),
							rs.getString("parentName2"), rs.getString("relation2"), rs.getString("parentIDnumber2"),
							rs.getString("phoneNumber2"), rs.getString("workSpace2"), rs.getString("homeAddress2"));
					list.add(students);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

}
