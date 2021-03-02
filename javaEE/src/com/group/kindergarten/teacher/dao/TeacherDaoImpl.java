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
	 * ����ʦ��Ϣ���з�ҳ
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
	 * ����ҳ�����ҽ�ʦ��Ϣ
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
	 * ����ְ��ʦ��Ϣ���з�ҳ
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
	 * ����ҳ��������ְ��ʦ��Ϣ
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
	 * ���ݽ�ʦ����ģ�����Ҳ������ҳ
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
	 * ����ҳ���������������ҽ�ʦ
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
	 * ������ְ��ʦ����ģ�����Ҳ������ҳ
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
	 * ����ҳ������������������ְ��ʦ
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
	 * ����id��ȡ��ʦ��Ϣ
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
	 * ��ȡ���н�ʦ��Ϣ
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
	 * ������ʦ
	 * 
	 * @param teacher
	 * @return
	 */
	public boolean addTeacher(Teacher teacher) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		// ��ȡ��ʦ��Ϣ
		String name = teacher.getName();
		String position = teacher.getPosition();
		String phone = teacher.getPhone();
		String picture = teacher.getPicture();
		String motto = teacher.getMotto();
		int n = -1;// �洢����ļ�¼��
		try {
			conn = DBUtil.getConnection();
			String select = "select * from teacher where status=1 and phone='" + phone + "' and name='" + name + "'";
			pstm = conn.prepareStatement(select);
			rs = pstm.executeQuery();
			boolean isExist = rs.next();
			if (!isExist) {
				String sql = "";
				if (picture != null) {// ��Ƭ��Ϊ��
					if (motto != null) {
						sql = "insert into teacher(name,position,phone,picture,motto,status) values('" + name + "','"
								+ position + "','" + phone + "','" + picture + "','" + motto + "',1)";
					} else {
						sql = "insert into teacher(name,position,phone,picture,status) values('" + name + "','"
								+ position + "','" + phone + "','" + picture + "',1)";
					}
				} else {// ��ƬΪ��
					if (motto != null) {
						sql = "insert into teacher(name,position,phone,motto,status) values('" + name + "','" + position
								+ "','" + phone + "','" + motto + "',1)";
					} else {
						sql = "insert into teacher(name,position,phone,status) values('" + name + "','" + position
								+ "','" + phone + "',1)";
					}
				}
				// ����Ϣ���뵽���ݿ����
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
	 * ����idɾ����ʦ
	 * 
	 * @param id
	 * @return
	 */
	public boolean deleteTeacher(int id) {
		Teacher teacher = findTeacherById(id);
		// ��ȡ��ʦ��Ϣ
		String name = teacher.getName();
		String position = teacher.getPosition();
		String phone = teacher.getPhone();
		String picture = teacher.getPicture();
		String motto = teacher.getMotto();
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		int n = -1;// �洢�޸ĵļ�¼
		try {
			conn = DBUtil.getConnection();
			String select = "select * from teacher where status=1 and id=" + id + "";
			pstm = conn.prepareStatement(select);
			rs = pstm.executeQuery();
			boolean isExist = rs.next();
			if (isExist) {
				String sql = "update teacher set name='" + name + "', position='" + position + "', phone='" + phone
						+ "', picture='" + picture + "', motto='" + motto + "', status=0 where id=" + id + "";
				// ����ʦ��Ϣ�����ݿ����ɾ��
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
	 * �޸Ľ�ʦ��Ϣ
	 * 
	 * @param teacher
	 * @return
	 */
	public boolean updateTeacher(Teacher teacher) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		// ��ȡ��ʦ��Ϣ
		int id = teacher.getId();
		String name = teacher.getName();
		String position = teacher.getPosition();
		String phone = teacher.getPhone();
		String picture = teacher.getPicture();
		String motto = teacher.getMotto();
		int n = -1;// �洢����ļ�¼��
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
				// �����º�Ľ�ʦ��Ϣ���뵽���ݿ����
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
