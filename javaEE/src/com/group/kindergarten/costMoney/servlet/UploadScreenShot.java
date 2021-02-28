package com.group.kindergarten.costMoney.servlet;

import java.io.IOException;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.group.kindergarten.costMoney.entity.MoneyPicture;
import com.group.kindergarten.costMoney.service.CostMoneyService;
import com.group.kindergarten.costMoney.util.Util;

/**
 * Servlet implementation class UploadScreenShot
 */
@WebServlet("/UploadScreenShot")
public class UploadScreenShot extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UploadScreenShot() {
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
		System.out.println("GetChildMoneyServlet");
		// ��ȡjson������Ϣ
		String json = request.getParameter("content");
		//����json��
		MoneyPicture moneyPicture=new Gson().fromJson(json, MoneyPicture.class);
		//��ȡ��ǰ�·�
		int month=0;
		Calendar c = Calendar.getInstance();//0-11
		month=c.get(Calendar.MONTH)+1;
		//����Ҫ����Ľ�ͼ���ļ�����
		String screenshotName=moneyPicture.getBabyName()+"-"+moneyPicture.getBabyGrade()+"-"+moneyPicture.getBabyClass()+"-"+moneyPicture.getPhone()+".jpg";
		Util.screenshotName=screenshotName;
		//�����ݱ��������ݿ�
		boolean b=CostMoneyService.getInstance().preserveScreenshotInfo(moneyPicture, month, screenshotName);
		//���ͻ��˷����ϴ����
		if(b) {
			response.getWriter().write("�ϴ��ɹ�");
			System.out.println("�ϴ��ɹ�");
		}else {
			response.getWriter().write("�ϴ�ʧ��");
			System.out.println("�ϴ�ʧ��");
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
