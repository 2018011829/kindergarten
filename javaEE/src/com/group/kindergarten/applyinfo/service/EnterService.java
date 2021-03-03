package com.group.kindergarten.applyinfo.service;

import java.util.List;

import com.group.kindergarten.applyinfo.dao.EnterDao;
import com.group.kindergarten.applyinfo.entity.ApplyInfo;
import com.group.kindergarten.util.Page;

public class EnterService {
	private static EnterService enterService;
	private static EnterDao enterDao;
	
	
	/**
	 * �õ�һ��enterServiceʵ��
	 * 
	 * @return EnterService
	 */
	public static EnterService getInstance() {
		if (null == enterService) {
			enterService = new EnterService();
		}
		if (null == enterDao) {
			enterDao = EnterDao.getInstance();
		}
		return enterService;
	}
	
	
	/**
	 * �жϺ��ӱ�����Ϣ
	 * @return boolean
	 * */
	public boolean isExist(String babyIDnumber) {
		
		return enterDao.isExist(babyIDnumber);
	}
	
	/**
	 * ��Ӻ��ӱ�����Ϣ
	 * @return boolean
	 * */
	public boolean addChildApplyInformation(String userNumber,String babyName,String babyBirthday,String babySex,String babyIDnumber
			,String babyAddoAllergies,String parentName1,String relation1,String parentIDnumber1,String phoneNumber1,String workSpace1,
			String homeAddress1,String parentName2,String relation2,String parentIDnumber2,String phoneNumber2,String workSpace2,
			String homeAddress2) {
		
		return enterDao.addChildApplyInformation(userNumber,babyName, babyBirthday, babySex, babyIDnumber, babyAddoAllergies, 
				parentName1, relation1,parentIDnumber1, phoneNumber1, workSpace1, homeAddress1, 
				parentName2, relation2,parentIDnumber2, phoneNumber2, workSpace2, homeAddress2);
	}
	
	/**
	 * ��ѯ���б����ĺ��ӵ�������Ϣ
	 * @return list�������к�����Ϣ�ļ���
	 */
	public List<ApplyInfo> searchAllChild(){
		List<ApplyInfo> list=enterDao.searchAllChild();
		
		return list;
	}
	
	/**
	 * ���ݵ�ǰ�ֻ��Ų�ѯ�����ĺ��ӵ�������Ϣ
	 * @return list������ǰ�ֻ����������ĺ�����Ϣ�ļ���
	 */
	public List<ApplyInfo> searchChildByPhoneNum(String phoneNum){
		List<ApplyInfo> list=enterDao.searchChildByPhoneNum(phoneNum);
		
		return list;
	}
	
	/**
	 * ���ݵ�ǰapplyId��ѯ�����ĺ��ӵ�������Ϣ
	 * @return �����ĺ�����Ϣ
	 */
	public ApplyInfo searchChildById(int id){
		ApplyInfo applyInfo=enterDao.searchChildById(id);
		
		return applyInfo;
	}
	
	
	/**
	 * ��ҳ��ѯ���б����ĺ��ӵ�������Ϣ
	 * @return list����������Ϣ�ļ���
	 */
	public Page<ApplyInfo> searchChildByPage(int pageNum, int pageSize){
		Page<ApplyInfo> page = new Page<ApplyInfo>(pageNum, pageSize);
		int count = enterDao.countByPage();
		List<ApplyInfo> list=enterDao.searchChildByPage(pageNum, pageSize);
		page.setList(list);
		page.setTotalCount(count);
		
		return page;
	}
	
	/**
	 * �������ֲ�ѯ���ӱ�����Ϣ
	 * @return list����������Ϣ�ļ���
	 */
	public Page<ApplyInfo> searchChildByName(int pageNum, int pageSize,String msg){
		Page<ApplyInfo> page = new Page<ApplyInfo>(pageNum, pageSize);
		int count = enterDao.searchCountByPage(msg);
		List<ApplyInfo> list=enterDao.searchChildByName(pageNum, pageSize, msg);
		page.setList(list);
		page.setTotalCount(count);
		
		return page;
	}
	
	/**
	 * �޸ĺ��ӱ�����Ϣ
	 * @return boolean
	 * */
	public boolean updataApplyinfo(String id,String userNumber,String babyName,String babyBirthday,String babySex,String babyIDnumber
			,String babyAddoAllergies,String parentName1,String relation1,String parentIDnumber1,String phoneNumber1,String workSpace1,
			String homeAddress1,String parentName2,String relation2,String parentIDnumber2,String phoneNumber2,String workSpace2,
			String homeAddress2) {
		
		
		return enterDao.updataApplyinfo(id, userNumber, babyName, babyBirthday, babySex, babyIDnumber, babyAddoAllergies, 
				parentName1, relation1, parentIDnumber1, phoneNumber1, workSpace1, homeAddress1, parentName2, relation2, parentIDnumber2, phoneNumber2, workSpace2, homeAddress2);
	}
	
	/**
	 * ɾ�����ӱ�����Ϣ
	 * 
	 * */
	public boolean deleteApplyinfo(String id) {
		
		return enterDao.deleteApplyinfo(id);
		
	}
}
