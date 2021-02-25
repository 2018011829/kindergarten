package com.group.kindergarten.parent.servlet;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.group.kindergarten.parent.service.UserParentService;

/**
 * Servlet implementation class DownLoadMessageServlet
 */
@WebServlet("/DownLoadMessageServlet")
public class DownLoadMessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DownLoadMessageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
					// 设置编码方式
					request.setCharacterEncoding("utf-8");
					response.setContentType("text/html;charset=utf-8");
					
					String headName = null;
					//判断头像是否做过修改
					ServletContext application = this.getServletContext();
					if(application.getAttribute("headImgName")!=null&&!application.getAttribute("headImgName").equals("")) {
						headName = application.getAttribute("headImgName").toString();
						application.removeAttribute("headImgName");
						System.out.println("缓存" + application.getAttribute("headImaName"));
					}
					//得到手机号码
					String phone = request.getParameter("phone");
					String nickName = request.getParameter("nickName");
					if(headName == null) {
						headName = request.getParameter("headName");
						String [] nameStrings = headName.split("/");
						headName = nameStrings[nameStrings.length-1];
					}
				
					System.out.println("手机号" + phone);
					System.out.println("昵称" + nickName);
					System.out.println("头像" + headName);
					
					UserParentService userParentService = UserParentService.getInstance();
					userParentService.updateParentMessage(phone,nickName, headName);
					
					response.getWriter().write("success");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
