package com.group.kindergarten.server.schoolInfo.controller.Phone;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.group.kindergarten.schoolInfo.service.DescriptionService;
import com.group.kindergarten.schoolInfo.service.PhoneService;

/**
 * Servlet implementation class UploadPhone
 */
@WebServlet("/UploadPhone")
public class UploadPhone extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UploadPhone() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// …Ë÷√±‡¬Î∑Ω Ω
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String id = request.getParameter("phoneId");
		String phone = request.getParameter("phone");
		System.out.println(id + " " + phone);
		PhoneService phoneService = new PhoneService();
		boolean flag = phoneService .updatePhone(Integer.parseInt(id), phone);
		if (flag) {
			response.sendRedirect("PhoneManageServlet");
		} else {
			response.sendRedirect("UpdatePhone");
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
