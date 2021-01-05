package com.group.kindergarten.server.teacher.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.group.kindergarten.teacher.entity.Teacher;
import com.group.kindergarten.teacher.service.TeacherServiceImpl;
import com.group.kindergarten.util.Page;

/**
 * Servlet implementation class SearchTeacherServlet
 */
@WebServlet("/searchTeacherByName")
public class SearchTeacherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchTeacherServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("teacherName");
		if(name != null && !name.equals("")) {
			String page1 = request.getParameter("page");
			int pageNum = 1, pageSize = 10;
			if (page1 != null && !page1.equals("")) {
				pageNum = Integer.parseInt(page1);
			}
			TeacherServiceImpl teacherServiceImpl = new TeacherServiceImpl();
			Page<Teacher> page = teacherServiceImpl.listByPageAndName(pageNum, pageSize,name);
			request.setAttribute("name", name);
			request.setAttribute("page", page);
			request.getRequestDispatcher("teacherSearchResult.jsp").forward(request, response);
		}else {
			response.sendRedirect("teacherManage");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
