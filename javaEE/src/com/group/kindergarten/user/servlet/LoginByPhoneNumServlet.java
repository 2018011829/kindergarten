package com.group.kindergarten.user.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.group.kindergarten.user.service.UserService;

/**
 * Servlet implementation class LoginByPhoneNumServlet
 */
@WebServlet("/LoginByPhoneNumServlet")
public class LoginByPhoneNumServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginByPhoneNumServlet() {
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
		System.out.println(phone);
		// 查找手机号是否已经注册
		UserService userService = UserService.getInstance();
		boolean b = userService.isExistPhone(phone);
		if (b) {
			response.getWriter().write("success");
			System.out.println("手机号已经注册：" + phone);
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
