package com.group.kindergarten.costMoney.servlet;

import java.io.IOException;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.group.kindergarten.costMoney.entity.ChildConsumeInfo;
import com.group.kindergarten.costMoney.service.CostMoneyService;

/**
 * Servlet implementation class GetChildMoneyServlet
 */
@WebServlet("/GetChildMoneyServlet")
public class GetChildMoneyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetChildMoneyServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * �����˷���ĳ�����ӵĳ��ں���Ҫ���ķ���
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ���ñ���
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		System.out.println("GetChildMoneyServlet");
		// ��ȡ������Ϣ
		String childName = request.getParameter("childName");
		String phone = request.getParameter("phone");
		if(!childName.equals("") && !phone.equals("") && childName!=null && phone!=null) {
			//��ȡ�ϸ��µĳ�������
			int day=CostMoneyService.getInstance().childAttendLastMonth(childName, phone);
			//��ȡ��ǰʱ��
			int month=0;
			Calendar c = Calendar.getInstance();
		    //��ȡ���µ��·� ��0��ʼ��0-11
			month=c.get(Calendar.MONTH)+1;
			//��ȡ����Ӧ����Ǯ
			double money=CostMoneyService.getInstance().caculateNowMoney(month, childName, phone);
			ChildConsumeInfo childInfo=new ChildConsumeInfo();
			childInfo.setDay(day);
			childInfo.setMoney(money);
			Gson gson = new Gson();
			String gsonStr = gson.toJson(childInfo);
			System.out.println(gsonStr);
			response.getWriter().write(gsonStr);
		}else {
			System.out.println("�յ��ĺ�����ϢΪ��");
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
