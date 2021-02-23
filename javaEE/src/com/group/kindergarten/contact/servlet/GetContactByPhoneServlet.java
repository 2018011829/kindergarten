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
		//��ȡ�绰����
		String phone = request.getParameter("phone");
		System.out.println(phone);
		//����
		List<LCChatKitUser> lcChatKitUsers = RemarkService.getInstance().getContactByPhone(phone);
		//�õ���list�ǿյ�
		if (lcChatKitUsers.isEmpty()) {
			response.getWriter().write("����û����ϵ��");
		}
		
		//list�ǿ�
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
