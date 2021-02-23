package com.group.kindergarten.contact.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.group.kindergarten.contact.entity.LCChatKitUser;
import com.group.kindergarten.contact.service.RemarkService;

/**
 * Servlet implementation class GetContactByPhoneServlet
 */
@WebServlet("/GetContactByPhoneServlet")
public class GetContactByPhoneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetContactByPhoneServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取电话号码
		String phone = request.getParameter("phone");
		System.out.println(phone);
		//调用
		List<LCChatKitUser> lcChatKitUsers = RemarkService.getInstance().getContactByPhone(phone);
		//得到的list是空的
		if (lcChatKitUsers.isEmpty()) {
			response.getWriter().write("您还没有联系人");
		}
		
		//list非空
		String json = new Gson().toJson(lcChatKitUsers);
		response.getWriter().write(json);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
