package com.group.kindergarten.costMoney.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.group.kindergarten.costMoney.service.CostMoneyService;
import com.group.kindergarten.my.service.ChildrenService;

/**
 * Servlet implementation class AskForLeaveServlet
 */
@WebServlet("/AskForLeaveServlet")
public class AskForLeaveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AskForLeaveServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ���ñ���
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		System.out.println("AskForLeaveServlet");
		// ��ȡ������Ϣ
		String stuName=request.getParameter("stuName");
		String phone=request.getParameter("phone");
		String dayNumStr=request.getParameter("dayNum");
		// �ж���Ϣ�Ƿ�Ϊ��
		if(stuName!=null && !stuName.equals("")) {
			// ����ѧ�������Ƿ����
			boolean a=CostMoneyService.getInstance().isExistChildName(stuName);
			if(a) {
				if(phone!=null && !phone.equals("")) {
					// ���Ҹ�ĸ��ϵ��ʽ��ѧ�������Ƿ����
					boolean b=ChildrenService.getInstance().searchChild(stuName, phone);
					if(b) {
						if(dayNumStr!=null && !dayNumStr.equals("")) {
							// �ҵ��ú��ӵ�id
							int id=CostMoneyService.getInstance().returnChildId(stuName, phone);
							int dayNum=Integer.parseInt(dayNumStr);
							// ���¸ú��ӵĵ�����Ϣ
							boolean c=CostMoneyService.getInstance().updateLeaveInfo(id, phone, dayNum);
							if(c) {
								request.getRequestDispatcher("success.jsp").forward(request, response);
							}else {// ����ʧ��
								request.setAttribute("stuName", stuName);
								request.setAttribute("phone", phone);
								request.setAttribute("dayNum", dayNumStr);
								request.setAttribute("errorInfo", "�������");
								request.getRequestDispatcher("inputAskForLeave.jsp").forward(request, response);
							}
						}else {
							request.setAttribute("stuName", stuName);
							request.setAttribute("phone", phone);
							request.setAttribute("dayNum", dayNumStr);
							request.setAttribute("errorInfo", "�����������Ϊ�գ�");
							request.getRequestDispatcher("inputAskForLeave.jsp").forward(request, response);
						}
					}else {// ����ϵ��ʽ���Ǹú��Ӹ�ĸ��
						request.setAttribute("stuName", stuName);
						request.setAttribute("phone", phone);
						request.setAttribute("dayNum", dayNumStr);
						request.setAttribute("errorInfo", "����ϵ��ʽ���Ǹú��Ӹ�ĸ�ģ�");
						request.getRequestDispatcher("inputAskForLeave.jsp").forward(request, response);
					}
				}else {
					request.setAttribute("stuName", stuName);
					request.setAttribute("phone", phone);
					request.setAttribute("dayNum", dayNumStr);
					request.setAttribute("errorInfo", "�ҳ���ϵ��ʽ����Ϊ�գ�");
					request.getRequestDispatcher("inputAskForLeave.jsp").forward(request, response);
				}
			}else {// ��������������
				request.setAttribute("stuName", stuName);
				request.setAttribute("phone", phone);
				request.setAttribute("dayNum", dayNumStr);
				request.setAttribute("errorInfo", "ѧ�����������ڣ�");
				request.getRequestDispatcher("inputAskForLeave.jsp").forward(request, response);
			}
		}else {
			request.setAttribute("stuName", stuName);
			request.setAttribute("phone", phone);
			request.setAttribute("dayNum", dayNumStr);
			request.setAttribute("errorInfo", "ѧ��������Ϣ����Ϊ�գ�");
			request.getRequestDispatcher("inputAskForLeave.jsp").forward(request, response);
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
