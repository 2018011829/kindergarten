package com.group.kindergarten.server.user.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.group.kindergarten.server.user.service.UserServiceImpl;

/**
 * Servlet implementation class DeleteTeacherServlet
 */
@WebServlet("/deleteUser")
public class DeleteUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteUserServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("id");
		if(userId != null && !userId.equals("")) {
			int id = Integer.parseInt(userId);
			UserServiceImpl userServiceImpl = new UserServiceImpl();
			boolean flag = userServiceImpl.deleteUserService(id);
			if(flag) {
				response.sendRedirect("userManage");
			}else {
				System.out.println("É¾³ýÊ§°Ü£¡");
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
