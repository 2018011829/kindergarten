package com.group.kindergarten.server.parent.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.group.kindergarten.parent.service.ParentService;


/**
 * Servlet implementation class ParentRegisterServlet
 */
@WebServlet("/ParentRegisterServlet")
public class ParentRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ParentRegisterServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 得到手机号码、昵称、密码进行注册
		String phone = request.getParameter("phone");
		String nickname = request.getParameter("nickname");
		String password = request.getParameter("password");
		System.out.println("1.获取相关参数");
		ParentService parentService = ParentService.getInstance();
		if (!parentService.isExistPhone(phone)) {
			boolean b = parentService.resigter(phone, nickname, password);
			if (b) {
				response.getWriter().write("success");
				System.out.println("注册成功：" + phone);
			} else {
				response.getWriter().write("手机号注册失败！");
				System.out.println("注册失败：" + phone);
			}
		} else {
			response.getWriter().write("手机号已注册！");
			System.out.println("注册失败：" + phone);
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
