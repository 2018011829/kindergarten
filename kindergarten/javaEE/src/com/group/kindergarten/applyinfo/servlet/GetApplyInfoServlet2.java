package com.group.kindergarten.applyinfo.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.group.kindergarten.applyinfo.entity.ApplyInfo;
import com.group.kindergarten.applyinfo.service.EnterService;
import com.group.kindergarten.util.Page;

/**
 * Servlet implementation class GetApplyInfoServlet2
 */
@WebServlet("/GetApplyInfoServlet2")
public class GetApplyInfoServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetApplyInfoServlet2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String page1 = request.getParameter("page");
		String size = request.getParameter("size");
		String count = request.getParameter("count");
		
		int pageNum = 1, pageSize = 5;
		if(count!=null && !count.equals("")) {
			if((Integer.parseInt(count)-1)%Integer.parseInt(size)==0) {
			if(page1!=null && !page1.equals("")) {
				pageNum = Integer.parseInt(page1)-1;
			}
			}else {
				if(page1!=null && !page1.equals("")) {
				pageNum = Integer.parseInt(page1);
				}
			}
		}else if(page1!=null && !page1.equals("")) {
			pageNum = Integer.parseInt(page1);
		}
		
		EnterService enterService = EnterService.getInstance();
		Page<ApplyInfo> page =enterService.searchChildByPage(pageNum, pageSize);
		request.setAttribute("page", page);
		request.getRequestDispatcher("applyinfoManage.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
