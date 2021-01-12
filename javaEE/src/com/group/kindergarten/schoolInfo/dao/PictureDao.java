package com.group.kindergarten.schoolInfo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.group.kindergarten.schoolInfo.entity.Description;
import com.group.kindergarten.schoolInfo.entity.Picture;
import com.group.kindergarten.util.DBUtil;

public class PictureDao {
	/**
	 * 将描述的图片信息进行分页
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
			sql = "select count(*) from kindergarten_environment_picture";
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
	 * 根据页数查找描述的图片信息
	 * 
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public List<Picture> findByPage(int pageNum, int pageSize) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<Picture> list = new ArrayList<>();
		try {
			String sql = "";
			conn = DBUtil.getConnection();
			sql = "select * from kindergarten_environment_picture limit ?, ?";
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, (pageNum - 1) * pageSize);
			pstm.setInt(2, pageSize);
			rs = pstm.executeQuery();
			while (rs.next()) {
				Picture picture = new Picture();
				picture.setId(rs.getInt(1));
				picture.setPicture(rs.getString(2));
				picture.setDescriptionId(rs.getInt(3));
				list.add(picture);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, pstm, conn);
		}
		return list;
	}
	
	/**
	 * 获取所有描述的图片信息
	 * @return
	 */
	public List<Picture> findPicture(){
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<Picture> list = new ArrayList<>();
		try {
			conn = DBUtil.getConnection();
			pstm = conn.prepareStatement("select * from kindergarten_environment_picture");
			rs = pstm.executeQuery();
			while(rs.next()) {
				Picture picture = new Picture();
				picture.setId(rs.getInt(1));
				picture.setPicture(rs.getString(2));
				list.add(picture);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, pstm, conn);
		}
		return list;
	}

}
