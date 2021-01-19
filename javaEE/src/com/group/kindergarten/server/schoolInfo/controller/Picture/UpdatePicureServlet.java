package com.group.kindergarten.server.schoolInfo.controller.Picture;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.group.kindergarten.schoolInfo.entity.Picture;
import com.group.kindergarten.schoolInfo.service.PictureServce;

/**
 * Servlet implementation class UpdatePicureServlet
 */
@WebServlet("/UpdatePicureServlet")
public class UpdatePicureServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdatePicureServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pictureId = request.getParameter("id");
		if(pictureId != null && !pictureId.equals("")) {
			int id = Integer.parseInt(pictureId);
			PictureServce pictureServce = new PictureServce();
			Picture picture = pictureServce.findPictureByIdService(id);
			request.setAttribute("id", picture.getId());
			request.setAttribute("picture", picture.getPicture());	
			request.setAttribute("descriptionId", picture.getDescriptionId());
			request.getRequestDispatcher("updateEnvironmentPicture.jsp").forward(request, response);
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
