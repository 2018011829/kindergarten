package com.group.kindergarten.schoolInfo.service;

import java.util.List;

import com.group.kindergarten.schoolInfo.dao.DescriptionDao;
import com.group.kindergarten.schoolInfo.dao.PictureDao;
import com.group.kindergarten.schoolInfo.entity.Description;
import com.group.kindergarten.schoolInfo.entity.Picture;
import com.group.kindergarten.teacher.dao.TeacherDaoImpl;
import com.group.kindergarten.teacher.entity.Teacher;
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
	/**
	 * 根据id删除描述
	 * @param id
	 * @return
	 */
	public boolean deletePictureService(int id,String url) {
		return new PictureDao().deletePicture(id,url);
	}
	/**
	 * 根据页数和搜索条件查找描述信息
	 * @param pageNum
	 * @param pageSize
	 * @param name
	 * @return
	 */
	public Page<Picture> listByPageAndSearchInfo(int pageNum, int pageSize, String searchInfo){
		Page<Picture> page = new Page<Picture>(pageNum, pageSize);
		PictureDao pictureDao = new PictureDao();
		int count = pictureDao.countByPageAndSearchInfo(searchInfo);
		List<Picture> list = pictureDao.findByPageAndSearchInfo(pageNum,pageSize,searchInfo);
		page.setList(list);
		page.setTotalCount(count);
		return page;
	}
	/**
	 * 新增描述图片
	 * @param teacher
	 * @return
	 */
	public boolean addPictureService(Picture picture) {
		return new PictureDao().addPicture(picture);
	}
	/**
	 * 根据id获取图片信息
	 * @param id
	 * @return
	 */
	public Picture findPictureByIdService(int id) {
		return new PictureDao().findPictureById(id);
	}

	/**
	 * 修改教师信息
	 * @return
	 */
	public boolean updatePictureService(Picture picture,String pictureLast) {
		return new PictureDao().updatePicture(picture,pictureLast);
	}
	

}
