package com.group.kindergarten.teacher.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.group.kindergarten.teacher.service.UserTeacherService;

/**
 * Servlet implementation class TeacherLoginByPhoneAndPwdServlet
 */
@WebServlet("/TeacherLoginByPhoneAndPwdServlet")
public class TeacherLoginByPhoneAndPwdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TeacherLoginByPhoneAndPwdServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// �õ��ֻ�����
		String phone = request.getParameter("phone");
		String password = request.getParameter("password");
		// �����ֻ����Ƿ��Ѿ�ע��
		UserTeacherService teacherService = UserTeacherService.getInstance();
		boolean b = teacherService.isExistPhone(phone);
		if (b) {
			// �ж��û������Ƿ���ȷ
			boolean a = new UserTeacherService().isExistUser(phone, password);
			if (a) {
				response.getWriter().write("success");
				System.out.println("�ֻ����Ѿ�ע�᣺" + phone);
			} else {
				response.getWriter().write("�������");
				System.out.println("�������" + password);
			}
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
