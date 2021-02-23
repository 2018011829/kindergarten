package com.group.kindergarten.contact.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.group.kindergarten.contact.entity.LCChatKitUser;
import com.group.kindergarten.util.DBUtil;

public class RemarkDaoImpl {
	public static Connection connection;
	public static RemarkDaoImpl remarkDaoImpl;
	public static PreparedStatement preparedStatement;

	/**
	 * �õ�RemarkDaoImplʾ��
	 * 
	 * @return RemarkDaoImpl
	 */
	public static RemarkDaoImpl getInstance() {
		if (null == remarkDaoImpl) {
			remarkDaoImpl = new RemarkDaoImpl();
		}
		if (null == connection) {
			connection = DBUtil.getConnection();
		}
		return remarkDaoImpl;
	}

	/**
	 * ��ӱ�ע
	 * 
	 * @param fromUser  �����
	 * @param toUser    �������
	 * @param remarkStr ��ע
	 * @return boolean
	 */
	public boolean addRemark(int fromUser, int toUser, String remarkStr) {
		boolean b = false;

		try {
			preparedStatement = connection
					.prepareStatement("insert into contact_remark(from_user,to_user,remark_str) values (?,?,?)");
			preparedStatement.setInt(1, fromUser);
			preparedStatement.setInt(2, toUser);
			preparedStatement.setString(3, remarkStr);
			int rows = preparedStatement.executeUpdate();
			if (rows > 0) {
				b = true;
				System.out.println("��ӱ�ע�ɹ�");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return b;
	}
	
	
	/**
	 * ��ȡ��ϵ���б�
	 * @param phone
	 * @return ��ϵ�ˣ�����chatkit��
	 */
	public List<LCChatKitUser> getContactByPhone(String phone) {
		String sqlForid = "select id from user where phone='"+ phone+"'";
		int id=-1;
		String sql = "select (id,nickname,avatar) from user where id is in(select to_user from contact_remark where id=?)";

		List<LCChatKitUser> lcChatKitUsers = new ArrayList<LCChatKitUser>();
		try {
			//��ѯ��phone��Ӧ��id
			preparedStatement = connection.prepareStatement(sqlForid);
			ResultSet resultSetForId = preparedStatement.executeQuery();
			if (resultSetForId.next()) {
				System.out.println(phone+"��Ӧ��id��"+resultSetForId.getInt(1));
				id = resultSetForId.getInt(1);
			}
			preparedStatement = connection
					.prepareStatement(sql);
		
			preparedStatement.setInt(1,id);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				LCChatKitUser lcChatKitUser = new LCChatKitUser();
				lcChatKitUser.setUserId(resultSet.getString(1));
				lcChatKitUser.setName(resultSet.getString(2));
				lcChatKitUser.setAvatarUrl(resultSet.getString(3));
				lcChatKitUsers.add(lcChatKitUser);
			}
			System.out.println(lcChatKitUsers.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lcChatKitUsers;
	}

}
