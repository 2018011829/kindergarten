package com.group.kindergarten.student.service;

import java.util.List;

import com.group.kindergarten.applyinfo.dao.EnterDao;
import com.group.kindergarten.applyinfo.entity.ApplyInfo;
import com.group.kindergarten.applyinfo.service.EnterService;
import com.group.kindergarten.student.dao.StudentsDao;
import com.group.kindergarten.student.entity.Students;
import com.group.kindergarten.util.Page;

public class StudentsService {
	private static StudentsService studentsService;
	private static StudentsDao studentsDao;
	
	
	/**
	 * 得到一个enterService实例
	 * 
	 * @return EnterService
	 */
	public static StudentsService getInstance() {
		if (null == studentsService) {
			studentsService = new StudentsService();
		}
		if (null == studentsDao) {
			studentsDao = StudentsDao.getInstance();
		}
		return studentsService ;
	}

	/**
	 * 分页查询所有报名的孩子的所有信息
	 * @return list包含孩子信息的集合
	 */
	public Page<Students> searchChildByPage(int pageNum, int pageSize){
		Page<Students> page = new Page<Students>(pageNum, pageSize);
		int count = studentsDao.countByPage();
		List<Students> list=studentsDao.searchChildByPage(pageNum, pageSize);
		page.setList(list);
		page.setTotalCount(count);
		return page;
	}
	/**
	 * 根据名字查询孩子报名信息
	 * @return list包含孩子信息的集合
	 */
	public Page<Students> searchChildByName(int pageNum, int pageSize,String babyName){
		Page<Students> page = new Page<Students>(pageNum, pageSize);
		int count = studentsDao.searchCountByPage(babyName);
		List<Students> list=studentsDao.searchChildByName(pageNum, pageSize, babyName);
		page.setList(list);
		page.setTotalCount(count);
		return page;
	}

	/**
	 * 将excel表中的数据保存到数据库中
	 * @param students
	 * @return
	 */
	public boolean insertDataAboutStudent(List<Students> students) {
		boolean b=studentsDao.insertDataAboutStudent(students);
		
		return b;
	}
}
