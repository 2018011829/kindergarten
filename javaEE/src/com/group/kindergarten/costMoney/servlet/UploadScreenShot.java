package com.group.kindergarten.costMoney.servlet;

import java.io.IOException;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.group.kindergarten.costMoney.entity.MoneyPicture;
import com.group.kindergarten.costMoney.service.CostMoneyService;
import com.group.kindergarten.costMoney.util.Util;

/**
 * Servlet implementation class UploadScreenShot
 */
@WebServlet("/UploadScreenShot")
public class UploadScreenShot extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UploadScreenShot() {
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
		System.out.println("GetChildMoneyServlet");
		// 获取json参数信息
		String json = request.getParameter("content");
		//解析json串
		MoneyPicture moneyPicture=new Gson().fromJson(json, MoneyPicture.class);
		//获取当前月份
		int month=0;
		Calendar c = Calendar.getInstance();//0-11
		month=c.get(Calendar.MONTH)+1;
		//产生要保存的截图的文件名称
		String screenshotName=moneyPicture.getBabyName()+"-"+moneyPicture.getBabyGrade()+"-"+moneyPicture.getBabyClass()+"-"+moneyPicture.getPhone()+".jpg";
		Util.screenshotName=screenshotName;
		//将数据保存在数据库
		boolean b=CostMoneyService.getInstance().preserveScreenshotInfo(moneyPicture, month, screenshotName);
		//给客户端返回上传结果
		if(b) {
			response.getWriter().write("上传成功");
			System.out.println("上传成功");
		}else {
			response.getWriter().write("上传失败");
			System.out.println("上传失败");
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
