package com.group.kindergarten.teacher.service;

import java.util.List;

import com.group.kindergarten.teacher.dao.TeacherDaoImpl;
import com.group.kindergarten.teacher.entity.Teacher;
import com.group.kindergarten.util.Page;

public class TeacherServiceImpl {
	
	/**
	 * ��ҳ��ѯ��ʦ��Ϣ
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public Page<Teacher> listByPage(int pageNum, int pageSize){
		Page<Teacher> page = new Page<Teacher>(pageNum, pageSize);
		TeacherDaoImpl teacherDaoImpl = new TeacherDaoImpl();
		int count = teacherDaoImpl.countByPage();
		List<Teacher> list = teacherDaoImpl.findByPage(pageNum,pageSize);
		page.setList(list);
		page.setTotalCount(count);
		return page;
	}
	
	/**
	 * ��ȡ���н�ʦ��Ϣ
	 * @return
	 */
	public List<Teacher> findTeachersService(){
		TeacherDaoImpl teacherDaoImpl = new TeacherDaoImpl();
		List<Teacher> list = teacherDaoImpl.findTeachers();
		return list;
	}
	
	/**
	 * ������ʦ
	 * @param teacher
	 * @return
	 */
	public boolean addTeacherService(Teacher teacher) {
		return new TeacherDaoImpl().addTeacher(teacher);
	}
	
	/**
	 * ����idɾ����ʦ
	 * @param id
	 * @return
	 */
	public boolean deleteTeacherService(int id) {
		return new TeacherDaoImpl().deleteTeacher(id);
	}
	
	/**
	 * �޸Ľ�ʦ��Ϣ
	 * @return
	 */
	public boolean updateTeacherService(Teacher teacher) {
		return new TeacherDaoImpl().updateTeacher(teacher);
	}
	
}
