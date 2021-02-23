package com.group.kindergarten.schoolInfo.dao;

import java.io.File;
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
	 * ��������Ϣ���з�ҳ
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
	 * ����ҳ�����һ�����Ϣ
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
	 * ��ȡ���л�����Ϣ
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
	/**
	 * ����id��ȡ������Ϣ
	 * @param id
	 * @return
	 */
	public BasicInfo findBasicInfoById(int id) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		BasicInfo basicInfo = new BasicInfo();
		try {
			conn = DBUtil.getConnection();
			pstm = conn.prepareStatement("select * from kindergarten_info where id=" + id);
			rs = pstm.executeQuery();
			while(rs.next()) {
				basicInfo.setId(rs.getInt(1));
				basicInfo.setIntroduceFile(rs.getString(2));
				basicInfo.setAddress(rs.getString(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, pstm, conn);
		}
		return basicInfo;
	}

	/**
	 * �޸Ļ�����Ϣ
	 * @param teacher
	 * @return
	 */
	public boolean updateBasicInfo(BasicInfo basicInfo) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		// ��ȡ��ʦ��Ϣ
		int id = basicInfo.getId();
		String introduceFile = basicInfo.getIntroduceFile();
		String address = basicInfo.getAddress();
		int n = -1;// �洢����ļ�¼��
		try {
			conn = DBUtil.getConnection();
			String select = "select * from kindergarten_info where id=" + id + "";
			pstm = conn.prepareStatement(select);
			rs = pstm.executeQuery();
			boolean isExist = rs.next();
			if (isExist) {
				String sql = null;
				if (introduceFile != null && !introduceFile.equals("")) {
					if (address!=null&&!address.equals("")) {
						sql = "update kindergarten_info set introduce_file_name='" + introduceFile + "',address='" + address + "' where id=" + id + "";
			            // �����º�Ľ�ʦ��Ϣ���뵽���ݿ����
						n = pstm.executeUpdate(sql);
					} else {
						n=-1;
					}
				}else{
					if (address!=null&&!address.equals("")) {
						sql = "update kindergarten_info set address='" + address + "' where id=" + id + "";
						// �����º�Ľ�ʦ��Ϣ���뵽���ݿ����
						n = pstm.executeUpdate(sql);
					} else {
						n=-1;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, pstm, conn);
		}
		return n > 0 ? true : false;
	}
}
