package com.group.kindergarten.applyinfo.service;

import java.util.List;

import com.group.kindergarten.applyinfo.dao.EnterDao;
import com.group.kindergarten.applyinfo.entity.ApplyInfo;
import com.group.kindergarten.util.Page;

public class EnterService {
	private static EnterService enterService;
	private static EnterDao enterDao;
	
	
	/**
	 * 得到一个enterService实例
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
	 * 判断孩子报名信息
	 * @return boolean
	 * */
	public boolean isExist(String babyIDnumber) {
		
		return enterDao.isExist(babyIDnumber);
	}
	
	/**
	 * 添加孩子报名信息
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
	 * 查询所有报名的孩子的所有信息
	 * @return list包含所有孩子信息的集合
	 */
	public List<ApplyInfo> searchAllChild(){
		List<ApplyInfo> list=enterDao.searchAllChild();
		
		return list;
	}
	
	/**
	 * 根据当前手机号查询报名的孩子的所有信息
	 * @return list包含当前手机号所报名的孩子信息的集合
	 */
	public List<ApplyInfo> searchChildByPhoneNum(String phoneNum){
		List<ApplyInfo> list=enterDao.searchChildByPhoneNum(phoneNum);
		
		return list;
	}
	
	/**
	 * 根据当前applyId查询报名的孩子的所有信息
	 * @return 报名的孩子信息
	 */
	public ApplyInfo searchChildById(int id){
		ApplyInfo applyInfo=enterDao.searchChildById(id);
		
		return applyInfo;
	}
	
	
	/**
	 * 分页查询所有报名的孩子的所有信息
	 * @return list包含孩子信息的集合
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
	 * 根据名字查询孩子报名信息
	 * @return list包含孩子信息的集合
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
	 * 修改孩子报名信息
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
	 * 删除孩子报名信息
	 * 
	 * */
	public boolean deleteApplyinfo(String id) {
		
		return enterDao.deleteApplyinfo(id);
		
	}
}
