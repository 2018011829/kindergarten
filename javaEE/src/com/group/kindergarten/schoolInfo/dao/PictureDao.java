package com.group.kindergarten.schoolInfo.dao;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.group.kindergarten.schoolInfo.entity.Description;
import com.group.kindergarten.schoolInfo.entity.Picture;
import com.group.kindergarten.teacher.entity.Teacher;
import com.group.kindergarten.util.DBUtil;

public class PictureDao {
	/**
	 * ��������ͼƬ��Ϣ���з�ҳ
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
	 * ����ҳ������������ͼƬ��Ϣ
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
	 * ��ȡ����������ͼƬ��Ϣ
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
	
	/**
	 * ����idɾ������
	 * 
	 * @param id
	 * @return
	 */
	public boolean deletePicture(int id,String url) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		int n = -1;// �洢�޸ĵļ�¼
		try {
			conn = DBUtil.getConnection();
			String select = "select * from kindergarten_environment_picture where id=" + id + "";
			pstm = conn.prepareStatement(select);
			rs = pstm.executeQuery();
			boolean isExist = rs.next();
			if (isExist) {
	            File file = new File(url+rs.getString(2));
	            //�ж��ļ��Ƿ����
	            if (file.exists() == true){
	                System.out.println("ͼƬ���ڣ���ִ��ɾ������");
	                Boolean flag = false;
	                flag = file.delete();
	                if (flag){
	                    System.out.println("�ɹ�ɾ��ͼƬ"+file.getName());
	                }else {
	                    n=-1;
	                }
	            }else {
	            	n=-1;
	            }
				String sql = "delete from kindergarten_environment_picture where id=" + id + "";
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
	 * ����������Ϣģ�����Ҳ������ҳ
	 * @param name
	 * @return
	 */
	public int countByPageAndSearchInfo(String searchInfo) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		int count = 0;
		try {
			String sql = "";
			conn = DBUtil.getConnection();
			sql = "select count(id) from kindergarten_environment_picture where description_id like ?";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, "%" + searchInfo + "%");
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
	 * ����ҳ����������������ͼƬ
	 * @param pageNum
	 * @param pageSize
	 * @param name
	 * @return
	 */
	public List<Picture> findByPageAndSearchInfo(int pageNum, int pageSize, String searchInfo) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<Picture> list = new ArrayList<>();
		try {
			String sql = "";
			conn = DBUtil.getConnection();
			sql = "select * from kindergarten_environment_picture where description_id like ? limit ?, ?";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, "%" + searchInfo + "%");
			pstm.setInt(2, (pageNum - 1) * pageSize);
			pstm.setInt(3, pageSize);
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
	 * ��������ͼƬ
	 * 
	 * @param teacher
	 * @return
	 */
	public boolean addPicture(Picture picture) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		// ��ȡ��ʦ��Ϣ
		String pictureUrl = picture.getPicture();
		int descriptionId = picture.getDescriptionId();
		System.out.println(pictureUrl+descriptionId);
		int n = -1;// �洢����ļ�¼��
		try {
			conn = DBUtil.getConnection();
			String select = "select * from kindergarten_environment_picture where picture='" + pictureUrl + "' and description_id=" + descriptionId + "";
			pstm = conn.prepareStatement(select);
			rs = pstm.executeQuery();
			boolean isExist = rs.next();
			if (!isExist) {
				String sql = "";
				if (pictureUrl != null&&!pictureUrl.equals("")) {// ��Ƭ��Ϊ��
					if (String.valueOf(descriptionId)!=null&&!String.valueOf(descriptionId).equals("")) {
						sql = "insert into kindergarten_environment_picture(picture,description_id) values('" + pictureUrl + "'," +descriptionId + ")";
						// ����Ϣ���뵽���ݿ����
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
	
	/**
	 * ����id��ȡͼƬ��Ϣ
	 * @param id
	 * @return
	 */
	public Picture findPictureById(int id) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		Picture picture = new Picture();
		try {
			conn = DBUtil.getConnection();
			pstm = conn.prepareStatement("select * from kindergarten_environment_picture where id=" + id);
			rs = pstm.executeQuery();
			while(rs.next()) {
				picture.setId(rs.getInt(1));
				picture.setPicture(rs.getString(2));
				picture.setDescriptionId(rs.getInt(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, pstm, conn);
		}
		return picture;
	}
	
	/**
	 * �޸�ͼƬ��Ϣ
	 * @param teacher
	 * @return
	 */
	public boolean updatePicture(Picture picture,String pictureLast) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		// ��ȡ��ʦ��Ϣ
		int id = picture.getId();
		String pictureUrl = picture.getPicture();
		int descriptionId = picture.getDescriptionId();
		int n = -1;// �洢����ļ�¼��
		try {
			conn = DBUtil.getConnection();
			String select = "select * from kindergarten_environment_picture where id=" + id + "";
			pstm = conn.prepareStatement(select);
			rs = pstm.executeQuery();
			boolean isExist = rs.next();
			if (isExist) {
				String sql = null;
				if (pictureUrl != null && !pictureUrl.equals("")) {
					if (String.valueOf(descriptionId)!=null&&!String.valueOf(descriptionId).equals("")) {
						System.out.println(pictureUrl);
						sql = "update kindergarten_environment_picture set picture='" + pictureUrl + "', description_id=" + descriptionId + " where id=" + id + "";
						//ɾ��ԭͼƬ
						File file = new File(pictureLast);
			            //�ж��ļ��Ƿ����
			            if (file.exists() == true){
			                System.out.println("ͼƬ���ڣ���ִ��ɾ������");
			                Boolean flag = false;
			                flag = file.delete();
			                if (flag){
			                    System.out.println("�ɹ�ɾ��ͼƬ"+file.getName());
			                }else {
			                    n=-1;
			                }
			            }else {
			            	n=-1;
			            }
			            // �����º�Ľ�ʦ��Ϣ���뵽���ݿ����
						n = pstm.executeUpdate(sql);
					} else {
						n=-1;
					}
				}else{
					if (String.valueOf(descriptionId)!=null&&!String.valueOf(descriptionId).equals("")) {
						sql = "update kindergarten_environment_picture set description_id=" + descriptionId + " where id=" + id + "";
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
