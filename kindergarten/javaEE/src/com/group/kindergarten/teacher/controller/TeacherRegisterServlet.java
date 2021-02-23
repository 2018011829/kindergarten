package com.group.kindergarten.teacher.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.group.kindergarten.teacher.service.UserTeacherService;

/**
 * Servlet implementation class TeacherRegisterServlet
 */
@WebServlet("/TeacherRegisterServlet")
public class TeacherRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TeacherRegisterServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// �õ��ֻ����롢�ǳơ��������ע��
		String phone = request.getParameter("phone");
		String nickname = request.getParameter("nickname");
		String password = request.getParameter("password");
		System.out.println("1.��ȡ��ز���");
		UserTeacherService teacherService = UserTeacherService.getInstance();
		if (!teacherService.isExistPhone(phone)) {
			boolean b = teacherService.resigter(phone, nickname, password);
			if (b) {
				response.getWriter().write("success");
				System.out.println("ע��ɹ���" + phone);
			} else {
				response.getWriter().write("�ֻ���ע��ʧ�ܣ�");
				System.out.println("ע��ʧ�ܣ�" + phone);
			}
		} else {
			response.getWriter().write("�ֻ�����ע�ᣡ");
			System.out.println("ע��ʧ�ܣ�" + phone);
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
