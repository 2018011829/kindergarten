package com.group.kindergarten.costMoney.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.group.kindergarten.costMoney.entity.Charge;
import com.group.kindergarten.costMoney.service.CostMoneyService;

/**
 * Servlet implementation class SendChargeServlet
 */
@WebServlet("/SendChargeServlet")
public class SendChargeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// 定义Gson对象属性
	private Gson gson;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SendChargeServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 初始化json
		initGson();
		List<Charge> charges = CostMoneyService.getInstance().findCharge();
		// 序列化
		String json = gson.toJson(charges);
		// 获取网络输出流，并将图片的网络资源路径返回给客户端
		System.out.println(json);
		response.getWriter().write(json);
	}

	/**
	 * 初始化Gson对象
	 */
	private void initGson() {
		gson = new GsonBuilder()// 创建GsonBuilder对象
				.setPrettyPrinting()// 格式化输出
				.serializeNulls()// 允许输出Null值属性
				.setDateFormat("YY:MM:dd")// 日期格式化
				.create();// 创建Gson对象
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
