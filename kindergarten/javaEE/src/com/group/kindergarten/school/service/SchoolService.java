package com.group.kindergarten.school.service;

import com.group.kindergarten.school.dao.SchoolDao;
import com.group.kindergarten.school.entity.SchoolOutside;
import com.group.kindergarten.school.entity.ThreePicture;
import com.group.kindergarten.school.entity.TwoPicture;

public class SchoolService {

	private static SchoolService schoolService;
	private static SchoolDao schoolDao;
	
	/**
	 * �õ�һ��SchoolServiceʵ��
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
	 * ���ұ����׶�԰�����Ϣ���ļ�����
	 * @return
	 */
	public String searchIntroduceTextFileName() {
		String fileName=schoolDao.searchIntroduceTextFileName();
		
		return fileName;
	}
	
	/**
	 * ��ȡѧУ���ⲿ�����������Ϣ
	 * @return
	 */
	public SchoolOutside getSchoolOutside() {
		SchoolOutside schoolOutside=schoolDao.getSchoolOutside();
		
		return schoolOutside;
	}
	
	/**
	 * ��ȡѧУ��GridView�����������Ϣ
	 * @return
	 */
	public SchoolOutside getSchoolGridView() {
		SchoolOutside schoolOutside=schoolDao.getSchoolGridView();

		return schoolOutside;
	}
	
	/**
	 * ��ȡѧУ�����ǻ����������Ϣ
	 * @return
	 */
	public SchoolOutside getSchoolAngleBanner() {
		SchoolOutside schoolOutside=schoolDao.getSchoolAngleBanner();
		
		return schoolOutside;
	}
	
	/**
	 * ��ȡѧУ��¥�������������Ϣ
	 * @return
	 */
	public TwoPicture getSchoolPassageway() {
		TwoPicture twoPicture=schoolDao.getSchoolPassageway();

		return twoPicture;
	}
	
	/**
	 * ��ȡѧУ���ڲ������������Ϣ
	 * @return
	 */
	public TwoPicture getSchoolInside() {
		TwoPicture twoPicture=schoolDao.getSchoolInside();

		return twoPicture;
	}
	
	/**
	 * ��ȡѧУ��������ʩ�����������Ϣ
	 * @return
	 */
	public ThreePicture getSchoolPlay() {
		ThreePicture threePicture=schoolDao.getSchoolPlay();
		
		return threePicture;
	}
}
