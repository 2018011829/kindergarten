package com.group.kindergarten.teacher.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.group.kindergarten.teacher.entity.Teacher;
import com.group.kindergarten.teacher.service.TeacherServiceImpl;

/**
 * Servlet implementation class GetAllTeacherMessageServlet
 */
@WebServlet("/getAllTeacherMessage.action")
public class GetAllTeacherMessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetAllTeacherMessageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("µ√µΩ«Î«Û");
//		response.getWriter().write("json");
		List<Teacher> teachers = new TeacherServiceImpl().findTeachersService();
		String json = new Gson().toJson(teachers);
		response.getWriter().write(json);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
