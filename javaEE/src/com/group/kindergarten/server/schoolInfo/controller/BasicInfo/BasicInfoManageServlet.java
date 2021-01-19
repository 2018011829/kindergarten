package com.group.kindergarten.server.schoolInfo.controller.BasicInfo;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.group.kindergarten.schoolInfo.entity.BasicInfo;
import com.group.kindergarten.schoolInfo.entity.Description;
import com.group.kindergarten.schoolInfo.service.BasicInfoService;
import com.group.kindergarten.schoolInfo.service.DescriptionService;
import com.group.kindergarten.util.Page;

/**
 * Servlet implementation class BasicInfoServlet
 */
@WebServlet("/BasicInfoServlet")
public class BasicInfoManageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BasicInfoManageServlet() {
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
		BasicInfoService basicInfoService = new BasicInfoService();
		Page<BasicInfo> page = basicInfoService.listByPage(pageNum, pageSize);
		
		request.setAttribute("page", page);
		request.getRequestDispatcher("basicInformation.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
