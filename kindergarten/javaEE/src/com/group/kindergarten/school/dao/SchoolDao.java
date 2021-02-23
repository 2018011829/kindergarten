package com.group.kindergarten.school.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.group.kindergarten.school.entity.SchoolOutside;
import com.group.kindergarten.school.entity.ThreePicture;
import com.group.kindergarten.school.entity.TwoPicture;
import com.group.kindergarten.util.DBUtil;

public class SchoolDao {

	public static Connection connection;
	public static PreparedStatement preparedStatement;
	public static SchoolDao schoolDao;
	
	/**
	 * 获取ChildrenDao实例
	 * @return ChildrentDao
	 */
	public static SchoolDao getInstance() {
		if(null==schoolDao) {
			schoolDao=new SchoolDao();
		}
		if(null==connection) {
			connection= DBUtil.getConnection();
		}
		return schoolDao;
	}
	
	/**
	 * 查找保存幼儿园简介信息的文件名称
	 * @return
	 */
	public String searchIntroduceTextFileName() {
		String fileName="";
		String sql="select * from kindergarten_info";
		try {
			preparedStatement=connection.prepareStatement(sql);
			ResultSet rs=preparedStatement.executeQuery();
			if(rs.next()) {
				fileName=rs.getString("introduce_file_name");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return fileName;
	}
	
	/**
	 * 获取学校的外部环境的相关信息
	 * @return
	 */
	public SchoolOutside getSchoolOutside() {
		SchoolOutside schoolOutside=null;
		String sql1="select * from kindergarten_environment_description where id=1";
		try {
			preparedStatement=connection.prepareStatement(sql1);
			ResultSet rs1=preparedStatement.executeQuery();
			if(rs1.next()) {
				schoolOutside=new SchoolOutside();
				schoolOutside.setDescription(rs1.getString("description"));
				String sql2="select * from kindergarten_environment_picture where description_id=1";
				preparedStatement=connection.prepareStatement(sql2);
				ResultSet rs2=preparedStatement.executeQuery();
				if(rs2!=null) {
					List<String> list=new ArrayList<>();
					while(rs2.next()) {
						list.add(rs2.getString("picture"));
					}
					schoolOutside.setList(list);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return schoolOutside;
	}
	
	/**
	 * 获取学校的GridView环境的相关信息
	 * @return
	 */
	public SchoolOutside getSchoolGridView() {
		SchoolOutside schoolOutside=null;
		String sql1="select * from kindergarten_environment_description where id=5";
		try {
			preparedStatement=connection.prepareStatement(sql1);
			ResultSet rs1=preparedStatement.executeQuery();
			if(rs1.next()) {
				schoolOutside=new SchoolOutside();
				schoolOutside.setDescription(rs1.getString("description"));
				String sql2="select * from kindergarten_environment_picture where description_id=5";
				preparedStatement=connection.prepareStatement(sql2);
				ResultSet rs2=preparedStatement.executeQuery();
				if(rs2!=null) {
					List<String> list=new ArrayList<>();
					while(rs2.next()) {
						list.add(rs2.getString("picture"));
					}
					schoolOutside.setList(list);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return schoolOutside;
	}
	
	/**
	 * 获取学校的区角环境的相关信息
	 * @return
	 */
	public SchoolOutside getSchoolAngleBanner() {
		SchoolOutside schoolOutside=null;
		String sql1="select * from kindergarten_environment_description where id=6";
		try {
			preparedStatement=connection.prepareStatement(sql1);
			ResultSet rs1=preparedStatement.executeQuery();
			if(rs1.next()) {
				schoolOutside=new SchoolOutside();
				schoolOutside.setDescription(rs1.getString("description"));
				String sql2="select * from kindergarten_environment_picture where description_id=6";
				preparedStatement=connection.prepareStatement(sql2);
				ResultSet rs2=preparedStatement.executeQuery();
				if(rs2!=null) {
					List<String> list=new ArrayList<>();
					while(rs2.next()) {
						list.add(rs2.getString("picture"));
					}
					schoolOutside.setList(list);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return schoolOutside;
	}
	
	/**
	 * 获取学校的楼道环境的相关信息
	 * @return
	 */
	public TwoPicture getSchoolPassageway() {
		TwoPicture twoPicture=null;
		String sql1="select * from kindergarten_environment_description where id=2";
		try {
			preparedStatement=connection.prepareStatement(sql1);
			ResultSet rs1=preparedStatement.executeQuery();
			if(rs1.next()) {
				twoPicture=new TwoPicture();
				twoPicture.setDescription(rs1.getString("description"));
				String sql2="select * from kindergarten_environment_picture where description_id=2";
				preparedStatement=connection.prepareStatement(sql2);
				ResultSet rs2=preparedStatement.executeQuery();
				if(rs2!=null) {
					int index=1;
					while(rs2.next()) {
						if(index==1) {
							twoPicture.setPicture1(rs2.getString("picture"));
						}
						if(index==2) {
							twoPicture.setPicture2(rs2.getString("picture"));
						}
						index++;
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return twoPicture;
	}
	
	/**
	 * 获取学校的内部环境的相关信息
	 * @return
	 */
	public TwoPicture getSchoolInside() {
		TwoPicture twoPicture=null;
		String sql1="select * from kindergarten_environment_description where id=3";
		try {
			preparedStatement=connection.prepareStatement(sql1);
			ResultSet rs1=preparedStatement.executeQuery();
			if(rs1.next()) {
				twoPicture=new TwoPicture();
				twoPicture.setDescription(rs1.getString("description"));
				String sql2="select * from kindergarten_environment_picture where description_id=3";
				preparedStatement=connection.prepareStatement(sql2);
				ResultSet rs2=preparedStatement.executeQuery();
				if(rs2!=null) {
					int index=1;
					while(rs2.next()) {
						if(index==1) {
							twoPicture.setPicture1(rs2.getString("picture"));
						}
						if(index==2) {
							twoPicture.setPicture2(rs2.getString("picture"));
						}
						index++;
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return twoPicture;
	}
	
	/**
	 * 获取学校的游乐设施环境的相关信息
	 * @return
	 */
	public ThreePicture getSchoolPlay() {
		ThreePicture threePicture=null;
		String sql1="select * from kindergarten_environment_description where id=4";
		try {
			preparedStatement=connection.prepareStatement(sql1);
			ResultSet rs1=preparedStatement.executeQuery();
			if(rs1.next()) {
				threePicture=new ThreePicture();
				threePicture.setDescription(rs1.getString("description"));
				String sql2="select * from kindergarten_environment_picture where description_id=4";
				preparedStatement=connection.prepareStatement(sql2);
				ResultSet rs2=preparedStatement.executeQuery();
				if(rs2!=null) {
					int index=1;
					while(rs2.next()) {
						if(index==1) {
							threePicture.setPicture1(rs2.getString("picture"));
						}
						if(index==2) {
							threePicture.setPicture2(rs2.getString("picture"));
						}
						if(index==3) {
							threePicture.setPicture3(rs2.getString("picture"));
						}
						index++;
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return threePicture;
	}
}
