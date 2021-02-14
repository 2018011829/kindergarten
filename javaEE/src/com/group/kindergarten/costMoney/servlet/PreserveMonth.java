package com.group.kindergarten.costMoney.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.group.kindergarten.costMoney.entity.SchoolSemester;
import com.group.kindergarten.costMoney.service.CostMoneyService;

/**
 * Servlet implementation class PreserveMonth
 */
@WebServlet("/PreserveMonth")
public class PreserveMonth extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PreserveMonth() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 设置编码
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		System.out.println("PreserveMonth");
		// 获取控件内容 1-12个月的天数信息
		String one=request.getParameter("one");
		String two=request.getParameter("two");
		String three=request.getParameter("three");
		String four=request.getParameter("four");
		String five=request.getParameter("five");
		String six=request.getParameter("six");
		String seven=request.getParameter("seven");
		String eight=request.getParameter("eight");
		String nine=request.getParameter("nine");
		String ten=request.getParameter("ten");
		String eleven=request.getParameter("eleven");
		String twelve=request.getParameter("twelve");
		// 判断控件内容是否是空
		boolean a=false;boolean b=false;boolean c=false;boolean d=false;boolean e=false;boolean f=false;
		boolean g=false;boolean h=false;boolean i=false;boolean j=false;boolean k=false;boolean l=false;
		if(one!=null && !one.equals("")) {
			int dayNum=Integer.parseInt(one);
			a=CostMoneyService.getInstance().justAndPreserve(1, dayNum);
		}
		if(two!=null && !two.equals("")) {
			int dayNum=Integer.parseInt(two);
			b=CostMoneyService.getInstance().justAndPreserve(1, dayNum);
		}
		if(three!=null && !three.equals("")) {
			int dayNum=Integer.parseInt(three);
			c=CostMoneyService.getInstance().justAndPreserve(1, dayNum);
		}
		if(four!=null && !four.equals("")) {
			int dayNum=Integer.parseInt(four);
			d=CostMoneyService.getInstance().justAndPreserve(1, dayNum);
		}
		if(five!=null && !five.equals("")) {
			int dayNum=Integer.parseInt(five);
			e=CostMoneyService.getInstance().justAndPreserve(1, dayNum);
		}
		if(six!=null && !six.equals("")) {
			int dayNum=Integer.parseInt(six);
			f=CostMoneyService.getInstance().justAndPreserve(1, dayNum);
		}
		if(seven!=null && !seven.equals("")) {
			int dayNum=Integer.parseInt(seven);
			g=CostMoneyService.getInstance().justAndPreserve(1, dayNum);
		}
		if(eight!=null && !eight.equals("")) {
			int dayNum=Integer.parseInt(eight);
			h=CostMoneyService.getInstance().justAndPreserve(1, dayNum);
		}
		if(ten!=null && !ten.equals("")) {
			int dayNum=Integer.parseInt(ten);
			i=CostMoneyService.getInstance().justAndPreserve(1, dayNum);
		}
		if(nine!=null && !nine.equals("")) {
			int dayNum=Integer.parseInt(nine);
			j=CostMoneyService.getInstance().justAndPreserve(1, dayNum);
		}
		if(eleven!=null && !eleven.equals("")) {
			int dayNum=Integer.parseInt(eleven);
			k=CostMoneyService.getInstance().justAndPreserve(1, dayNum);
		}
		if(twelve!=null && !twelve.equals("")) {
			int dayNum=Integer.parseInt(twelve);
			l=CostMoneyService.getInstance().justAndPreserve(1, dayNum);
		}
		if(a||b||c||d||e||f||g||h||i||j||k||l) {// 跳转到显示界面
			//从数据库中获取设置的学期信息
			List<SchoolSemester> list=CostMoneyService.getInstance().searchSemesterInfo();
			request.getSession().setAttribute("schoolSemester", list);
			request.getRequestDispatcher("showSemester.jsp").forward(request, response);
		}else {// 跳转回设置界面，显示设置失败
			request.setAttribute("errorInfo", "设置失败，请重新设置！");
			request.getRequestDispatcher("SetSemester.jsp").forward(request, response);
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
