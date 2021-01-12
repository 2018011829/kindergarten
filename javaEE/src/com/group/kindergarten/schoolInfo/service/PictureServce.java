package com.group.kindergarten.schoolInfo.service;

import java.util.List;

import com.group.kindergarten.schoolInfo.dao.DescriptionDao;
import com.group.kindergarten.schoolInfo.dao.PictureDao;
import com.group.kindergarten.schoolInfo.entity.Description;
import com.group.kindergarten.schoolInfo.entity.Picture;
import com.group.kindergarten.util.Page;

public class PictureServce {
	/**
	 * 分页查询描述的图片信息
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public Page<Picture> listByPage(int pageNum, int pageSize){
		Page<Picture> page = new Page<Picture>(pageNum, pageSize);
		PictureDao pictureDao = new PictureDao();
		int count = pictureDao.countByPage();
		List<Picture> list = pictureDao.findByPage(pageNum,pageSize);
		page.setList(list);
		page.setTotalCount(count);
		return page;
	}
	

}
