package com.group.kindergarten.costMoney.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.group.kindergarten.costMoney.entity.Charge;
import com.group.kindergarten.costMoney.service.CostMoneyService;

/**
 * Servlet implementation class SendChargeServlet
 */
@WebServlet("/SendChargeServlet")
public class SendChargeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// ����Gson��������
	private Gson gson;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SendChargeServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ��ʼ��json
		initGson();
		List<Charge> charges = CostMoneyService.getInstance().findCharge();
		// ���л�
		String json = gson.toJson(charges);
		// ��ȡ���������������ͼƬ��������Դ·�����ظ��ͻ���
		System.out.println(json);
		response.getWriter().write(json);
	}

	/**
	 * ��ʼ��Gson����
	 */
	private void initGson() {
		gson = new GsonBuilder()// ����GsonBuilder����
				.setPrettyPrinting()// ��ʽ�����
				.serializeNulls()// �������Nullֵ����
				.setDateFormat("YY:MM:dd")// ���ڸ�ʽ��
				.create();// ����Gson����
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
