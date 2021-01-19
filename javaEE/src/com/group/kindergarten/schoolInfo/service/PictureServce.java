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
	 * ��ҳ��ѯ������ͼƬ��Ϣ
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
	 * ����idɾ������
	 * @param id
	 * @return
	 */
	public boolean deletePictureService(int id,String url) {
		return new PictureDao().deletePicture(id,url);
	}
	/**
	 * ����ҳ����������������������Ϣ
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
	 * ��������ͼƬ
	 * @param teacher
	 * @return
	 */
	public boolean addPictureService(Picture picture) {
		return new PictureDao().addPicture(picture);
	}
	/**
	 * ����id��ȡͼƬ��Ϣ
	 * @param id
	 * @return
	 */
	public Picture findPictureByIdService(int id) {
		return new PictureDao().findPictureById(id);
	}

	/**
	 * �޸Ľ�ʦ��Ϣ
	 * @return
	 */
	public boolean updatePictureService(Picture picture,String pictureLast) {
		return new PictureDao().updatePicture(picture,pictureLast);
	}
	

}
