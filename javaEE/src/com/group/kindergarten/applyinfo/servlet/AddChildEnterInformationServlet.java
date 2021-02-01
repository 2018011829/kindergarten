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
import com.group.kindergarten.applyinfo.service.EnterService;

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
		System.out.println("�յ�����Ϣ��"+applyinfo);
		initGson();
		if(applyinfo!=null) {
			ApplyInfo applyInfo = gson.fromJson(applyinfo, ApplyInfo.class);
			System.out.println("�յ�����Ϣ��"+applyInfo.toString());
	
			EnterService enterService = EnterService.getInstance();
			String userNumber = applyInfo.getUserNumber();
			String babyName = applyInfo.getBabyName();
			String babyBirthday = applyInfo.getBabyBirthday();
			String babySex = applyInfo.getBabySex();
			String babyIDnumber = applyInfo.getBabyIDnumber();
			String babyAddoAllergies = applyInfo.getBabyAddoAllergies();
			String parentName1 = applyInfo.getParentName1();
			String parentName2 = applyInfo.getParentIDnumber2();
			String parentIDnumber1 = applyInfo.getParentIDnumber1();
			String parentIDnumber2 = applyInfo.getParentIDnumber2();
			String relation1 = applyInfo.getRelation1();
			String relation2 = applyInfo.getRelation2();
			String phoneNumber1 = applyInfo.getPhoneNumber1();
			String phoneNumber2 = applyInfo.getPhoneNumber2();
			String workSpace1 = applyInfo.getWorkSpace1();
			String workSpace2 = applyInfo.getWorkSpace2();
			String homeAddress1 = applyInfo.getHomeAddress1();
			String homeAddress2 = applyInfo.getHomeAddress2();
			boolean flag =  enterService.addChildApplyInformation(userNumber, babyName, babyBirthday, babySex,
					babyIDnumber, babyAddoAllergies, parentName1, relation1, parentIDnumber1, 
					phoneNumber1, workSpace1, homeAddress1, parentName2, relation2, parentIDnumber2, 
					phoneNumber2, workSpace2, homeAddress2);
			if(flag) {
				// ������Ӧ
				response.getWriter().write("�ύ�ɹ�");
			}else {
				response.getWriter().write("�ύʧ��");
			}	
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	
	private void initGson() {
		gson = new GsonBuilder()// ����GsonBuilder����
				.setPrettyPrinting()// ��ʽ�����
				.serializeNulls()// �������Nullֵ����
				.setDateFormat("YY:MM:dd")// ���ڸ�ʽ��
				.create();// ����Gson����
	}

}
