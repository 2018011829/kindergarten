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
	
	/**
	 * 根据id获取电话
	 * @param id
	 * @return
	 */
	public Phone findPhoneById(int id) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		Phone phone = new Phone();
		try {
			conn = DBUtil.getConnection();
			pstm = conn.prepareStatement("select * from kindergarten_phone where id=" + id);
			rs = pstm.executeQuery();
			while(rs.next()) {
				phone.setId(rs.getInt(1));
				phone.setPhone(rs.getString(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, pstm, conn);
		}
		return phone;
	}

	/**
	 * 根据id修改描述
	 * 
	 * @param id
	 * @return
	 */
	public boolean updatePhone(int id,String phone) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		int n = -1;// 存储修改的记录
		try {
			conn = DBUtil.getConnection();
			String select = "select * from kindergarten_phone where id=" + id + "";
			pstm = conn.prepareStatement(select);
			rs = pstm.executeQuery();
			boolean isExist = rs.next();
			if (isExist) {
				String sql = "update kindergarten_phone set phone ='"+ phone +"' where id=" + id + "";
				n = pstm.executeUpdate(sql);
			}else {
				n=-1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, pstm, conn);
		}
		return n > 0 ? true : false;
	}
}
