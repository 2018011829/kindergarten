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
	 * �õ�һ��enterServiceʵ��
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
	 * ��ҳ��ѯ���б����ĺ��ӵ�������Ϣ
	 * @return list����������Ϣ�ļ���
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
	 * �������ֲ�ѯ���ӱ�����Ϣ
	 * @return list����������Ϣ�ļ���
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
	 * ��excel���е����ݱ��浽���ݿ���
	 * @param students
	 * @return
	 */
	public boolean insertDataAboutStudent(List<Students> students) {
		boolean b=studentsDao.insertDataAboutStudent(students);
		
		return b;
	}
}
