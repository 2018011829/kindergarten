package com.group.kindergarten.school.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.group.kindergarten.school.entity.SchoolOutside;
import com.group.kindergarten.school.service.SchoolService;

/**
 * Servlet implementation class InitDataAboutSchoolGridView
 */
@WebServlet("/InitDataAboutSchoolGridView")
public class InitDataAboutSchoolGridView extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InitDataAboutSchoolGridView() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ���뷽ʽ
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		// ��ȡѧУ��GridView���������Ӧ��ͼƬ
		SchoolOutside schoolOutside=SchoolService.getInstance().getSchoolGridView();
		if(schoolOutside!=null) {
			Gson gson = new Gson();
			String gsonStr = gson.toJson(schoolOutside);
			System.out.println(gsonStr);
			response.getWriter().write(gsonStr);
		}else {
			System.out.println("��ȡ����GridView������ϢΪ�գ�");
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