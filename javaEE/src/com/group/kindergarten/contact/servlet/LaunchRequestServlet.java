package com.group.kindergarten.contact.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.group.kindergarten.contact.service.RequestService;
import com.group.kindergarten.user.entity.User;
import com.group.kindergarten.user.service.UserService;

/**
 * �����������������ֻ��ţ������id�����û�id��message��remark
 */
@WebServlet("/LaunchRequestServlet")
public class LaunchRequestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LaunchRequestServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ��ȡ���� phone id
		String phone = request.getParameter("phone");
		int reqId = Integer.valueOf(request.getParameter("req_user"));
		String message = request.getParameter("message");
		String remark = request.getParameter("remark");

		String json = UserService.getInstance().getOneUserInfo(phone);
		User user = new Gson().fromJson(json, User.class);
		int respId = user.getId();
		if (RequestService.getInstance().addRequest(reqId, respId, message, remark)) {
			System.out.println("��������ɹ�");
			response.getWriter().write("��������ɹ�");
		} else {
			System.out.println("��������ʧ��");
			response.getWriter().write("��������ʧ��");
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
