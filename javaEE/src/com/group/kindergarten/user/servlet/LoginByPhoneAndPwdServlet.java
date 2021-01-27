package com.group.kindergarten.user.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.group.kindergarten.user.service.UserService;

/**
 * Servlet implementation class LoginByPhoneAndPwdServlet
 */
@WebServlet("/LoginByPhoneAndPwdServlet")
public class LoginByPhoneAndPwdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginByPhoneAndPwdServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 得到手机号码
		String phone = request.getParameter("phone");
		String password = request.getParameter("password");
		
		// 查找手机号是否已经注册
		UserService userService = UserService.getInstance();
		boolean b = userService.isExistPhone(phone);
		if (b) {
			// 判断用户密码是否正确
			boolean a = new UserService().isExistUser(phone, password);
			if (a) {
				response.getWriter().write("success");
				System.out.println("手机号已经注册：" + phone);
			} else {
				response.getWriter().write("密码错误！");
				System.out.println("密码错误：" + password);
			}
		} else {
			response.getWriter().write("faliure");
			System.out.println("手机号还未注册：" + phone);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
