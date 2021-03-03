package com.group.kindergarten.my.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.group.kindergarten.my.service.ChildrenService;

/**
 * Servlet implementation class SearchClassInfoServlet
 */
@WebServlet("/SearchClassInfoServlet")
public class SearchClassInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchClassInfoServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ���ñ���
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		//�����ݿ��л�ȡ�༶��Ϣ
		List<String> list=ChildrenService.getInstance().searchClassInfo();
		//����Ϣ���͸������
		if(list!=null && list.size()!=0) {
			String gsonStr=new Gson().toJson(list);
			response.getWriter().write(gsonStr);
		}else {
			System.out.println("�༶��ϢΪ�գ�");
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
