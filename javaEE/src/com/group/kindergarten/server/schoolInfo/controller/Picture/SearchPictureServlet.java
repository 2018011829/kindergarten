package com.group.kindergarten.server.schoolInfo.controller.Picture;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.group.kindergarten.schoolInfo.entity.Description;
import com.group.kindergarten.schoolInfo.entity.Picture;
import com.group.kindergarten.schoolInfo.service.DescriptionService;
import com.group.kindergarten.schoolInfo.service.PictureServce;
import com.group.kindergarten.util.Page;

/**
 * Servlet implementation class SearchPictureServlet
 */
@WebServlet("/SearchPictureServlet")
public class SearchPictureServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchPictureServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String searchInfo = request.getParameter("searchInfo");
		String page1 = request.getParameter("page");
		if(searchInfo != null && !searchInfo.equals("")) {
			int pageNum = 1, pageSize = 6;
			if (page1 != null && !page1.equals("")) {
				pageNum = Integer.parseInt(page1);
			}
			PictureServce pictureServce = new PictureServce();
			Page<Picture> page = pictureServce.listByPageAndSearchInfo(pageNum, pageSize,searchInfo);			
			request.setAttribute("searchInfo",searchInfo);
			request.setAttribute("page", page);
			request.getRequestDispatcher("searchPicture.jsp").forward(request, response);
		}else {
			response.sendRedirect("PictureManageServlet");
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
