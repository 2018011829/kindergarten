package com.group.kindergarten.schoolInfo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.group.kindergarten.schoolInfo.entity.BasicInfo;
import com.group.kindergarten.schoolInfo.entity.Picture;
import com.group.kindergarten.util.DBUtil;

public class BasicInfoDao {
	/**
	 * 将基本信息进行分页
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
			sql = "select count(*) from kindergarten_info";
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
	 * 根据页数查找基本信息
	 * 
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public List<BasicInfo> findByPage(int pageNum, int pageSize) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<BasicInfo> list = new ArrayList<>();
		try {
			String sql = "";
			conn = DBUtil.getConnection();
			sql = "select * from kindergarten_info limit ?, ?";
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, (pageNum - 1) * pageSize);
			pstm.setInt(2, pageSize);
			rs = pstm.executeQuery();
			while (rs.next()) {
				BasicInfo basicInfo = new BasicInfo();
				basicInfo.setId(rs.getInt(1));
				basicInfo.setIntroduceFile(rs.getString(2));
				basicInfo.setAddress(rs.getString(3));
				list.add(basicInfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, pstm, conn);
		}
		return list;
	}
	
	/**
	 * 获取所有基本信息
	 * @return
	 */
	public List<BasicInfo> findBasicInfo(){
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<BasicInfo> list = new ArrayList<>();
		try {
			conn = DBUtil.getConnection();
			pstm = conn.prepareStatement("select * from kindergarten_info");
			rs = pstm.executeQuery();
			while(rs.next()) {
				BasicInfo basicInfo = new BasicInfo();
				basicInfo.setId(rs.getInt(1));
				basicInfo.setIntroduceFile(rs.getString(2));
				basicInfo.setAddress(rs.getString(3));
				list.add(basicInfo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, pstm, conn);
		}
		return list;
	}

}
