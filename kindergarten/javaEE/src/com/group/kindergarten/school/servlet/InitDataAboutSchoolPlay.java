package com.group.kindergarten.school.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.group.kindergarten.school.entity.ThreePicture;
import com.group.kindergarten.school.service.SchoolService;

/**
 * Servlet implementation class InitDataAboutSchoolPlay
 */
@WebServlet("/InitDataAboutSchoolPlay")
public class InitDataAboutSchoolPlay extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InitDataAboutSchoolPlay() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 编码方式
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		// 获取学校的游乐设施的描述与对应的图片
		ThreePicture threePicture = SchoolService.getInstance().getSchoolPlay();
		if (threePicture != null) {
			Gson gson = new Gson();
			String gsonStr = gson.toJson(threePicture);
			System.out.println(gsonStr);
			response.getWriter().write(gsonStr);
		} else {
			System.out.println("获取到的游乐设施环境信息为空！");
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
