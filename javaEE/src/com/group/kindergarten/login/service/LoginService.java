package com.group.kindergarten.login.service;

import com.group.kindergarten.login.dao.LoginDao;

public class LoginService {
	private static LoginService loginService;
	private static LoginDao loginDao;

	/**
	 * 得到一个parentService实例
	 * 
	 * @return
	 */
	public static LoginService getInstance() {
		if (null == loginService) {
			loginService = new LoginService();
		}
		if (null == loginDao) {
			loginDao = LoginDao.getInstance();
		}
		return loginService;
	}
	
	/**
	 * 判断用户名是否存在
	 * 
	 * @param userName 用户名
	 * @return 是否存在
	 */
	public boolean isExist(String userName) {
		boolean b=loginDao.isExist(userName);
				
		return b;
	}

	/**
	 * 判断用户名和密码是否匹配 即用户是否存在
	 * 
	 * @param userName
	 * @param userPwd
	 * @return
	 */
	public boolean isExistUser(String userName, String userPwd) {
		boolean b = loginDao.isExistUser(userName, userPwd);
		
		return b;
	}
	
	/**
	 * 注册用户
	 * @param userName
	 * @param password
	 * @return
	 */
	public boolean registerUser(String userName,String password) {
		boolean b=loginDao.registerUser(userName, password);
		
		return b;
	}

	public String searchPwd(String userName) {
		String pwd=LoginDao.searchPwd(userName);
		return pwd;
	}

	public boolean updatePwd(String userName,String password) {
		boolean b=loginDao.updatePwd(userName,password);
		
		return b;
	}
}
