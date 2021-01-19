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
		// ���ñ���
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		//�����ݿ��л�ȡ�����׶�԰����������Ϣ
		String text="";
		String fileName=SchoolService.getInstance().searchIntroduceTextFileName();
		//��ȡվ���Ŀ¼�µ��ļ���Ϣ
		if(!fileName.equals("") && fileName!=null) {
			//ͨ������ѭ����ȡ
			//ƴ�Ӷ�ȡ·��
			String path=getServletContext().getRealPath("/files/")+fileName;
			System.out.println(path);
			//���ж�ȡ�ļ�
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
				System.out.println("�׶�԰�����ϢΪ��");
				response.getWriter().write("�׶�԰�����ϢΪ��");
			}
		}else {
			System.out.println("�ļ���Ϊ�գ�");
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
