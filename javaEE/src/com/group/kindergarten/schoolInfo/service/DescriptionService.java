package com.group.kindergarten.schoolInfo.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import com.group.kindergarten.schoolInfo.dao.DescriptionDao;
import com.group.kindergarten.schoolInfo.entity.Description;
import com.group.kindergarten.teacher.dao.TeacherDaoImpl;
import com.group.kindergarten.teacher.entity.Teacher;
import com.group.kindergarten.util.DBUtil;
import com.group.kindergarten.util.Page;

public class DescriptionService {
	/**
	 * 分页查询校园信息简介
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public Page<Description> listByPage(int pageNum, int pageSize){
		Page<Description> page = new Page<Description>(pageNum, pageSize);
		DescriptionDao schoolInfoDao = new DescriptionDao();
		int count = schoolInfoDao.countByPage();
		List<Description> list = schoolInfoDao.findByPage(pageNum,pageSize);
		page.setList(list);
		page.setTotalCount(count);
		return page;
	}
	
	/**
	 * 根据id删除描述
	 * @param id
	 * @return
	 */
	public boolean deleteDescriptionService(int id) {
		return new DescriptionDao().deleteDescription(id);
	}
	
}
