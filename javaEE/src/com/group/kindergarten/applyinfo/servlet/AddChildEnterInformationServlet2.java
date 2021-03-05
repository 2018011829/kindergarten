package com.group.kindergarten.applyinfo.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.group.kindergarten.applyinfo.service.EnterService;

/**
 * Servlet implementation class AddChildEnterInformationServlet2
 */
@WebServlet("/AddChildEnterInformationServlet2")
public class AddChildEnterInformationServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddChildEnterInformationServlet2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		String babyIDnumber = request.getParameter("babyIDnumber");
		boolean result = EnterService.getInstance().isExist(babyIDnumber);
		if(result) {
			response.getWriter().write("添加失败，该孩子已经完成报名了<a href='addApplyinfo.jsp'>,点击返回</a>");
		}else {
			EnterService enterService = EnterService.getInstance();
			String userNumber = request.getParameter("userNumber");
			String babyName = request.getParameter("babyName");
			String babyBirthday = request.getParameter("babyBirthday");
			String babySex = request.getParameter("babySex");
			String babyAddoAllergies = request.getParameter("babyAddoAllergies");
			String parentName1 = request.getParameter("parentName1");
			String parentName2 = request.getParameter("parentName2");
			String parentIDnumber1 = request.getParameter("parentIDnumber1");
			String parentIDnumber2 = request.getParameter("parentIDnumber2");
			String relation1 = request.getParameter("relation1");
			String relation2 = request.getParameter("relation2");
			String phoneNumber1 = request.getParameter("phoneNumber1");
			String phoneNumber2 = request.getParameter("phoneNumber2");
			String workSpace1 = request.getParameter("workSpace1");
			String workSpace2 = request.getParameter("workSpace2");
			String homeAddress1 = request.getParameter("homeAddress1");
			String homeAddress2 = request.getParameter("homeAddress2");
			enterService.addChildApplyInformation(userNumber, babyName, babyBirthday, babySex,
					babyIDnumber, babyAddoAllergies, parentName1, relation1, parentIDnumber1, 
					phoneNumber1, workSpace1, homeAddress1, parentName2, relation2, parentIDnumber2, 
					phoneNumber2, workSpace2, homeAddress2);
			response.getWriter().write("成功添加孩子报名信息<a href='addApplyinfo.jsp'>,点击返回</a>");
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
