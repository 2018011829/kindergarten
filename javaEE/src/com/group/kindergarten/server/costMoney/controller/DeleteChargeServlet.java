package com.group.kindergarten.server.costMoney.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.group.kindergarten.costMoney.service.CostMoneyService;
import com.group.kindergarten.schoolInfo.service.PictureServce;

/**
 * Servlet implementation class DeleteCharge
 */
@WebServlet("/DeleteChargeServlet")
public class DeleteChargeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteChargeServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String chargeId = request.getParameter("id");
		String url = request.getRealPath("/") + "imgs\\charge\\";
		System.out.println(url);
		if (chargeId != null && !chargeId.equals("")) {
			int id = Integer.parseInt(chargeId);
			CostMoneyService costMoneyService = new CostMoneyService();
			boolean flag = costMoneyService.deleteCharge(id, url);
			if (flag) {
				response.sendRedirect("ChargeManagerServlet");
			} else {
				System.out.println("É¾³ýÊ§°Ü£¡");
			}
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
