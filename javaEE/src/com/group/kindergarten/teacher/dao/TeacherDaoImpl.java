package com.group.kindergarten.teacher.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.group.kindergarten.teacher.entity.Teacher;
import com.group.kindergarten.util.DBUtil;

public class TeacherDaoImpl {

	/**
	 * 将教师信息进行分页
	 * 
	 * @return
	 */
	public int countByPage() {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		int count = 0;
		try {
			String sql = "select count(*) from teacher where status=1";
			conn = DBUtil.getConnection();
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, pstm, conn);
		}
		return count;
	}

	/**
	 * 根据页数查找教师信息
	 * 
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public List<Teacher> findByPage(int pageNum, int pageSize) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<Teacher> list = new ArrayList<>();
		try {
			String sql = "";
			conn = DBUtil.getConnection();
			sql = "select * from teacher where status=1 limit ?, ?";
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, (pageNum - 1) * pageSize);
			pstm.setInt(2, pageSize);
			rs = pstm.executeQuery();
			while (rs.next()) {
				Teacher teacher = new Teacher();
				teacher.setId(rs.getInt(1));
				teacher.setName(rs.getString(2));
				teacher.setPosition(rs.getString(3));
				teacher.setPhone(rs.getString(4));
				teacher.setPicture(rs.getString(5));
				teacher.setMotto(rs.getString(6));
				teacher.setStatus(rs.getInt(7));
				list.add(teacher);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, pstm, conn);
		}
		return list;
	}

	/**
	 * 将离职教师信息进行分页
	 * 
	 * @return
	 */
	public int countDeletedByPage() {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		int count = 0;
		try {
			String sql = "select count(*) from teacher where status=0";
			conn = DBUtil.getConnection();
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, pstm, conn);
		}
		return count;
	}

	/**
	 * 根据页数查找离职教师信息
	 * 
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public List<Teacher> findDeletedByPage(int pageNum, int pageSize) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<Teacher> list = new ArrayList<>();
		try {
			String sql = "select * from teacher where status=0 limit ?, ?";
			conn = DBUtil.getConnection();
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, (pageNum - 1) * pageSize);
			pstm.setInt(2, pageSize);
			rs = pstm.executeQuery();
			while (rs.next()) {
				Teacher teacher = new Teacher();
				teacher.setId(rs.getInt(1));
				teacher.setName(rs.getString(2));
				teacher.setPosition(rs.getString(3));
				teacher.setPhone(rs.getString(4));
				teacher.setPicture(rs.getString(5));
				teacher.setMotto(rs.getString(6));
				teacher.setStatus(rs.getInt(7));
				list.add(teacher);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, pstm, conn);
		}
		return list;
	}

	/**
	 * 根据教师姓名模糊查找并将其分页
	 * 
	 * @param name
	 * @return
	 */
	public int countByPageAndName(String name) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		int count = 0;
		try {
			String sql = "";
			conn = DBUtil.getConnection();
			sql = "select count(id) from teacher where name like ? and status=1";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, "%" + name + "%");
			rs = pstm.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, pstm, conn);
		}
		return count;
	}

	/**
	 * 根据页数和搜索条件查找教师
	 * 
	 * @param pageNum
	 * @param pageSize
	 * @param name
	 * @return
	 */
	public List<Teacher> findByPageAndName(int pageNum, int pageSize, String name) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<Teacher> list = new ArrayList<>();
		try {
			String sql = "";
			conn = DBUtil.getConnection();
			sql = "select * from teacher where status=1 and name like ? limit ?, ?";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, "%" + name + "%");
			pstm.setInt(2, (pageNum - 1) * pageSize);
			pstm.setInt(3, pageSize);
			rs = pstm.executeQuery();
			while (rs.next()) {
				Teacher teacher = new Teacher();
				teacher.setId(rs.getInt(1));
				teacher.setName(rs.getString(2));
				teacher.setPosition(rs.getString(3));
				teacher.setPhone(rs.getString(4));
				teacher.setPicture(rs.getString(5));
				teacher.setMotto(rs.getString(6));
				list.add(teacher);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, pstm, conn);
		}
		return list;
	}
	
	/**
	 * 根据离职教师姓名模糊查找并将其分页
	 * 
	 * @param name
	 * @return
	 */
	public int countDeletedByPageAndName(String name) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		int count = 0;
		try {
			String sql = "";
			conn = DBUtil.getConnection();
			sql = "select count(id) from teacher where name like ? and status=0";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, "%" + name + "%");
			rs = pstm.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, pstm, conn);
		}
		return count;
	}

	/**
	 * 根据页数和搜索条件查找离职教师
	 * 
	 * @param pageNum
	 * @param pageSize
	 * @param name
	 * @return
	 */
	public List<Teacher> findDeletedByPageAndName(int pageNum, int pageSize, String name) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<Teacher> list = new ArrayList<>();
		try {
			String sql = "";
			conn = DBUtil.getConnection();
			sql = "select * from teacher where status=0 and name like ? limit ?, ?";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, "%" + name + "%");
			pstm.setInt(2, (pageNum - 1) * pageSize);
			pstm.setInt(3, pageSize);
			rs = pstm.executeQuery();
			while (rs.next()) {
				Teacher teacher = new Teacher();
				teacher.setId(rs.getInt(1));
				teacher.setName(rs.getString(2));
				teacher.setPosition(rs.getString(3));
				teacher.setPhone(rs.getString(4));
				teacher.setPicture(rs.getString(5));
				teacher.setMotto(rs.getString(6));
				list.add(teacher);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, pstm, conn);
		}
		return list;
	}

	/**
	 * 根据id获取教师信息
	 * 
	 * @param id
	 * @return
	 */
	public Teacher findTeacherById(int id) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		Teacher teacher = new Teacher();
		try {
			conn = DBUtil.getConnection();
			pstm = conn.prepareStatement("select * from teacher where status=1 and id=" + id);
			rs = pstm.executeQuery();
			while (rs.next()) {
				teacher.setId(rs.getInt(1));
				teacher.setName(rs.getString(2));
				teacher.setPosition(rs.getString(3));
				teacher.setPhone(rs.getString(4));
				teacher.setPicture(rs.getString(5));
				teacher.setMotto(rs.getString(6));
				teacher.setStatus(rs.getInt(7));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, pstm, conn);
		}
		return teacher;
	}

	/**
	 * 获取所有教师信息
	 * 
	 * @return
	 */
	public List<Teacher> findTeachers() {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<Teacher> list = new ArrayList<>();
		try {
			conn = DBUtil.getConnection();
			pstm = conn.prepareStatement("select * from teacher where status=1");
			rs = pstm.executeQuery();
			while (rs.next()) {
				Teacher teacher = new Teacher();
				teacher.setId(rs.getInt(1));
				teacher.setName(rs.getString(2));
				teacher.setPosition(rs.getString(3));
				teacher.setPhone(rs.getString(4));
				teacher.setPicture(rs.getString(5));
				teacher.setMotto(rs.getString(6));
				teacher.setStatus(rs.getInt(7));
				list.add(teacher);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, pstm, conn);
		}
		return list;
	}

	/**
	 * 新增教师
	 * 
	 * @param teacher
	 * @return
	 */
	public boolean addTeacher(Teacher teacher) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		// 获取教师信息
		String name = teacher.getName();
		String position = teacher.getPosition();
		String phone = teacher.getPhone();
		String picture = teacher.getPicture();
		String motto = teacher.getMotto();
		int n = -1;// 存储插入的记录数
		try {
			conn = DBUtil.getConnection();
			String select = "select * from teacher where status=1 and phone='" + phone + "' and name='" + name + "'";
			pstm = conn.prepareStatement(select);
			rs = pstm.executeQuery();
			boolean isExist = rs.next();
			if (!isExist) {
				String sql = "";
				if (picture != null) {// 照片不为空
					if (motto != null) {
						sql = "insert into teacher(name,position,phone,picture,motto,status) values('" + name + "','"
								+ position + "','" + phone + "','" + picture + "','" + motto + "',1)";
					} else {
						sql = "insert into teacher(name,position,phone,picture,status) values('" + name + "','"
								+ position + "','" + phone + "','" + picture + "',1)";
					}
				} else {// 照片为空
					if (motto != null) {
						sql = "insert into teacher(name,position,phone,motto,status) values('" + name + "','" + position
								+ "','" + phone + "','" + motto + "',1)";
					} else {
						sql = "insert into teacher(name,position,phone,status) values('" + name + "','" + position
								+ "','" + phone + "',1)";
					}
				}
				// 将信息插入到数据库表中
				n = pstm.executeUpdate(sql);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, pstm, conn);
		}
		return n > 0 ? true : false;
	}

	/**
	 * 根据id删除教师
	 * 
	 * @param id
	 * @return
	 */
	public boolean deleteTeacher(int id) {
		Teacher teacher = findTeacherById(id);
		// 获取教师信息
		String name = teacher.getName();
		String position = teacher.getPosition();
		String phone = teacher.getPhone();
		String picture = teacher.getPicture();
		String motto = teacher.getMotto();
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		int n = -1;// 存储修改的记录
		try {
			conn = DBUtil.getConnection();
			String select = "select * from teacher where status=1 and id=" + id + "";
			pstm = conn.prepareStatement(select);
			rs = pstm.executeQuery();
			boolean isExist = rs.next();
			if (isExist) {
				String sql = "update teacher set name='" + name + "', position='" + position + "', phone='" + phone
						+ "', picture='" + picture + "', motto='" + motto + "', status=0 where id=" + id + "";
				// 将教师信息从数据库表中删除
				n = pstm.executeUpdate(sql);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, pstm, conn);
		}
		return n > 0 ? true : false;
	}

	/**
	 * 修改教师信息
	 * 
	 * @param teacher
	 * @return
	 */
	public boolean updateTeacher(Teacher teacher) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		// 获取教师信息
		int id = teacher.getId();
		String name = teacher.getName();
		String position = teacher.getPosition();
		String phone = teacher.getPhone();
		String picture = teacher.getPicture();
		String motto = teacher.getMotto();
		int n = -1;// 存储插入的记录数
		try {
			conn = DBUtil.getConnection();
			String select = "select * from teacher where status=1 and id=" + id + "";
			pstm = conn.prepareStatement(select);
			rs = pstm.executeQuery();
			boolean isExist = rs.next();
			if (isExist) {
				String sql = null;
				if (picture != null) {
					if (motto != null) {
						sql = "update teacher set name='" + name + "', position='" + position + "', phone='" + phone
								+ "', picture='" + picture + "', motto='" + motto + "' where status=1 and id=" + id
								+ "";
					} else {
						sql = "update teacher set name='" + name + "', position='" + position + "', phone='" + phone
								+ "', picture='" + picture + "' where status=1 and id=" + id + "";
					}
				} else {
					if (motto != null) {
						sql = "update teacher set name='" + name + "', position='" + position + "', phone='" + phone
								+ "', motto='" + motto + "' where status=1 and id=" + id + "";
					} else {
						sql = "update teacher set name='" + name + "', position='" + position + "', phone='" + phone
								+ "' where status=1 and id=" + id + "";
					}
				}
				// 将更新后的教师信息插入到数据库表中
				n = pstm.executeUpdate(sql);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, pstm, conn);
		}
		return n > 0 ? true : false;
	}

}
