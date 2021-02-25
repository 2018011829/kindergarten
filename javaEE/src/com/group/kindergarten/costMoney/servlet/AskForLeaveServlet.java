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
		// 设置编码
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		System.out.println("AskForLeaveServlet");
		// 获取参数信息
		String stuName=request.getParameter("stuName");
		String phone=request.getParameter("phone");
		String dayStart=request.getParameter("dayStart");
		String dayEnd=request.getParameter("dayEnd");
		// 判断信息是否为空
		if(stuName!=null && !stuName.equals("")) {
			// 查找学生姓名是否存在
			boolean a=CostMoneyService.getInstance().isExistChildName(stuName);
			if(a) {
				if(phone!=null && !phone.equals("")) {
					// 查找父母联系方式与学生姓名是否符合
					boolean b=ChildrenService.getInstance().searchChild(stuName, phone);
					if(b) {
						if(dayStart!=null && !dayStart.equals("")) {
							// 找到该孩子的id
							int id=CostMoneyService.getInstance().returnChildId(stuName, phone);
							if(dayEnd!=null && !dayEnd.equals("")) {
								int dayStartNum=Integer.parseInt(dayStart);
								int dayEndNum=Integer.parseInt(dayEnd);
								// 更新该孩子的到课信息
								boolean c=CostMoneyService.getInstance().updateLeaveInfo(id, phone, dayStartNum, dayEndNum);
								if(c) {
									request.getRequestDispatcher("success.jsp").forward(request, response);
								}else {// 更新失败
									request.setAttribute("stuName", stuName);
									request.setAttribute("phone", phone);
									request.setAttribute("dayStart", dayStart);
									request.setAttribute("dayEnd", dayEnd);
									request.setAttribute("errorInfo", "网络错误！");
									request.getRequestDispatcher("inputAskForLeave.jsp").forward(request, response);
								}
							}else {
								request.setAttribute("stuName", stuName);
								request.setAttribute("phone", phone);
								request.setAttribute("dayStart", dayStart);
								request.setAttribute("dayEnd", dayEnd);
								request.setAttribute("errorInfo", "请假结束日期不能为空！");
								request.getRequestDispatcher("inputAskForLeave.jsp").forward(request, response);
							}
							
						}else {
							request.setAttribute("stuName", stuName);
							request.setAttribute("phone", phone);
							request.setAttribute("dayStart", dayStart);
							request.setAttribute("dayEnd", dayEnd);
							request.setAttribute("errorInfo", "请假开始日期不能为空！");
							request.getRequestDispatcher("inputAskForLeave.jsp").forward(request, response);
						}
					}else {// 该联系方式不是该孩子父母的
						request.setAttribute("stuName", stuName);
						request.setAttribute("phone", phone);
						request.setAttribute("dayStart", dayStart);
						request.setAttribute("dayEnd", dayEnd);
						request.setAttribute("errorInfo", "该联系方式不是该孩子父母的！");
						request.getRequestDispatcher("inputAskForLeave.jsp").forward(request, response);
					}
				}else {
					request.setAttribute("stuName", stuName);
					request.setAttribute("phone", phone);
					request.setAttribute("dayStart", dayStart);
					request.setAttribute("dayEnd", dayEnd);
					request.setAttribute("errorInfo", "家长联系方式不能为空！");
					request.getRequestDispatcher("inputAskForLeave.jsp").forward(request, response);
				}
			}else {// 孩子姓名不存在
				request.setAttribute("stuName", stuName);
				request.setAttribute("phone", phone);
				request.setAttribute("dayStart", dayStart);
				request.setAttribute("dayEnd", dayEnd);
				request.setAttribute("errorInfo", "学生姓名不存在！");
				request.getRequestDispatcher("inputAskForLeave.jsp").forward(request, response);
			}
		}else {
			request.setAttribute("stuName", stuName);
			request.setAttribute("phone", phone);
			request.setAttribute("dayStart", dayStart);
			request.setAttribute("dayEnd", dayEnd);
			request.setAttribute("errorInfo", "学生姓名信息不能为空！");
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
