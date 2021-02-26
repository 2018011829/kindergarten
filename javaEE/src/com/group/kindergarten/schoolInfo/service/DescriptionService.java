package com.group.kindergarten.schoolInfo.service;

import java.util.List;

import com.group.kindergarten.schoolInfo.dao.DescriptionDao;
import com.group.kindergarten.schoolInfo.entity.Description;
import com.group.kindergarten.util.Page;

public class DescriptionService {
	/**
	 * ��ҳ��ѯУ԰��Ϣ���
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
	 * ����idɾ������
	 * @param id
	 * @return
	 */
	public boolean deleteDescriptionService(int id) {
		return new DescriptionDao().deleteDescription(id);
	}
	/**
	 * ����ҳ����������������������Ϣ
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
	 * ����id��ȡ������Ϣ
	 * @param id
	 * @return
	 */
	public Description findDescriptionByIdService(int id) {
		return new DescriptionDao().findDescriptionById(id);
	}
	/**
	 * ����id�޸�����
	 * 
	 * @param id
	 * @return
	 */
	public boolean updateDescription(int id,String description) {
		return new DescriptionDao().updateDescription(id,description);
	}
}
