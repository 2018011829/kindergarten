package com.group.kindergarten.applyinfo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.group.kindergarten.applyinfo.entity.ApplyInfo;
import com.group.kindergarten.util.DBUtil;


public class EnterDao {
	private static Connection connection;
	private static EnterDao enterDao;
	private PreparedStatement preparedStatement;
	
	/**
	 * 获取EnterDao实例
	 * @return EnterDao
	 */
	public static EnterDao getInstance() {
		if(null==enterDao) {
			enterDao=new EnterDao();
		}
		if(null==connection) {
			connection= DBUtil.getConnection();
		}
		return enterDao;
	}
	
	
	/**
	 * 向数据库添加一个孩子报名信息
	 * @param babyName 孩子姓名
	 * @param babyBirthday 孩子出生年月
	 * @param babySex 孩子性别
	 * @param babyIDnumber 孩子身份证号
	 * @param babyAddoAllergies 孩子过敏食物
	 * @param parentName1 家长姓名1
	 * @param relation1 家长和孩子的关系1
	 * @param phoneNumber1 家长联系方式1
	 * @param workSpace1 家长工作地址1
	 * @param homeAddress1 家长家庭住址1
	 * @param parentName2家长姓名2(选填)
	 * @param relation2 家长和孩子的关系2(选填)
	 * @param phoneNumber2家长联系方式2(选填)
	 * @param workSpace2 家长工作地址2(选填)
	 * @param homeAddress2家长家庭住址2(选填)
	 * @return 添加是否成功
	 */
	public boolean addChildApplyInformation(String userNumber,String babyName,String babyBirthday,String babySex,String babyIDnumber
			,String babyAddoAllergies,String parentName1,String relation1,String phoneNumber1,String workSpace1,
			String homeAddress1,String parentName2,String relation2,String phoneNumber2,String workSpace2,
			String homeAddress2) {
		boolean b=false;
		try {
			preparedStatement=connection.prepareStatement("insert into applyinfo(userNumber,babyName,babyBirthday,babySex,babyIDnumber"
					+ ",babyAddoAllergies,parentName1,relation1,phoneNumber1,workSpace1,homeAddress1,parentName2,relation2,phoneNumber2,workSpace2,homeAddress2) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			preparedStatement.setString(1, userNumber);
			preparedStatement.setString(2, babyName);
			preparedStatement.setString(3, babyBirthday);
			preparedStatement.setString(4, babySex);
			preparedStatement.setString(5, babyIDnumber);
			preparedStatement.setString(6, babyAddoAllergies);
			preparedStatement.setString(7, parentName1);
			preparedStatement.setString(8, relation1);
			preparedStatement.setString(9, phoneNumber1);
			preparedStatement.setString(10, workSpace1);
			preparedStatement.setString(11, homeAddress1);
			preparedStatement.setString(12, parentName2);
			preparedStatement.setString(13, relation2);
			preparedStatement.setString(14, phoneNumber2);
			preparedStatement.setString(15, workSpace2);
			preparedStatement.setString(16, homeAddress2);
			int rows=preparedStatement.executeUpdate();
			if(rows>0) {
				b=true;
				System.out.println("成功添加孩子报名信息");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return b;
	}
	
	/**
	 * 查询所有报名的孩子的所有信息
	 * @return list包含所有孩子信息的集合
	 */
	public List<ApplyInfo> searchAllChild(){
		List<ApplyInfo> list=null;
		String sql="select * from applyinfo";
		try {
			preparedStatement=connection.prepareStatement(sql);
			ResultSet rs=preparedStatement.executeQuery();
			if(rs!=null) {
				list=new ArrayList<>();
				while(rs.next()) {
					ApplyInfo applyInfo=new ApplyInfo(rs.getInt("id"),rs.getString("userNumber"),rs.getString("babyName"),
							rs.getString("babyBirthday"),rs.getString("babySex"),rs.getString("babyIDnumber"),
							rs.getString("babyAddoAllergies"),rs.getString("parentName1"),rs.getString("relation1"),
							rs.getString("parentIDnumber1"),rs.getString("phoneNumber1"),rs.getString("workSpace1"),
							rs.getString("homeAddress1"),rs.getString("parentName2"),rs.getString("relation2"),
							rs.getString("parentIDnumber2"),rs.getString("phoneNumber2"),rs.getString("workSpace2"),
							rs.getString("homeAddress2"));
					list.add(applyInfo);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
}
