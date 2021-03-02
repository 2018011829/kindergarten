package com.group.kindergarten.server.user.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.group.kindergarten.server.user.service.UserServiceImpl;
import com.group.kindergarten.user.entity.User;
import com.group.kindergarten.util.Page;

/**
 * Servlet implementation class SearchTeacherServlet
 */
@WebServlet("/searchUserByPhone")
public class SearchUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchUserServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String phone = request.getParameter("userPhone");
		if(phone != null && !phone.equals("")) {
			String page1 = request.getParameter("page");
			int pageNum = 1, pageSize = 10;
			if (page1 != null && !page1.equals("")) {
				pageNum = Integer.parseInt(page1);
			}
			UserServiceImpl userServiceImpl = new UserServiceImpl();
			Page<User> page = userServiceImpl.listByPhondService(pageNum, pageSize, phone);
			request.setAttribute("phone", phone);
			request.setAttribute("page", page);
			request.getRequestDispatcher("userSearchResult.jsp").forward(request, response);
		}else {
			response.sendRedirect("userManage");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
