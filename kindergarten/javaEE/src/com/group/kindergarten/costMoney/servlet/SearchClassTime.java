package com.group.kindergarten.costMoney.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.group.kindergarten.costMoney.entity.SchoolSemester;
import com.group.kindergarten.costMoney.service.CostMoneyService;

/**
 * Servlet implementation class SearchClassTime
 */
@WebServlet("/SearchClassTime")
public class SearchClassTime extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchClassTime() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 获取本学期的时间安排
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 设置编码
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		System.out.println("SearchClassTime");
		//查找数据库中是否进行了上课时间的规定
		boolean b=CostMoneyService.getInstance().isClassTimeExist();
		if(b) {//b为false：表示未进行时间的设置
			//从数据库中获取设置的学期信息
			List<SchoolSemester> list=CostMoneyService.getInstance().searchSemesterInfo();
			request.getSession().setAttribute("schoolSemester", list);
			request.getRequestDispatcher("showSemester.jsp").forward(request, response);
		}else {
			request.getRequestDispatcher("SetSemester.jsp").forward(request, response);
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
