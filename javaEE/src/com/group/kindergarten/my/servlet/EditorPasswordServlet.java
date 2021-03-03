package com.group.kindergarten.my.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.group.kindergarten.my.service.ChildrenService;

/**
 * Servlet implementation class EditorPasswordServlet
 */
@WebServlet("/EditorPasswordServlet")
public class EditorPasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditorPasswordServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String phone = request.getParameter("phone");
		String password1 = request.getParameter("password1");
		String password2 = request.getParameter("password2");
		String password3 = request.getParameter("password3");
		
		boolean b = ChildrenService.getInstance().isExistUser(phone, password1);
		if(b) {
			if(password2.equals(password3) && password2.length()>=6) {
				ChildrenService.getInstance().updatePwd(phone, password2);
				response.getWriter().write("success");
				System.out.println("密码修改成功！");
			}else {
				response.getWriter().write("failure2");
				System.out.println("新密码两次输入不一致或密码位数错误！");
			}
		}else {
			response.getWriter().write("failure1");
			System.out.println("原密码不正确！");
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
