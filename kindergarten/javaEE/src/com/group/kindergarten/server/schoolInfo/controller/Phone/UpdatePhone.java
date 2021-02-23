package com.group.kindergarten.server.schoolInfo.controller.Phone;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.group.kindergarten.schoolInfo.entity.Description;
import com.group.kindergarten.schoolInfo.entity.Phone;
import com.group.kindergarten.schoolInfo.service.DescriptionService;
import com.group.kindergarten.schoolInfo.service.PhoneService;

/**
 * Servlet implementation class UpdatePhone
 */
@WebServlet("/UpdatePhone")
public class UpdatePhone extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdatePhone() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String phoneId = request.getParameter("id");
		if(phoneId != null && !phoneId.equals("")) {
			int id = Integer.parseInt(phoneId);
			PhoneService phoneService = new PhoneService();
			Phone phone = phoneService.findPhoneByIdService(id);
			request.setAttribute("phone", phone.getPhone());
			request.setAttribute("id", phone.getId());
			request.getRequestDispatcher("updateSchoolPhone.jsp").forward(request, response);
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
