package com.group.kindergarten.user.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.group.kindergarten.teacher.entity.Teacher;
import com.group.kindergarten.teacher.service.TeacherServiceImpl;
import com.group.kindergarten.user.service.UserService;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegisterServlet() {
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
		String identity = request.getParameter("identity");
		if (identity.equals("1")) {
			// �ж��Ƿ���ڴ˽�ʦ
			TeacherServiceImpl teacherServiceImpl = new TeacherServiceImpl();
			List<Teacher> teachers = teacherServiceImpl.findTeachersService();
			boolean b0 = false;
			for (Teacher teacher : teachers) {
				if (teacher.getPhone().equals(phone)) {
					b0 = true;
				}
			}
			if (!b0) {
				response.getWriter().write("û�д˽�ʦ��");
			}

		}
		System.out.println("1.��ȡ��ز���");
		UserService userService = UserService.getInstance();
		if (!userService.isExistPhone(phone)) {
			boolean b = userService.resigter(phone, nickname, password,identity);
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
