package com.group.kindergarten.costMoney.servlet;

import java.io.IOException;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.group.kindergarten.costMoney.dao.CostMoneyDao;
import com.group.kindergarten.costMoney.entity.LeaveInfo;

/**
 * Servlet implementation class GetLeaveDayAndShouldDay
 */
@WebServlet("/GetLeaveDayAndShouldDay")
public class GetLeaveDayAndShouldDay extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetLeaveDayAndShouldDay() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * ��ͻ��˷���ĳ�����ϸ��µ����������Ӧ��������
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ���ñ���
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		System.out.println("GetLeaveDayAndShouldDay");
		// ��ȡ������Ϣ
		String stuName = request.getParameter("stuName");
		String phone = request.getParameter("phone");
		// �����ݿ��л�ȡĳ�����ӵ����������Ӧ��������
		if(stuName!=null && !stuName.equals("")) {
			if(phone!=null && !phone.equals("")) {
				int month=0;
				//��ȡ��ǰʱ��
				Calendar c = Calendar.getInstance();
			    //��ȡ�ϸ����·� ��0��ʼ��0-11
				if(c.get(Calendar.MONTH)==0) {
					month=12;
				}else {
					month=c.get(Calendar.MONTH);
				}
				LeaveInfo leave=new LeaveInfo();
				leave.setLeaveDay(CostMoneyDao.getInstance().getPreMonthLeave(stuName, phone));
				leave.setMonthDay(CostMoneyDao.getInstance().getOneMonthAboutDayNum(month));
				Gson gson = new Gson();
				String gsonStr = gson.toJson(leave);
				System.out.println(gsonStr);
				response.getWriter().write(gsonStr);
			}else {
				System.out.println("�ֻ���Ϊ��");
			}
		}else {
			System.out.println("ѧ��������Ϊ��");
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
