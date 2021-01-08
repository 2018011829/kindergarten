package com.group.kindergarten.applyinfo.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.group.kindergarten.applyinfo.entity.ApplyInfo;

/**
 * Servlet implementation class AddChildEnterInformation
 */
@WebServlet("/AddChildEnterInformation")
public class AddChildEnterInformationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Gson gson;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddChildEnterInformationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String applyinfo = request.getParameter("applyInfo");
		initGson();
		ApplyInfo applyInfo = gson.fromJson(applyinfo, ApplyInfo.class);
		System.out.println("收到的消息："+applyinfo);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	
	private void initGson() {
		gson = new GsonBuilder()// 创建GsonBuilder对象
				.setPrettyPrinting()// 格式化输出
				.serializeNulls()// 允许输出Null值属性
				.setDateFormat("YY:MM:dd")// 日期格式化
				.create();// 创建Gson对象
	}

}
