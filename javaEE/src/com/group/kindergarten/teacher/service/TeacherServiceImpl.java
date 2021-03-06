package com.group.kindergarten.teacher.service;

import java.util.List;

import com.group.kindergarten.teacher.dao.TeacherDaoImpl;
import com.group.kindergarten.teacher.entity.Teacher;
import com.group.kindergarten.util.Page;

public class TeacherServiceImpl {
	
	/**
	 * 分页查询教师信息
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
	 * 分页查询离职教师信息
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public Page<Teacher> listDeletedByPage(int pageNum, int pageSize){
		Page<Teacher> page = new Page<Teacher>(pageNum, pageSize);
		TeacherDaoImpl teacherDaoImpl = new TeacherDaoImpl();
		int count = teacherDaoImpl.countDeletedByPage();
		List<Teacher> list = teacherDaoImpl.findDeletedByPage(pageNum,pageSize);
		page.setList(list);
		page.setTotalCount(count);
		return page;
	}
	
	/**
	 * 根据页数和搜索条件查找离职教师信息
	 * @param pageNum
	 * @param pageSize
	 * @param name
	 * @return
	 */
	public Page<Teacher> listByPageAndName(int pageNum, int pageSize, String name){
		Page<Teacher> page = new Page<Teacher>(pageNum, pageSize);
		TeacherDaoImpl teacherDaoImpl = new TeacherDaoImpl();
		int count = teacherDaoImpl.countByPageAndName(name);
		List<Teacher> list = teacherDaoImpl.findByPageAndName(pageNum,pageSize,name);
		page.setList(list);
		page.setTotalCount(count);
		return page;
	}
	
	/**
	 * 根据页数和搜索条件查找离职教师信息
	 * @param pageNum
	 * @param pageSize
	 * @param name
	 * @return
	 */
	public Page<Teacher> listDeletedByPageAndName(int pageNum, int pageSize, String name){
		Page<Teacher> page = new Page<Teacher>(pageNum, pageSize);
		TeacherDaoImpl teacherDaoImpl = new TeacherDaoImpl();
		int count = teacherDaoImpl.countDeletedByPageAndName(name);
		List<Teacher> list = teacherDaoImpl.findDeletedByPageAndName(pageNum,pageSize,name);
		page.setList(list);
		page.setTotalCount(count);
		return page;
	}
	
	/**
	 * 根据id获取教师信息
	 * @param id
	 * @return
	 */
	public Teacher findTeacherByIdService(int id) {
		return new TeacherDaoImpl().findTeacherById(id);
	}
	
	/**
	 * 获取所有教师信息
	 * @return
	 */
	public List<Teacher> findTeachersService(){
		TeacherDaoImpl teacherDaoImpl = new TeacherDaoImpl();
		List<Teacher> list = teacherDaoImpl.findTeachers();
		return list;
	}
	
	/**
	 * 新增教师
	 * @param teacher
	 * @return
	 */
	public boolean addTeacherService(Teacher teacher) {
		return new TeacherDaoImpl().addTeacher(teacher);
	}
	
	/**
	 * 根据id删除教师
	 * @param id
	 * @return
	 */
	public boolean deleteTeacherService(int id) {
		return new TeacherDaoImpl().deleteTeacher(id);
	}
	
	/**
	 * 修改教师信息
	 * @return
	 */
	public boolean updateTeacherService(Teacher teacher) {
		return new TeacherDaoImpl().updateTeacher(teacher);
	}
	
}
