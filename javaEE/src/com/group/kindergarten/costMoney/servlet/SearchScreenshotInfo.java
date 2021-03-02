package com.group.kindergarten.costMoney.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.group.kindergarten.costMoney.entity.ScreenshotInfo;
import com.group.kindergarten.costMoney.service.CostMoneyService;

/**
 * Servlet implementation class SearchScreenshotInfo
 */
@WebServlet("/SearchScreenshotInfo")
public class SearchScreenshotInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchScreenshotInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 从数据库中获取本月所有的缴费截图信息，并返回给jsp页面
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("SearchScreenshotInfo");
		// 获取当前月份
		int month = 0;
		Calendar c = Calendar.getInstance();// 0-11
		month = c.get(Calendar.MONTH) + 1;
		System.out.println("本月月份："+month);
		List<ScreenshotInfo> list = CostMoneyService.getInstance().searchScreenshotInfo(month);
		if (list != null && list.size() != 0) {
			request.setAttribute("ScreenshotList", list);
			request.getRequestDispatcher("showScreenshotInfo.jsp").forward(request, response);
		} else {
			System.out.println("未查询到相关数据");
			request.setAttribute("errorInfo", "未查询到相关数据");
			request.getRequestDispatcher("showErrorInfo.jsp").forward(request, response);
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
