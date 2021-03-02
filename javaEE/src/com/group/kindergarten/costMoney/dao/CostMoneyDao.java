package com.group.kindergarten.costMoney.dao;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.group.kindergarten.costMoney.entity.Charge;
import com.group.kindergarten.costMoney.entity.MoneyPicture;
import com.group.kindergarten.costMoney.entity.SchoolSemester;
import com.group.kindergarten.costMoney.entity.ScreenshotInfo;
import com.group.kindergarten.schoolInfo.entity.Picture;
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
	 * 计算请假的天数并保存到数据库 ，并请求数据库更新请假天数
	 * @param id
	 * @param phone
	 * @param dayStartNum
	 * @param dayEndNum
	 * @return
	 */
	public boolean updateLeaveInfo(String name, String phone, int dayStartNum, int dayEndNum) {
		boolean b=false;
		//获取孩子的id
		int id=returnChildId(name, phone);
		//计算请假天数
		int leaveNewDay=dayEndNum-dayStartNum;
		//获取数据库中的请假天数
		int leaveLastDay=getPreMonthLeave(name, phone);
		//累计请假天数总和
		int totalLeaveDay=leaveNewDay+leaveLastDay;
		//更新数据库
		try {
			preparedStatement=connection.prepareStatement("update child_attendence set leave_day=? where child_id=? and phone=?");
			preparedStatement.setInt(1, totalLeaveDay);
			preparedStatement.setInt(2, id);
			preparedStatement.setString(3, phone);
			b=preparedStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return b;
	}
	
	/**
	 * 将用户上传截图的相关信息保存到数据库
	 * @param moneyPicture
	 * @param monthNow
	 * @param screenshotName
	 * @return
	 */
	public boolean preserveScreenshotInfo(MoneyPicture moneyPicture,int monthNow,String screenshotName) {
		boolean b=false;
		try {
			preparedStatement=connection.prepareStatement("insert into money_screenshot(child_name,phone,grade_num,class_num,screenshot_name,month) values(?,?,?,?,?,?)");
			preparedStatement.setString(1, moneyPicture.getBabyName());
			preparedStatement.setString(2, moneyPicture.getPhone());
			preparedStatement.setString(3, moneyPicture.getBabyGrade());
			preparedStatement.setString(4, moneyPicture.getBabyClass());
			preparedStatement.setString(5, screenshotName);
			preparedStatement.setInt(6, monthNow);
			b=preparedStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return b;
	}
	
	/**
	 * 根据当前月份获取所有相关的缴费截图信息
	 * @param monthNow
	 * @return
	 */
	public List<ScreenshotInfo> searchScreenshotInfo(int monthNow){
		List<ScreenshotInfo> list=null;
		try {
			preparedStatement=connection.prepareStatement("select * from money_screenshot where month=?");
			preparedStatement.setInt(1, monthNow);
			ResultSet rs=preparedStatement.executeQuery();
			if(rs!=null) {
				list=new ArrayList<ScreenshotInfo>();
				while(rs.next()) {
					ScreenshotInfo screenshotInfo=new ScreenshotInfo();
					screenshotInfo.setId(rs.getInt("id"));
					screenshotInfo.setBabyClass(rs.getString("class_num"));
					screenshotInfo.setBabyGrade(rs.getString("grade_num"));
					screenshotInfo.setPhone(rs.getString("phone"));
					screenshotInfo.setBabyName(rs.getString("child_name"));
					screenshotInfo.setPhotoName(rs.getString("screenshot_name"));
					list.add(screenshotInfo);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	
	/**
	 * 将收款码信息进行分页
	 * 
	 * @return
	 */
	public int countByPageCharge() {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		int count = 0;
		try {
			String sql = "";
			conn = DBUtil.getConnection();
			sql = "select count(*) from charge";
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
	 * 根据页数查找收款码信息
	 * 
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public List<Charge> findChargeByPage(int pageNum, int pageSize) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<Charge> list = new ArrayList<>();
		try {
			String sql = "";
			conn = DBUtil.getConnection();
			sql = "select * from charge limit ?, ?";
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, (pageNum - 1) * pageSize);
			pstm.setInt(2, pageSize);
			rs = pstm.executeQuery();
			while (rs.next()) {
				Charge charge = new Charge();
				charge.setId(rs.getInt(1));
				charge.setBabyClass(rs.getString(2));
				charge.setTeacher(rs.getString(3));
				charge.setWeChat(rs.getString(4));
				charge.setAlipay(rs.getString(5));
				list.add(charge);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, pstm, conn);
		}
		return list;
	}
	/**
	 * 获取所有收款码信息
	 * @return
	 */
	public List<Charge> findCharge(){
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<Charge> list = new ArrayList<>();
		try {
			conn = DBUtil.getConnection();
			pstm = conn.prepareStatement("select * from charge");
			rs = pstm.executeQuery();
			while(rs.next()) {
				Charge charge = new Charge();
				charge.setId(rs.getInt(1));
				charge.setBabyClass(rs.getString(2));
				charge.setTeacher(rs.getString(3));
				charge.setWeChat(rs.getString(4));
				charge.setAlipay(rs.getString(5));
				list.add(charge);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, pstm, conn);
		}
		return list;
	}
	
	/**
	 * 新增收款码信息
	 * 
	 * @param teacher
	 * @return
	 */
	public boolean addCharge(Charge charge) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		// 获取教师信息
		String babyClass = charge.getBabyClass();
		String teacher = charge.getTeacher();
		String weChat = charge.getWeChat();
		String alipay = charge.getAlipay();
		System.out.println(babyClass+" "+teacher+" "+weChat+" "+alipay);
		int n = -1;// 存储插入的记录数
		try {
			conn = DBUtil.getConnection();
			String select = "select * from charge where babyClass='" + babyClass + "' and teacher=" + teacher + "";
			pstm = conn.prepareStatement(select);
			rs = pstm.executeQuery();
			boolean isExist = rs.next();
			if (!isExist) {
				String sql = "";
				if (weChat != null&&!weChat.equals("")&&alipay != null&&!alipay.equals("")) {// 照片不为空
					if (babyClass!=null&&!babyClass.equals("")&&teacher!=null&&!teacher.equals("")) {
						sql = "insert into charge(babyClass,teacher,WeChat,Alipay) values('" + babyClass + "','" +teacher + "','"+weChat+"','"+alipay+"')";
						// 将信息插入到数据库表中
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
	 * 根据id删除收款码信息
	 * 
	 * @param id
	 * @return
	 */
	public boolean deleteCharge(int id,String url) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		int n = -1;// 存储修改的记录
		try {
			conn = DBUtil.getConnection();
			String select = "select * from charge where id=" + id + "";
			pstm = conn.prepareStatement(select);
			rs = pstm.executeQuery();
			boolean isExist = rs.next();
			if (isExist) {
	            File file1 = new File(url+rs.getString(4));
	            File file2 = new File(url+rs.getString(5));
	            //判断文件是否存在
	            if (file1.exists() == true&&file2.exists() == true){
	                System.out.println("图片存在，可执行删除操作");
	                Boolean flag1 = false;
	                Boolean flag2 = false;
	                flag1 = file1.delete();
	                flag2 = file2.delete();
	                if (flag1&&flag2){
	                    System.out.println("成功删除图片"+file1.getName());
	                }else {
	                    n=-1;
	                }
	            }else {
	            	n=-1;
	            }
				String sql = "delete from charge where id=" + id + "";
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
	 * 根据id获取收款码信息
	 * @param id
	 * @return
	 */
	public Charge findChargeById(int id) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		Charge charge = new Charge();
		try {
			conn = DBUtil.getConnection();
			pstm = conn.prepareStatement("select * from charge where id=" + id);
			rs = pstm.executeQuery();
			while(rs.next()) {
				charge.setId(rs.getInt(1));
				charge.setBabyClass(rs.getString(2));
				charge.setTeacher(rs.getString(3));
				charge.setWeChat(rs.getString(4));
				charge.setAlipay(rs.getString(5));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, pstm, conn);
		}
		return charge;
	}
	
	/**
	 * 修改收款码信息
	 * @param teacher
	 * @return
	 */
	public boolean updateCharge(Charge charge) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		// 获取教师信息
		int id = charge.getId();
		String babyClass = charge.getBabyClass();
		String teacher = charge.getTeacher();
		String weChat = charge.getWeChat();
		String alipay = charge.getAlipay();
		int n = -1;// 存储插入的记录数
		try {
			conn = DBUtil.getConnection();
			String select = "select * from charge where id=" + id + "";
			pstm = conn.prepareStatement(select);
			rs = pstm.executeQuery();
			boolean isExist = rs.next();
			if (isExist) {
				String sql = null;
				if (weChat != null && !weChat.equals("")) {
					if (alipay!=null&&!alipay.equals("")) {
						sql = "update charge set babyClass ='"+babyClass+"',alipay='"+alipay+"',WeChat='" +weChat + "', Alipay='" + alipay + "' where id=" + id + "";
			            // 将更新后的教师信息插入到数据库表中
						n = pstm.executeUpdate(sql);
					} else {
						sql = "update charge set babyClass ='"+babyClass+"',alipay='"+alipay+"',WeChat='" +weChat + "' where id=" + id + "";
			            // 将更新后的教师信息插入到数据库表中
						n = pstm.executeUpdate(sql);
					}
				}else{
					if (alipay!=null&&!alipay.equals("")) {
						sql = "update charge set babyClass ='"+babyClass+"',alipay='"+alipay+"', Alipay='" + alipay + "' where id=" + id + "";
			            // 将更新后的教师信息插入到数据库表中
						n = pstm.executeUpdate(sql);
					} else {
						sql = "update charge set babyClass ='"+babyClass+"',alipay='"+alipay+"' where id=" + id + "";
			            // 将更新后的教师信息插入到数据库表中
						n = pstm.executeUpdate(sql);
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
	 * 根据id获取收款码信息
	 * @param id
	 * @return
	 */
	public Charge findChargeByBabyClass(String babyClass) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		Charge charge = new Charge();
		try {
			conn = DBUtil.getConnection();
			pstm = conn.prepareStatement("select * from charge where babyClass='" +babyClass+"'");
			rs = pstm.executeQuery();
			while(rs.next()) {
				charge.setId(rs.getInt(1));
				charge.setBabyClass(rs.getString(2));
				charge.setTeacher(rs.getString(3));
				charge.setWeChat(rs.getString(4));
				charge.setAlipay(rs.getString(5));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, pstm, conn);
		}
		return charge;
	}
	/**
	 * 根据输入信息模糊查找并将其分页
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
			sql = "select count(id) from charge where babyClass like ?";
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
	 * 根据页数和搜索条件查找图片
	 * @param pageNum
	 * @param pageSize
	 * @param name
	 * @return
	 */
	public List<Charge> findByPageAndSearchInfo(int pageNum, int pageSize, String searchInfo) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<Charge> list = new ArrayList<>();
		try {
			String sql = "";
			conn = DBUtil.getConnection();
			sql = "select * from charge where babyClass like ? limit ?, ?";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, "%" + searchInfo + "%");
			pstm.setInt(2, (pageNum - 1) * pageSize);
			pstm.setInt(3, pageSize);
			rs = pstm.executeQuery();
			while (rs.next()) {
				Charge charge = new Charge();
				charge.setId(rs.getInt(1));
				charge.setBabyClass(rs.getString(2));
				charge.setTeacher(rs.getString(3));
				charge.setWeChat(rs.getString(4));
				charge.setAlipay(rs.getString(5));
				list.add(charge);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, pstm, conn);
		}
		return list;
	}
	

}
