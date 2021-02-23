package com.group.kindergarten.server.schoolInfo.controller.Description;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.group.kindergarten.schoolInfo.service.DescriptionService;
import com.group.kindergarten.teacher.service.TeacherServiceImpl;

/**
 * Servlet implementation class DeleteDescriptionServlet
 */
@WebServlet("/DeleteDescriptionServlet")
public class DeleteDescriptionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteDescriptionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String descriptionId = request.getParameter("id");
		if(descriptionId != null && !descriptionId.equals("")) {
			int id = Integer.parseInt(descriptionId);
			DescriptionService descriptionService = new DescriptionService();
			boolean flag = descriptionService.deleteDescriptionService(id);
			if(flag) {
				response.sendRedirect("DescriptionManageServlet");
			}else {
				System.out.println("É¾³ýÊ§°Ü£¡");
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
