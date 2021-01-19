package com.group.kindergarten.school.service;

import com.group.kindergarten.school.dao.SchoolDao;
import com.group.kindergarten.school.entity.SchoolOutside;
import com.group.kindergarten.school.entity.ThreePicture;
import com.group.kindergarten.school.entity.TwoPicture;

public class SchoolService {

	private static SchoolService schoolService;
	private static SchoolDao schoolDao;
	
	/**
	 * 得到一个SchoolService实例
	 * 
	 * @return SchoolService
	 */
	public static SchoolService getInstance() {
		if (null == schoolService) {
			schoolService = new SchoolService();
		}
		if (null == schoolDao) {
			schoolDao = SchoolDao.getInstance();
		}
		return schoolService;
	}
	
	/**
	 * 查找保存幼儿园简介信息的文件名称
	 * @return
	 */
	public String searchIntroduceTextFileName() {
		String fileName=schoolDao.searchIntroduceTextFileName();
		
		return fileName;
	}
	
	/**
	 * 获取学校的外部环境的相关信息
	 * @return
	 */
	public SchoolOutside getSchoolOutside() {
		SchoolOutside schoolOutside=schoolDao.getSchoolOutside();
		
		return schoolOutside;
	}
	
	/**
	 * 获取学校的GridView环境的相关信息
	 * @return
	 */
	public SchoolOutside getSchoolGridView() {
		SchoolOutside schoolOutside=schoolDao.getSchoolGridView();

		return schoolOutside;
	}
	
	/**
	 * 获取学校的区角环境的相关信息
	 * @return
	 */
	public SchoolOutside getSchoolAngleBanner() {
		SchoolOutside schoolOutside=schoolDao.getSchoolAngleBanner();
		
		return schoolOutside;
	}
	
	/**
	 * 获取学校的楼道环境的相关信息
	 * @return
	 */
	public TwoPicture getSchoolPassageway() {
		TwoPicture twoPicture=schoolDao.getSchoolPassageway();

		return twoPicture;
	}
	
	/**
	 * 获取学校的内部环境的相关信息
	 * @return
	 */
	public TwoPicture getSchoolInside() {
		TwoPicture twoPicture=schoolDao.getSchoolInside();

		return twoPicture;
	}
	
	/**
	 * 获取学校的游乐设施环境的相关信息
	 * @return
	 */
	public ThreePicture getSchoolPlay() {
		ThreePicture threePicture=schoolDao.getSchoolPlay();
		
		return threePicture;
	}
}
