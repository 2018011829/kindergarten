package com.group.kindergarten.school.servlet;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.group.kindergarten.school.service.SchoolService;

/**
 * Servlet implementation class InitDataAboutIntroduceText
 */
@WebServlet("/InitDataAboutIntroduceText")
public class InitDataAboutIntroduceText extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InitDataAboutIntroduceText() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 设置编码
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		//从数据库中获取保存幼儿园简介的文字信息
		String text="";
		String fileName=SchoolService.getInstance().searchIntroduceTextFileName();
		//读取站点根目录下的文件信息
		if(!fileName.equals("") && fileName!=null) {
			//通过流来循环读取
			//拼接读取路径
			String path=getServletContext().getRealPath("/files/")+fileName;
			System.out.println(path);
			//按行读取文件
			BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(path),"utf-8"));
			StringBuffer sb=new StringBuffer();
			String str = "";
			while((str=br.readLine())!=null) {
				sb.append(str);
			}
			br.close();
			text=sb.toString();
			if(!text.equals("") && text!=null) {
				response.getWriter().write(text);
			}else {
				System.out.println("幼儿园简介信息为空");
				response.getWriter().write("幼儿园简介信息为空");
			}
		}else {
			System.out.println("文件名为空！");
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
