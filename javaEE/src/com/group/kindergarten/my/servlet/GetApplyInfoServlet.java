package com.group.kindergarten.my.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.group.kindergarten.applyinfo.entity.ApplyInfo;
import com.group.kindergarten.applyinfo.service.EnterService;

/**
 * Servlet implementation class GetApplyInfoServlet
 */
@WebServlet("/GetApplyInfo")
public class GetApplyInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetApplyInfoServlet() {
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
		System.out.println("GetApplyInfoServlet");
		String applyId = request.getParameter("applyId");
		// ����applyId���ұ�����Ϣ
		if (applyId != null && !applyId.equals("")) {
			int id=Integer.parseInt(applyId);
			EnterService enterService = EnterService.getInstance();
			ApplyInfo applyInfo=enterService.searchChildById(id);
			if (applyInfo != null) {
				Gson gson = new Gson();
				String gsonStr = gson.toJson(applyInfo);
				System.out.println(gsonStr);
				response.getWriter().write(gsonStr);
			} else {
				System.out.println("δ�ҵ���ر�����Ϣ");
				response.getWriter().write("δ�ҵ���ر�����Ϣ");
			}
		} else {
			System.out.println("applyIdΪ��");
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
