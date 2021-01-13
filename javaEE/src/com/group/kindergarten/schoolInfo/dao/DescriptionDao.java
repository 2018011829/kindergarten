package com.group.kindergarten.schoolInfo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.group.kindergarten.schoolInfo.entity.Description;
import com.group.kindergarten.teacher.entity.Teacher;
import com.group.kindergarten.util.DBUtil;

public class DescriptionDao {
	/**
	 * ��У԰������Ϣ���з�ҳ
	 * 
	 * @return
	 */
	public int countByPage() {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		int count = 0;
		try {
			String sql = "";
			conn = DBUtil.getConnection();
			sql = "select count(*) from kindergarten_environment_description";
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
	 * ����ҳ������У԰������Ϣ
	 * 
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public List<Description> findByPage(int pageNum, int pageSize) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<Description> list = new ArrayList<>();
		try {
			String sql = "";
			conn = DBUtil.getConnection();
			sql = "select * from kindergarten_environment_description limit ?, ?";
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, (pageNum - 1) * pageSize);
			pstm.setInt(2, pageSize);
			rs = pstm.executeQuery();
			while (rs.next()) {
				Description description = new Description();
				description.setId(rs.getInt(1));
				description.setDescription(rs.getString(2));
				list.add(description);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, pstm, conn);
		}
		return list;
	}
	
	/**
	 * ��ȡ����У԰������Ϣ
	 * @return
	 */
	public List<Description> findDescription(){
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<Description> list = new ArrayList<>();
		try {
			conn = DBUtil.getConnection();
			pstm = conn.prepareStatement("select * from kindergarten_environment_description");
			rs = pstm.executeQuery();
			while(rs.next()) {
				Description description = new Description();
				description.setId(rs.getInt(1));
				description.setDescription(rs.getString(2));
				list.add(description);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, pstm, conn);
		}
		return list;
	}
	
	/**
	 * ����idɾ������
	 * 
	 * @param id
	 * @return
	 */
	public boolean deleteDescription(int id) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		int n = -1;// �洢�޸ĵļ�¼
		try {
			conn = DBUtil.getConnection();
			String select = "select * from kindergarten_environment_description where id=" + id + "";
			pstm = conn.prepareStatement(select);
			rs = pstm.executeQuery();
			boolean isExist = rs.next();
			if (isExist) {
				String sql = "delete from kindergarten_environment_description where id=" + id + "";
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