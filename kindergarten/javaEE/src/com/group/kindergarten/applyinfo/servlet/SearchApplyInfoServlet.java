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
 * Servlet implementation class SearchApplyInfoServlet
 */
@WebServlet("/SearchApplyInfoServlet")
public class SearchApplyInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchApplyInfoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String msg = request.getParameter("msg");
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
		Page<ApplyInfo> page =enterService.searchChildByName(pageNum, pageSize, msg);
		request.setAttribute("page", page);
		request.setAttribute("msg", msg);
		request.getRequestDispatcher("searchApplyInfo.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
