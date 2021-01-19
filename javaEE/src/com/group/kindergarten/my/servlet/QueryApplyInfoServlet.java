package com.group.kindergarten.my.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.group.kindergarten.applyinfo.entity.ApplyInfo;
import com.group.kindergarten.applyinfo.service.EnterService;

/**
 * Servlet implementation class QueryApplyInfoServlet
 */
@WebServlet("/QueryApplyInfoServlet")
public class QueryApplyInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public QueryApplyInfoServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 设置编码
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		System.out.println("QueryApplyInfoServlet");
		String phone = request.getParameter("phone");
		//根据手机号查找报名信息
		if(phone!=null && !phone.equals("")) {
			EnterService enterService = EnterService.getInstance();
			List<ApplyInfo> list=enterService.searchChildByPhoneNum(phone);
			if(list!=null && list.size()!=0) {
				Gson gson=new Gson();
				String gsonStr=gson.toJson(list);
				System.out.println(gsonStr);
				response.getWriter().write(gsonStr);
			}else {
				System.out.println("未找到相关报名信息");
				response.getWriter().write("未找到相关报名信息");
			}
		}else {
			System.out.println("手机号为空");
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
