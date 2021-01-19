package com.group.kindergarten.server.schoolInfo.controller.Picture;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.group.kindergarten.schoolInfo.service.DescriptionService;
import com.group.kindergarten.schoolInfo.service.PictureServce;

/**
 * Servlet implementation class DeletePictureServlet
 */
@WebServlet("/DeletePictureServlet")
public class DeletePictureServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeletePictureServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String descriptionId = request.getParameter("id");
		String url = request.getRealPath("/")+"imgs\\schoolInfoPicture\\";
		System.out.println(url);
		if(descriptionId != null && !descriptionId.equals("")) {
			int id = Integer.parseInt(descriptionId);
			PictureServce pictureServce = new PictureServce();
			boolean flag = pictureServce.deletePictureService(id,url);
			if(flag) {
				response.sendRedirect("PictureManageServlet");
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
