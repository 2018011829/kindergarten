package com.group.kindergarten.server.schoolInfo.controller.BasicInfo;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.group.kindergarten.schoolInfo.entity.BasicInfo;
import com.group.kindergarten.schoolInfo.service.BasicInfoService;

/**
 * Servlet implementation class UpdateBasicInfo
 */
@WebServlet("/UpdateBasicInfo")
public class UpdateBasicInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateBasicInfo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String basicInfoId = request.getParameter("id");
		if(basicInfoId != null && !basicInfoId.equals("")) {
			int id = Integer.parseInt(basicInfoId);
			BasicInfoService basicInfoService = new BasicInfoService();
			BasicInfo basicInfo = basicInfoService.findBasicInfoByIdService(id);
			request.setAttribute("id", basicInfo.getId());
			request.setAttribute("introduceFile", basicInfo.getIntroduceFile());	
			request.setAttribute("address", basicInfo.getAddress());
			System.out.println(id+"+"+basicInfo.getIntroduceFile()+"+"+basicInfo.getAddress());
			request.getRequestDispatcher("updateBasicInformation.jsp").forward(request, response);
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
