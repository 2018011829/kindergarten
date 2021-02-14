package com.group.kindergarten.costMoney.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.group.kindergarten.costMoney.entity.SchoolSemester;
import com.group.kindergarten.util.DBUtil;

public class CostMoneyDao {
	
	public static Connection connection;
	public static CostMoneyDao costMoneyDao;
	public static PreparedStatement preparedStatement;
	
	/**
	 * ��ȡCostMoneyDaoʵ��
	 * @return CostMoneyDao
	 */
	public static CostMoneyDao getInstance() {
		if(null==costMoneyDao) {
			costMoneyDao=new CostMoneyDao();
		}
		if(null==connection) {
			connection= DBUtil.getConnection();
		}
		return costMoneyDao;
	}
	
	/**
	 * �������ݿ����Ƿ�������Ͽ�ʱ��Ĺ涨
	 * @return falseΪ�գ�δ����ʱ��Ĺ涨
	 */
	public boolean isClassTimeExist() {
		boolean b=false;
		try {
			preparedStatement=connection.prepareStatement("select * from school_semester");
			ResultSet rs=preparedStatement.executeQuery();
			if(rs.next()) {
				b=true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return b;
	}
	
	/**
	 * �����ݿ��л�ȡ���õ�ѧ����Ϣ
	 * @return
	 */
	public List<SchoolSemester> searchSemesterInfo() {
		List<SchoolSemester> list=null;
		try {
			preparedStatement=connection.prepareStatement("select * from school_semester");
			ResultSet rs=preparedStatement.executeQuery();
			if(rs!=null) {
				list=new ArrayList<SchoolSemester>();
				while(rs.next()) {
					SchoolSemester s=new SchoolSemester();
					s.setId(rs.getInt("id"));
					s.setMonth(rs.getInt("month"));
					s.setDayNum(rs.getInt("day_num"));
					list.add(s);
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	/**
	 * �ж��·������ݿ����Ƿ���ڣ�����������޸ģ������ڽ��б���
	 * @param monthNum
	 * @return 
	 */
	public boolean justAndPreserve(int monthNum,int dayNum) {
		boolean b=false;
		try {
			preparedStatement=connection.prepareStatement("select * from school_semester where month=?");
			preparedStatement.setInt(1, monthNum);
			ResultSet rs=preparedStatement.executeQuery();
			if(rs!=null) {// �����޸�
				preparedStatement=connection.prepareStatement("update school_semester set day_num=? where month=?");
				preparedStatement.setInt(1, dayNum);
				preparedStatement.setInt(2, monthNum);
				b=preparedStatement.execute();
			}else {// ���в���
				preparedStatement=connection.prepareStatement("insert into school_semester(month,day_num) values(?,?)");
				preparedStatement.setInt(2, dayNum);
				preparedStatement.setInt(1, monthNum);
				b=preparedStatement.execute();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return b;
	}
	
	/**
	 * ��ѯ���������Ƿ����
	 * @param stuName
	 * @return
	 */
	public boolean isExistChildName(String stuName) {
		boolean b=false;
		try {
			preparedStatement=connection.prepareStatement("select * from child where name=?");
			preparedStatement.setString(1, stuName);
			ResultSet rs=preparedStatement.executeQuery();
			if(rs.next()) {
				b=true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return b;
	}
	
	/**
	 * ���Һ��ӵ�id
	 * @param name
	 * @param parentPhone
	 * @return
	 */
	public int returnChildId(String name,String parentPhone) {
		int id=0;
		try {
			preparedStatement=connection.prepareStatement("select * from child where name=? and parentPhone=?");
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, parentPhone);
			ResultSet rs=preparedStatement.executeQuery();
			if(rs.next()) {
				id=rs.getInt("id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return id;
	}
	
	/**
	 * �����ݿ��и��º��ӵĵ�������
	 * @param id
	 * @param parentPhone
	 * @param dayNum
	 * @return
	 */
	public boolean updateLeaveInfo(int id,String parentPhone,int dayNum) {
		boolean b=false;
		try {
			preparedStatement=connection.prepareStatement("select * from child_attendence where child_id=? and parentPhone=?");
			preparedStatement.setInt(1, id);
			preparedStatement.setString(2, parentPhone);
			ResultSet rs=preparedStatement.executeQuery();
			if(rs.next()) {
				// �õ�����ԭ�ȵĵ�������
				int preDayNum=rs.getInt("next_attendence_day");
				// ��ȥ���ڵ��������
				int nowDayNum=preDayNum-dayNum;
				// �޸����ݿ��е�����
				preparedStatement=connection.prepareStatement("update child_attendence set next_attendence_day=? where child_id=? and parentPhone=?");
				preparedStatement.setInt(1, nowDayNum);
				preparedStatement.setInt(2, id);
				preparedStatement.setString(3, parentPhone);
				b=preparedStatement.execute();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return b;
	}
}
