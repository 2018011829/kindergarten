package com.group.kindergarten.schoolInfo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.group.kindergarten.schoolInfo.entity.Description;
import com.group.kindergarten.schoolInfo.entity.Phone;
import com.group.kindergarten.util.DBUtil;

public class PhoneDao {
	/**
	 * 将电话信息进行分页
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
			sql = "select count(*) from kindergarten_phone";
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
	 * 根据页数查找电话信息
	 * 
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public List<Phone> findByPage(int pageNum, int pageSize) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<Phone> list = new ArrayList<>();
		try {
			String sql = "";
			conn = DBUtil.getConnection();
			sql = "select * from kindergarten_phone limit ?, ?";
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, (pageNum - 1) * pageSize);
			pstm.setInt(2, pageSize);
			rs = pstm.executeQuery();
			while (rs.next()) {
				Phone phone = new Phone();
				phone.setId(rs.getInt(1));
				phone.setPhone(rs.getString(2));
				list.add(phone);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, pstm, conn);
		}
		return list;
	}
	
	/**
	 * 获取所有电话信息
	 * @return
	 */
	public List<Phone> findPhone(){
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<Phone> list = new ArrayList<>();
		try {
			conn = DBUtil.getConnection();
			pstm = conn.prepareStatement("select * from kindergarten_phone");
			rs = pstm.executeQuery();
			while(rs.next()) {
				Phone phone = new Phone();
				phone.setId(rs.getInt(1));
				phone.setPhone(rs.getString(2));
				list.add(phone);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, pstm, conn);
		}
		return list;
	}

}
