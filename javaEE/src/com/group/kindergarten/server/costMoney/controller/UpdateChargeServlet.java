package com.group.kindergarten.server.costMoney.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.group.kindergarten.costMoney.entity.Charge;
import com.group.kindergarten.costMoney.service.CostMoneyService;
import com.group.kindergarten.schoolInfo.entity.Picture;
import com.group.kindergarten.schoolInfo.service.PictureServce;

/**
 * Servlet implementation class UpdateChargeServlet
 */
@WebServlet("/UpdateChargeServlet")
public class UpdateChargeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateChargeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String chargeId = request.getParameter("id");
		if(chargeId != null && !chargeId.equals("")) {
			int id = Integer.parseInt(chargeId);
			CostMoneyService costMoneyService = new CostMoneyService();
			Charge charge = costMoneyService.findChargeById(id);
			request.setAttribute("id", charge.getId());
			request.setAttribute("babyClass", charge.getBabyClass());	
			request.setAttribute("teacher", charge.getTeacher());	
			request.setAttribute("weChat", charge.getWeChat());
			request.setAttribute("alipay", charge.getAlipay());
			request.getRequestDispatcher("updateCharge.jsp").forward(request, response);
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
