package com.group.kindergarten.server.schoolInfo.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.group.kindergarten.schoolInfo.entity.Description;
import com.group.kindergarten.schoolInfo.entity.Phone;
import com.group.kindergarten.schoolInfo.service.DescriptionService;
import com.group.kindergarten.schoolInfo.service.PhoneService;
import com.group.kindergarten.util.Page;

/**
 * Servlet implementation class PhoneManageServlet
 */
@WebServlet("/PhoneManageServlet")
public class PhoneManageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PhoneManageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String page1 = request.getParameter("page");
		int pageNum = 1, pageSize = 10;
		if (page1 != null && !page1.equals("")) {
			pageNum = Integer.parseInt(page1);
		}
		PhoneService phoneService = new PhoneService();
		Page<Phone> page = phoneService.listByPage(pageNum, pageSize);
		
		request.setAttribute("page", page);
		request.getRequestDispatcher("schoolPhone.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
