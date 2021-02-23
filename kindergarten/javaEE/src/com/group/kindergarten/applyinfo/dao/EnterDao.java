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
	
	public int countByPage(){
		ResultSet rs = null;
		int count = 0;
		try {
			String sql="select count(id) from applyinfo";
			preparedStatement = connection.prepareStatement(sql);
			rs = preparedStatement.executeQuery();
			while(rs.next()) {
				count = rs.getInt(1);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return count;
	}
	
	public int searchCountByPage(String msg){
		ResultSet rs = null;
		int count = 0;
		try {
			String sql="select count(id) from applyinfo where babyName like '%"+msg+"%'";
			preparedStatement = connection.prepareStatement(sql);
			rs = preparedStatement.executeQuery();
			while(rs.next()) {
				count = rs.getInt(1);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return count;
	}
	
	/**
	 * �����ݿ����һ�����ӱ�����Ϣ
	 * @param userNumber ��ǰ�ҳ����ֻ���
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
			,String babyAddoAllergies,String parentName1,String relation1,String parentIDnumber1,String phoneNumber1,String workSpace1,
			String homeAddress1,String parentName2,String relation2,String parentIDnumber2,String phoneNumber2,String workSpace2,
			String homeAddress2) {
		boolean b=false;
		try {
			preparedStatement=connection.prepareStatement("insert into applyinfo(userNumber,babyName,babyBirthday,babySex,babyIDnumber"
					+ ",babyAddoAllergies,parentName1,relation1,parentIDnumber1,phoneNumber1,workSpace1,homeAddress1,parentName2,relation2,parentIDnumber2,phoneNumber2,workSpace2,homeAddress2) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			preparedStatement.setString(1, userNumber);
			preparedStatement.setString(2, babyName);
			preparedStatement.setString(3, babyBirthday);
			preparedStatement.setString(4, babySex);
			preparedStatement.setString(5, babyIDnumber);
			preparedStatement.setString(6, babyAddoAllergies);
			preparedStatement.setString(7, parentName1);
			preparedStatement.setString(8, relation1);
			preparedStatement.setString(9, parentIDnumber1);
			preparedStatement.setString(10, phoneNumber1);
			preparedStatement.setString(11, workSpace1);
			preparedStatement.setString(12, homeAddress1);
			preparedStatement.setString(13, parentName2);
			preparedStatement.setString(14, relation2);
			preparedStatement.setString(15, parentIDnumber2);
			preparedStatement.setString(16, phoneNumber2);
			preparedStatement.setString(17, workSpace2);
			preparedStatement.setString(18, homeAddress2);
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
	
	
	/**
	 * �޸ĺ��ӱ�����Ϣ
	 * @return boolean
	 * */
	public boolean updataApplyinfo(String id,String userNumber,String babyName,String babyBirthday,String babySex,String babyIDnumber
			,String babyAddoAllergies,String parentName1,String relation1,String parentIDnumber1,String phoneNumber1,String workSpace1,
			String homeAddress1,String parentName2,String relation2,String parentIDnumber2,String phoneNumber2,String workSpace2,
			String homeAddress2) {
			boolean b=false;
			try {
				preparedStatement=connection.prepareStatement("update applyinfo set userNumber='"+userNumber+"',babyName='"+babyName+"',babyBirthday='"+babyBirthday+"',babySex='"+babySex+"',babyIDnumber='"+babyIDnumber+"'" + 
				",babyAddoAllergies='"+babyAddoAllergies+"',parentName1='"+parentName1+"',relation1='"+relation1+"',parentIDnumber1='"+parentIDnumber1+"',phoneNumber1='"+phoneNumber1+"',workSpace1='"+workSpace1+"',homeAddress1='"+homeAddress1+"'"
						+ ",parentName2='"+parentName2+"',relation2='"+relation2+"',parentIDnumber2='"+parentIDnumber2+"',phoneNumber2='"+phoneNumber2+"',workSpace2='"+workSpace2+"',homeAddress2='"+homeAddress2+"' where id = "+id);
				int rows=preparedStatement.executeUpdate();
				if(rows>0) {
				b=true;
				System.out.println("�ɹ��޸ĺ��ӱ�����Ϣ");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return b;
	}
	
	/**
	 * ɾ�����ӱ�����Ϣ
	 * 
	 * */
	public boolean deleteApplyinfo(String id) {
		boolean b=false;
		try {
			preparedStatement=connection.prepareStatement("delete from applyinfo where id ="+id);
			int rows=preparedStatement.executeUpdate();
			if(rows>0) {
			b=true;
			System.out.println("�ɹ�ɾ�����ӱ�����Ϣ");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return b;
	}
	
	/**
	 * ��ҳ��ѯ���б����ĺ��ӵ�������Ϣ
	 * @return list����������Ϣ�ļ���
	 */
	public List<ApplyInfo> searchChildByPage(int pageNum, int pageSize){
		List<ApplyInfo> list=null;
		String sql="select * from applyinfo limit ?, ?";
		try {
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setInt(1, (pageNum-1)*pageSize);
			preparedStatement.setInt(2, pageSize);
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
	
	
	
	/**
	 * �������ֲ�ѯ���ӱ�����Ϣ
	 * @return list����������Ϣ�ļ���
	 */
	public List<ApplyInfo> searchChildByName(int pageNum, int pageSize,String msg){
		List<ApplyInfo> list=null;
		String sql="select * from applyinfo where babyName like '%"+msg+"%' limit ?, ?";
		try {
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setInt(1, (pageNum-1)*pageSize);
			preparedStatement.setInt(2, pageSize);
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
	
	/**
	 * ��ѯ���б����ĺ��ӵ�������Ϣ
	 * @return list�������к�����Ϣ�ļ���
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
	
	/**
	 * ���ݵ�ǰ�ֻ��Ų�ѯ�����ĺ��ӵ�������Ϣ
	 * @return list������ǰ�ֻ����������ĺ�����Ϣ�ļ���
	 */
	public List<ApplyInfo> searchChildByPhoneNum(String phoneNum){
		List<ApplyInfo> list=null;
		String sql="select * from applyinfo where userNumber=?";
		try {
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, phoneNum);
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
	
	/**
	 * ���ݵ�ǰapplyId��ѯ�����ĺ��ӵ�������Ϣ
	 * @return �����ĺ�����Ϣ
	 */
	public ApplyInfo searchChildById(int id){
		ApplyInfo applyInfo=null;
		String sql="select * from applyinfo where id=?";
		try {
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			ResultSet rs=preparedStatement.executeQuery();
			if(rs!=null) {
				while(rs.next()) {
					applyInfo=new ApplyInfo(rs.getInt("id"),rs.getString("userNumber"),rs.getString("babyName"),
							rs.getString("babyBirthday"),rs.getString("babySex"),rs.getString("babyIDnumber"),
							rs.getString("babyAddoAllergies"),rs.getString("parentName1"),rs.getString("relation1"),
							rs.getString("parentIDnumber1"),rs.getString("phoneNumber1"),rs.getString("workSpace1"),
							rs.getString("homeAddress1"),rs.getString("parentName2"),rs.getString("relation2"),
							rs.getString("parentIDnumber2"),rs.getString("phoneNumber2"),rs.getString("workSpace2"),
							rs.getString("homeAddress2"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return applyInfo;
	}
}
