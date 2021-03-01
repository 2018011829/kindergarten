package com.group.kindergarten.server.user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.group.kindergarten.user.entity.User;
import com.group.kindergarten.util.DBUtil;

public class UserDaoImpl {
	
	/**
	 * ���û���Ϣ���з�ҳ
	 * 
	 * @return
	 */
	public int countByPage() {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		int count = 0;
		try {
			String sql = "select count(*) from user";
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
	 * ����ҳ�������û���Ϣ
	 * 
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public List<User> findByPage(int pageNum, int pageSize) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<User> list = new ArrayList<>();
		try {
			String sql = "";
			conn = DBUtil.getConnection();
			sql = "select * from user limit ?, ?";
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, (pageNum - 1) * pageSize);
			pstm.setInt(2, pageSize);
			rs = pstm.executeQuery();
			while (rs.next()) {
				User user = new User();
				user.setId(rs.getInt(1));
				user.setPhone(rs.getString(2));
				user.setNickname(rs.getString(4));
				list.add(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, pstm, conn);
		}
		return list;
	}

	/**
	 * �����û��ֻ���ģ�����Ҳ������ҳ
	 * 
	 * @param phone
	 * @return
	 */
	public int countByPageAndPhone(String phone) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		int count = 0;
		try {
			String sql = "";
			conn = DBUtil.getConnection();
			sql = "select count(id) from user where phone like ?";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, "%" + phone + "%");
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
	 * ����ҳ�����������������û�
	 * 
	 * @param pageNum
	 * @param pageSize
	 * @param phone
	 * @return
	 */
	public List<User> findByPageAndPhone(int pageNum, int pageSize, String phone) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<User> list = new ArrayList<>();
		try {
			String sql = "";
			conn = DBUtil.getConnection();
			sql = "select * from user where phone like ? limit ?, ?";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, "%" + phone + "%");
			pstm.setInt(2, (pageNum - 1) * pageSize);
			pstm.setInt(3, pageSize);
			rs = pstm.executeQuery();
			while (rs.next()) {
				User user = new User();
				user.setId(rs.getInt(1));
				user.setPhone(rs.getString(2));
				user.setNickname(rs.getString(4));
				list.add(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, pstm, conn);
		}
		return list;
	}

	
	/**
	 * ����idɾ���û�
	 * 
	 * @param id
	 * @return
	 */
	public boolean deleteUser(int id) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		int n = -1;// �洢�޸ĵļ�¼
		try {
			conn = DBUtil.getConnection();
			String select = "select * from user where id=" + id + "";
			pstm = conn.prepareStatement(select);
			rs = pstm.executeQuery();
			boolean isExist = rs.next();
			if (isExist) {
				String sql = "delete from user where id=" + id + "";
				// ���û���Ϣ�����ݿ����ɾ��
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
