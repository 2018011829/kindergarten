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
	 * 获取CostMoneyDao实例
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
	 * 查找数据库中是否进行了上课时间的规定
	 * @return false为空：未进行时间的规定
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
	 * 从数据库中获取设置的学期信息
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
	 * 判断月份在数据库中是否存在，存在则进行修改，不存在进行保存
	 * @param monthNum
	 * @return 
	 */
	public boolean justAndPreserve(int monthNum,int dayNum) {
		boolean b=false;
		try {
			preparedStatement=connection.prepareStatement("select * from school_semester where month=?");
			preparedStatement.setInt(1, monthNum);
			ResultSet rs=preparedStatement.executeQuery();
			if(rs!=null) {// 进行修改
				preparedStatement=connection.prepareStatement("update school_semester set day_num=? where month=?");
				preparedStatement.setInt(1, dayNum);
				preparedStatement.setInt(2, monthNum);
				b=preparedStatement.execute();
			}else {// 进行插入
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
	 * 查询孩子姓名是否存在
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
	 * 查找孩子的id
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
	 * 在数据库中更新孩子的到课天数
	 * @param id
	 * @param parentPhone
	 * @param dayNum
	 * @return
	 */
	public boolean updateLeaveInfo(int id,String parentPhone,int dayNum) {
		boolean b=false;
		try {
			preparedStatement=connection.prepareStatement("select * from child_attendence where child_id=? and phone=?");
			preparedStatement.setInt(1, id);
			preparedStatement.setString(2, parentPhone);
			ResultSet rs=preparedStatement.executeQuery();
			if(rs.next()) {
				// 得到孩子原先的到课天数
				int preDayNum=rs.getInt("leave_day");
				// 减去现在的请假天数
				int nowDayNum=preDayNum+dayNum;
				// 修改数据库中的数据
				preparedStatement=connection.prepareStatement("update child_attendence set leave_day=? where child_id=? and phone=?");
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
	 * 每到1日进行上个月的请假天数清0
	 * @param name
	 * @param parentPhone
	 * @return
	 */
	public boolean clearLeaveDay(String name,String parentPhone) {
		boolean b=false;
		int id=returnChildId(name, parentPhone);
		try {
			preparedStatement=connection.prepareStatement("update child_attendence set leave_day=0 where child_id=? and phone=?");
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
	 * 获取某个孩子上个月的请假天数
	 * @param name
	 * @param parentPhone
	 * @return
	 */
	public int getPreMonthLeave(String name,String parentPhone) {
		int day=0;
		int id=returnChildId(name, parentPhone);
		try {
			preparedStatement=connection.prepareStatement("select * from child_attendence where child_id=? and phone=?");
			preparedStatement.setInt(1, id);
			preparedStatement.setString(2, parentPhone);
			ResultSet rs=preparedStatement.executeQuery();
			if(rs.next()) {
				// 得到孩子上个月请假天数
				day=rs.getInt("leave_day");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return day;
	}
	
	/**
	 * 计算孩子上个月的出勤天数
	 * @param name
	 * @param parentPhone
	 * @return
	 */
	public int childAttendLastMonth(String name,String parentPhone) {
		int day=0;
		int preMonthDay=0;
		int month=0;
		//获取当前时间
		Calendar c = Calendar.getInstance();
	    //获取上个月月份 从0开始，0-11
		if(c.get(Calendar.MONTH)==0) {
			month=12;
		}else {
			month=c.get(Calendar.MONTH);
		}
	    //从数据库中获取上个月应上的天数
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
	    //计算上个月出勤天数（用上个月应到的天数减去请假天数）
	    day=preMonthDay-getPreMonthLeave(name, parentPhone);
	    //修改数据库的信息（将计算的上个月出勤天数写入数据库，再将请假天数清0）
	    int id=returnChildId(name, parentPhone);
	    boolean b=false;
	    try {
			preparedStatement=connection.prepareStatement("update child_attendence set last_attendence_day=? where child_id=? and phone=?");
			preparedStatement.setInt(1, day);
			preparedStatement.setInt(2, id);
			preparedStatement.setString(3, parentPhone);
			b=preparedStatement.execute();
	    } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    if(b) {
	    	//请假天数清零
	    	clearLeaveDay(name, parentPhone);
	    }
		
		return day;
	}
	
	/**
	 * 获取孩子上个月的出勤天数
	 * @param name
	 * @param parentPhone
	 * @return
	 */
	public int getChildLastAttendenceDay(String name,String parentPhone) {
		int day=0;
		int id=returnChildId(name, parentPhone);
		try {
			preparedStatement=connection.prepareStatement("select * from child_attendence where child_id=? and phone=?");
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
	 * 根据月份获取对应月份的天数 
	 * @param month
	 * @return
	 */
	public int getOneMonthAboutDayNum(int month) {
		int day=0;
		try {
			preparedStatement=connection.prepareStatement("select * from school_semester where month=?");
			preparedStatement.setInt(1, month);
			ResultSet rs=preparedStatement.executeQuery();
			if(rs.next()) {
				day=rs.getInt("day_num");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return day;
	}
	
	/**
	 * 计算请事假时间总天数，并请求数据库更新请假天数，以及保存请假日期
	 * @param id
	 * @param phone
	 * @param dayStartNum
	 * @param dayEndNum
	 * @return
	 */
	public boolean updateLeaveInfo(int id, String phone, int dayStartNum, int dayEndNum) {
		boolean b=false;
		
		return b;
	}
}
