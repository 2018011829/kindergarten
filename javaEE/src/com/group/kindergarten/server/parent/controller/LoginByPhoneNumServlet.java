package com.group.kindergarten.server.parent.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.group.kindergarten.parent.service.ParentService;

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
		// ���ñ��뷽ʽ
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		// �õ��ֻ�����
		String phone = request.getParameter("phone");
		System.out.println(phone);
		// �����ֻ����Ƿ��Ѿ�ע��
		ParentService parentService = ParentService.getInstance();
		boolean b = parentService.isExistPhone(phone);
		if (b) {
			response.getWriter().write("success");
			System.out.println("�ֻ����Ѿ�ע�᣺" + phone);
		} else {
			response.getWriter().write("faliure");
			System.out.println("�ֻ��Ż�δע�᣺" + phone);
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