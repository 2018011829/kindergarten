package com.group.kindergarten.parent.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.group.kindergarten.my.entity.Child;
import com.group.kindergarten.my.service.ChildrenService;
import com.group.kindergarten.parent.entity.UserParent;
import com.group.kindergarten.parent.service.UserParentService;

/**
 * Servlet implementation class GetUserMsgServlet
 */
@WebServlet("/GetUserParentMsgServlet")
public class GetUserParentMsgServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetUserParentMsgServlet() {
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
		System.out.println("手机号" + phone);

		String json = UserParentService.getInstance().getOneParentInfo(phone);
		if (json != null) {
			response.getWriter().write(json);
			System.out.println("用户信息"+json);

		} else {
			response.getWriter().write("查询用户信息出错");
			System.out.println("查询用户信息出错");
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
