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
import com.group.kindergarten.util.Page;

/**
 * Servlet implementation class ChargeManagerServlet
 */
@WebServlet("/ChargeManagerServlet")
public class ChargeManagerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChargeManagerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String page1 = request.getParameter("page");
		int pageNum = 1, pageSize = 7;
		if (page1 != null && !page1.equals("")) {
			pageNum = Integer.parseInt(page1);
		}
		CostMoneyService costMoneyService = new CostMoneyService();
		Page<Charge> page = costMoneyService.listByPage(pageNum, pageSize);
		
		request.setAttribute("page", page);
		request.getRequestDispatcher("chargeManage.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
