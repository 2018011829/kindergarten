package com.group.kindergarten.contact.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.group.kindergarten.contact.service.RemarkService;

/**
 * Servlet implementation class DeleteFriendServlet
 */
@WebServlet("/DeleteFriendServlet")
public class DeleteFriendServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteFriendServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ��ȡ���� id id
		int fromUser = Integer.valueOf(request.getParameter("fromUser"));// �����ߵ�
		int toUser = Integer.valueOf(request.getParameter("toUser"));

		if (RemarkService.getInstance().delRemark(fromUser, toUser)) {
			System.out.println("ɾ���ɹ� ");
			response.getWriter().write("ɾ���ɹ�");
		} else {
			System.out.println("ɾ��ʧ��");
			response.getWriter().write("ɾ��ʧ��");
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
