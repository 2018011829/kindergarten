package com.group.kindergarten.user.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.group.kindergarten.user.service.UserService;

/**
 * Servlet implementation class GetUserMsgServlet
 */
@WebServlet("/GetUserMsgServlet")
public class GetUserMsgServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetUserMsgServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String phone = request.getParameter("phone");
		System.out.println("�ֻ���" + phone);

		String json = UserService.getInstance().getOneUserInfo(phone);
		if (json != null) {
			response.getWriter().write(json);
			System.out.println("�û���Ϣ" + json);

		} else {
			response.getWriter().write("��ѯ�û���Ϣ����");
			System.out.println("��ѯ�û���Ϣ����");
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
