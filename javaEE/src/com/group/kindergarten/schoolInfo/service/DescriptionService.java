package com.group.kindergarten.schoolInfo.service;

import java.util.List;

import com.group.kindergarten.schoolInfo.dao.DescriptionDao;
import com.group.kindergarten.schoolInfo.entity.Description;
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
	/**
	 * 根据页数和搜索条件查找描述信息
	 * @param pageNum
	 * @param pageSize
	 * @param name
	 * @return
	 */
	public Page<Description> listByPageAndSearchInfo(int pageNum, int pageSize, String searchInfo){
		Page<Description> page = new Page<Description>(pageNum, pageSize);
		DescriptionDao descriptionDao = new DescriptionDao();
		int count = descriptionDao.countByPageAndSearchInfo(searchInfo);
		List<Description> list = descriptionDao.findByPageAndSearchInfo(pageNum,pageSize,searchInfo);
		page.setList(list);
		page.setTotalCount(count);
		return page;
	}
	/**
	 * 根据id获取描述信息
	 * @param id
	 * @return
	 */
	public Description findDescriptionByIdService(int id) {
		return new DescriptionDao().findDescriptionById(id);
	}
	/**
	 * 根据id修改描述
	 * 
	 * @param id
	 * @return
	 */
	public boolean updateDescription(int id,String description) {
		return new DescriptionDao().updateDescription(id,description);
	}
}
