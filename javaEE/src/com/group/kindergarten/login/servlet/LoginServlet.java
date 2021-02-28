package com.group.kindergarten.login.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.group.kindergarten.login.service.LoginService;



/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		System.out.println("LoginServlet");
		// 获取表单数据
		String userName = request.getParameter("userName");
		String userPwd = request.getParameter("userPwd");
		// 判断信息是否为空
		if (userName != null && !userName.equals("")) {
			if (userPwd != null && !userPwd.equals("")) {
				// 检查用户是否存在
				LoginService loginService = LoginService.getInstance();
				boolean a = loginService.isExist(userName);
				if (a) {
					boolean b = loginService.isExistUser(userName, userPwd);
					if (b) {
						System.out.println("用户存在！");
						request.getSession().setAttribute("userName", userName);
						request.getRequestDispatcher("home.jsp").forward(request, response);
					} else {
						System.out.println("密码错误！");
						request.setAttribute("errorInfo", "密码错误！");
						request.setAttribute("userName", userName);
						request.setAttribute("userPwd", userPwd);
						request.getRequestDispatcher("index.jsp").forward(request, response);
					}
				} else {
					System.out.println("用户名不存在！");
					request.setAttribute("userName", userName);
					request.setAttribute("userPwd", userPwd);
					request.setAttribute("errorInfo", "该用户不存在！");
					request.getRequestDispatcher("index.jsp").forward(request, response);
				}
			} else {
				request.setAttribute("errorInfo", "密码不能为空！");
				request.setAttribute("userName", userName);
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}
		} else {
			request.setAttribute("errorInfo", "用户名不能为空！");
			request.setAttribute("userPwd", userPwd);
			request.getRequestDispatcher("index.jsp").forward(request, response);
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
