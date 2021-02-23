package com.group.kindergarten.schoolInfo.service;

import java.util.List;

import com.group.kindergarten.schoolInfo.dao.BasicInfoDao;
import com.group.kindergarten.schoolInfo.dao.DescriptionDao;
import com.group.kindergarten.schoolInfo.dao.PhoneDao;
import com.group.kindergarten.schoolInfo.entity.BasicInfo;
import com.group.kindergarten.schoolInfo.entity.Description;
import com.group.kindergarten.schoolInfo.entity.Phone;
import com.group.kindergarten.util.Page;

public class PhoneService {
	/**
	 * ��ҳ��ѯ�绰��Ϣ
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

	/**
	 * ����id��ȡ�绰
	 * @param id
	 * @return
	 */
	public Phone findPhoneByIdService(int id) {
		return new PhoneDao().findPhoneById(id);
	}
	/**
	 * ����id�޸ĵ绰
	 * 
	 * @param id
	 * @return
	 */
	public boolean updatePhone(int id,String phone) {
		return new PhoneDao().updatePhone(id,phone);
	}
}
