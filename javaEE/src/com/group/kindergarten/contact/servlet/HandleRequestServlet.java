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
 * Servlet implementation class HandleRequestServlet
 */
@WebServlet("/HandleRequestServlet")
public class HandleRequestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HandleRequestServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取参数 phone id status remark
		String phone = request.getParameter("phone");// 请求者的
		int respId = Integer.valueOf(request.getParameter("resp_user"));
		int status = Integer.valueOf(request.getParameter("status"));
		String remark = request.getParameter("remark");

		String json = UserService.getInstance().getOneUserInfo(phone);
		User user = new Gson().fromJson(json, User.class);
		int reqId = user.getId();
		if (RequestService.getInstance().handleRequest(reqId, respId, status, remark)) {
			System.out.println("处理请求成功 ");
			response.getWriter().write("处理请求成功");
		} else {
			System.out.println("处理请求失败");
			response.getWriter().write("处理请求失败");
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
