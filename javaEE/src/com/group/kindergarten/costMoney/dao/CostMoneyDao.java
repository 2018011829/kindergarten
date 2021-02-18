package com.group.kindergarten.costMoney.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
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
				int preDayNum=rs.getInt("leave_day");
				// ��ȥ���ڵ��������
				int nowDayNum=preDayNum+dayNum;
				// �޸����ݿ��е�����
				preparedStatement=connection.prepareStatement("update child_attendence set leave_day=? where child_id=? and parentPhone=?");
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
	
	/**
	 * ÿ��1�ս����ϸ��µ����������0
	 * @param name
	 * @param parentPhone
	 * @return
	 */
	public boolean clearLeaveDay(String name,String parentPhone) {
		boolean b=false;
		int id=returnChildId(name, parentPhone);
		try {
			preparedStatement=connection.prepareStatement("update child_attendence set leave_day=0 where child_id=? and parentPhone=?");
			preparedStatement.setInt(1, id);
			preparedStatement.setString(2, parentPhone);
			b=preparedStatement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return b;
	}
	
	/**
	 * ��ȡĳ�������ϸ��µ��������
	 * @param name
	 * @param parentPhone
	 * @return
	 */
	public int getPreMonthLeave(String name,String parentPhone) {
		int day=0;
		int id=returnChildId(name, parentPhone);
		try {
			preparedStatement=connection.prepareStatement("select * from child_attendence where child_id=? and parentPhone=?");
			preparedStatement.setInt(1, id);
			preparedStatement.setString(2, parentPhone);
			ResultSet rs=preparedStatement.executeQuery();
			if(rs.next()) {
				// �õ������ϸ����������
				day=rs.getInt("leave_day");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return day;
	}
	
	/**
	 * ���㺢���ϸ��µĳ�������
	 * @param name
	 * @param parentPhone
	 * @return
	 */
	public int childAttendLastMonth(String name,String parentPhone) {
		int day=0;
		int preMonthDay=0;
		int month=0;
		//��ȡ��ǰʱ��
		Calendar c = Calendar.getInstance();
	    //��ȡ�ϸ����·� ��0��ʼ��0-11
		if(c.get(Calendar.MONTH)==0) {
			month=12;
		}else {
			month=c.get(Calendar.MONTH);
		}
	    //�����ݿ��л�ȡ�ϸ���Ӧ�ϵ�����
	    try {
			preparedStatement=connection.prepareStatement("select * from school_semester where month=?");
			preparedStatement.setInt(1, month);
			ResultSet rs=preparedStatement.executeQuery();
			if(rs.next()) {
				preMonthDay=rs.getInt("day_num");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    //�����ϸ��³������������ϸ���Ӧ����������ȥ���������
	    day=preMonthDay-getPreMonthLeave(name, parentPhone);
	    //�޸����ݿ����Ϣ����������ϸ��³�������д�����ݿ⣬�ٽ����������0��
	    int id=returnChildId(name, parentPhone);
	    boolean b=false;
	    try {
			preparedStatement=connection.prepareStatement("update child_attendence set last_attendence_day=? where child_id=? and parentPhone=?");
			preparedStatement.setInt(1, day);
			preparedStatement.setInt(2, id);
			preparedStatement.setString(3, parentPhone);
			b=preparedStatement.execute();
	    } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    if(b) {
	    	//�����������
	    	clearLeaveDay(name, parentPhone);
	    }
		
		return day;
	}
	
	/**
	 * ��ȡ�����ϸ��µĳ�������
	 * @param name
	 * @param parentPhone
	 * @return
	 */
	public int getChildLastAttendenceDay(String name,String parentPhone) {
		int day=0;
		int id=returnChildId(name, parentPhone);
		try {
			preparedStatement=connection.prepareStatement("select * from child_attendence where child_id=? and parentPhone=?");
			preparedStatement.setInt(1, id);
			preparedStatement.setString(2, parentPhone);
			ResultSet rs=preparedStatement.executeQuery();
			if(rs.next()) {
				day=rs.getInt("last_attendence_day");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return day;
	}
	
	/**
	 * ���㺢�ӱ���Ӧ�ý��ķ���
	 * @param name
	 * @param parentPhone
	 * @return
	 */
	public int childNeedMoney(String name,String parentPhone) {
		int day=0;
		int preMonth=0;
		int nowMonth=0;
		//��ȡ��ǰʱ��
		Calendar c = Calendar.getInstance();
	    //��ȡ�ϸ����·� ��0��ʼ��0-11
		//��ȡ�ϸ����·�
		if(c.get(Calendar.MONTH)==0) {
			preMonth=12;
		}else {
			preMonth=c.get(Calendar.MONTH);
		}
		//��ȡ�����·�
		if(preMonth==12) {
			nowMonth=1;
		}else {
			nowMonth=preMonth+1;
		}
		//��ȡ�ϸ��µĳ�������
		day=getChildLastAttendenceDay(name, parentPhone);
		//�����ϸ��µ����
		
		//���㱾��Ҫ����Ǯ
		
		
		return day;
	}
	
	/**
	 * �����ϸ��µ����
	 * @param preMonth
	 * @return
	 */
	public int caculateLastMoney(int preMonth) {
		int money=0;
		//��ȡ�ϸ���Ҫ��ѧ������
		//���㱣���ѺͲͷѣ����ж������Ķ��٣�0, 1-10 ��11�����ϣ�
		//����ʵ��Ӧ֧���ķ��ã��ϸ��³����������㣩
		
		return money;
	}
	
	/**
	 * ���㱾��Ҫ����Ǯ
	 * @param nowMonth
	 * @return
	 */
	public int caculateNowMoney(int nowMonth) {
		int money=0;
		//��ȡ����Ҫ��ѧ������
		//��ȡ�ϸ��µ����
		//���㱾��ʵ��Ӧ���ķ��ã����·��ü�ȥ�ϸ��µ���
		
		return money;
	}
}
