package com.group.kindergarten.schoolInfo.service;

import java.util.List;

import com.group.kindergarten.schoolInfo.dao.BasicInfoDao;
import com.group.kindergarten.schoolInfo.dao.PhoneDao;
import com.group.kindergarten.schoolInfo.entity.BasicInfo;
import com.group.kindergarten.schoolInfo.entity.Phone;
import com.group.kindergarten.util.Page;

public class PhoneService {
	/**
	 * 分页查询电话信息
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public Page<Phone> listByPage(int pageNum, int pageSize){
		Page<Phone> page = new Page<Phone>(pageNum, pageSize);
		PhoneDao phoneDao = new PhoneDao();
		int count = phoneDao.countByPage();
		List<Phone> list = phoneDao.findByPage(pageNum,pageSize);
		page.setList(list);
		page.setTotalCount(count);
		return page;
	}

}
