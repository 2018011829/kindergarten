package com.group.kindergarten.schoolInfo.service;

import java.util.List;

import com.group.kindergarten.schoolInfo.dao.BasicInfoDao;
import com.group.kindergarten.schoolInfo.dao.DescriptionDao;
import com.group.kindergarten.schoolInfo.dao.PictureDao;
import com.group.kindergarten.schoolInfo.entity.BasicInfo;
import com.group.kindergarten.schoolInfo.entity.Description;
import com.group.kindergarten.schoolInfo.entity.Picture;
import com.group.kindergarten.teacher.dao.TeacherDaoImpl;
import com.group.kindergarten.util.Page;

public class BasicInfoService {
	/**
	 * 分页查询基本信息
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public Page<BasicInfo> listByPage(int pageNum, int pageSize){
		Page<BasicInfo> page = new Page<BasicInfo>(pageNum, pageSize);
		BasicInfoDao basicInfoDao = new BasicInfoDao();
		int count = basicInfoDao.countByPage();
		List<BasicInfo> list = basicInfoDao.findByPage(pageNum,pageSize);
		page.setList(list);
		page.setTotalCount(count);
		return page;
	}
	/**
	 * 根据id获取基本信息
	 * @param id
	 * @return
	 */
	public BasicInfo findBasicInfoByIdService(int id) {
		return new BasicInfoDao().findBasicInfoById(id);
	}
	/**
	 * 修改基本信息
	 * @return
	 */
	public boolean updateBasicInfoService(BasicInfo basicInfo) {
		return new BasicInfoDao().updateBasicInfo(basicInfo);
	}
}
