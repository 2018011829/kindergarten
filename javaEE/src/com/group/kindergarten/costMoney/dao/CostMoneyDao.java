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
			preparedStatement=connection.prepareStatement("select * from child_attendence where child_id=? and phone=?");
			preparedStatement.setInt(1, id);
			preparedStatement.setString(2, parentPhone);
			ResultSet rs=preparedStatement.executeQuery();
			if(rs.next()) {
				// �õ�����ԭ�ȵĵ�������
				int preDayNum=rs.getInt("leave_day");
				// ��ȥ���ڵ��������
				int nowDayNum=preDayNum+dayNum;
				// �޸����ݿ��е�����
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
	 * ÿ��1�ս����ϸ��µ����������0
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
	 * ��ȡĳ�������ϸ��µ��������
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
	 * �����·ݻ�ȡ��Ӧ�·ݵ����� 
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
	 * ������ٵ����������浽���ݿ� �����������ݿ�����������
	 * @param id
	 * @param phone
	 * @param dayStartNum
	 * @param dayEndNum
	 * @return
	 */
	public boolean updateLeaveInfo(String name, String phone, int dayStartNum, int dayEndNum) {
		boolean b=false;
		//��ȡ���ӵ�id
		int id=returnChildId(name, phone);
		//�����������
		int leaveNewDay=dayEndNum-dayStartNum;
		//��ȡ���ݿ��е��������
		int leaveLastDay=getPreMonthLeave(name, phone);
		//�ۼ���������ܺ�
		int totalLeaveDay=leaveNewDay+leaveLastDay;
		//�������ݿ�
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
	 * ���û��ϴ���ͼ�������Ϣ���浽���ݿ�
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
	 * ���ݵ�ǰ�·ݻ�ȡ������صĽɷѽ�ͼ��Ϣ
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
	 * ���տ�����Ϣ���з�ҳ
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
	 * ����ҳ�������տ�����Ϣ
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
	 * ��ȡ�����տ�����Ϣ
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
	 * �����տ�����Ϣ
	 * 
	 * @param teacher
	 * @return
	 */
	public boolean addCharge(Charge charge) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		// ��ȡ��ʦ��Ϣ
		String babyClass = charge.getBabyClass();
		String teacher = charge.getTeacher();
		String weChat = charge.getWeChat();
		String alipay = charge.getAlipay();
		System.out.println(babyClass+" "+teacher+" "+weChat+" "+alipay);
		int n = -1;// �洢����ļ�¼��
		try {
			conn = DBUtil.getConnection();
			String select = "select * from charge where babyClass='" + babyClass + "' and teacher=" + teacher + "";
			pstm = conn.prepareStatement(select);
			rs = pstm.executeQuery();
			boolean isExist = rs.next();
			if (!isExist) {
				String sql = "";
				if (weChat != null&&!weChat.equals("")&&alipay != null&&!alipay.equals("")) {// ��Ƭ��Ϊ��
					if (babyClass!=null&&!babyClass.equals("")&&teacher!=null&&!teacher.equals("")) {
						sql = "insert into charge(babyClass,teacher,WeChat,Alipay) values('" + babyClass + "','" +teacher + "','"+weChat+"','"+alipay+"')";
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
	 * ����idɾ���տ�����Ϣ
	 * 
	 * @param id
	 * @return
	 */
	public boolean deleteCharge(int id,String url) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		int n = -1;// �洢�޸ĵļ�¼
		try {
			conn = DBUtil.getConnection();
			String select = "select * from charge where id=" + id + "";
			pstm = conn.prepareStatement(select);
			rs = pstm.executeQuery();
			boolean isExist = rs.next();
			if (isExist) {
	            File file1 = new File(url+rs.getString(4));
	            File file2 = new File(url+rs.getString(5));
	            //�ж��ļ��Ƿ����
	            if (file1.exists() == true&&file2.exists() == true){
	                System.out.println("ͼƬ���ڣ���ִ��ɾ������");
	                Boolean flag1 = false;
	                Boolean flag2 = false;
	                flag1 = file1.delete();
	                flag2 = file2.delete();
	                if (flag1&&flag2){
	                    System.out.println("�ɹ�ɾ��ͼƬ"+file1.getName());
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
	 * ����id��ȡ�տ�����Ϣ
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
	 * �޸��տ�����Ϣ
	 * @param teacher
	 * @return
	 */
	public boolean updateCharge(Charge charge) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		// ��ȡ��ʦ��Ϣ
		int id = charge.getId();
		String babyClass = charge.getBabyClass();
		String teacher = charge.getTeacher();
		String weChat = charge.getWeChat();
		String alipay = charge.getAlipay();
		int n = -1;// �洢����ļ�¼��
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
			            // �����º�Ľ�ʦ��Ϣ���뵽���ݿ����
						n = pstm.executeUpdate(sql);
					} else {
						sql = "update charge set babyClass ='"+babyClass+"',alipay='"+alipay+"',WeChat='" +weChat + "' where id=" + id + "";
			            // �����º�Ľ�ʦ��Ϣ���뵽���ݿ����
						n = pstm.executeUpdate(sql);
					}
				}else{
					if (alipay!=null&&!alipay.equals("")) {
						sql = "update charge set babyClass ='"+babyClass+"',alipay='"+alipay+"', Alipay='" + alipay + "' where id=" + id + "";
			            // �����º�Ľ�ʦ��Ϣ���뵽���ݿ����
						n = pstm.executeUpdate(sql);
					} else {
						sql = "update charge set babyClass ='"+babyClass+"',alipay='"+alipay+"' where id=" + id + "";
			            // �����º�Ľ�ʦ��Ϣ���뵽���ݿ����
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
	 * ����id��ȡ�տ�����Ϣ
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
	 * ����ҳ����������������ͼƬ
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
