package com.group.kindergarten.server.schoolInfo.controller.Description;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.group.kindergarten.schoolInfo.service.DescriptionService;
import com.group.kindergarten.teacher.entity.Teacher;
import com.group.kindergarten.teacher.service.TeacherServiceImpl;

/**
 * Servlet implementation class UploadUodate
 */
@WebServlet("/UploadDescription")
public class UploadDescription extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UploadDescription() {
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
		String id = request.getParameter("id");
		String description = request.getParameter("description");
		System.out.println(id + " " + description);
		DescriptionService descriptionService = new DescriptionService();
		boolean flag = descriptionService.updateDescription(Integer.parseInt(id), description);
		if (flag) {
			response.sendRedirect("DescriptionManageServlet");
		} else {
			response.sendRedirect("UpdateDescription");
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
