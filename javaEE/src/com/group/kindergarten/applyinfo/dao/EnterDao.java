package com.group.kindergarten.applyinfo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.group.kindergarten.util.DBUtil;


public class EnterDao {
	private static Connection connection;
	private static EnterDao enterDao;
	private PreparedStatement preparedStatement;
	
	/**
	 * ��ȡEnterDaoʵ��
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
	 * �����ݿ����һ�����ӱ�����Ϣ
	 * @param babyName ��������
	 * @param babyBirthday ���ӳ�������
	 * @param babySex �����Ա�
	 * @param babyIDnumber �������֤��
	 * @param babyAddoAllergies ���ӹ���ʳ��
	 * @param parentName1 �ҳ�����1
	 * @param relation1 �ҳ��ͺ��ӵĹ�ϵ1
	 * @param phoneNumber1 �ҳ���ϵ��ʽ1
	 * @param workSpace1 �ҳ�������ַ1
	 * @param homeAddress1 �ҳ���ͥסַ1
	 * @param parentName2�ҳ�����2(ѡ��)
	 * @param relation2 �ҳ��ͺ��ӵĹ�ϵ2(ѡ��)
	 * @param phoneNumber2�ҳ���ϵ��ʽ2(ѡ��)
	 * @param workSpace2 �ҳ�������ַ2(ѡ��)
	 * @param homeAddress2�ҳ���ͥסַ2(ѡ��)
	 * @return ����Ƿ�ɹ�
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
				System.out.println("�ɹ���Ӻ��ӱ�����Ϣ");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return b;
	}
}
