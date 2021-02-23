package com.group.kindergarten.server.teacher.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.group.kindergarten.teacher.service.TeacherServiceImpl;

/**
 * Servlet implementation class DeleteTeacherServlet
 */
@WebServlet("/deleteTeacher")
public class DeleteTeacherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteTeacherServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String teacherId = request.getParameter("id");
		if(teacherId != null && !teacherId.equals("")) {
			int id = Integer.parseInt(teacherId);
			TeacherServiceImpl teacherServiceImpl = new TeacherServiceImpl();
			boolean flag = teacherServiceImpl.deleteTeacherService(id);
			if(flag) {
				response.sendRedirect("teacherManage");
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
