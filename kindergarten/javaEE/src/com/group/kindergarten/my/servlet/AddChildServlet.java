package com.group.kindergarten.my.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.group.kindergarten.my.service.ChildrenService;

/**
 * Servlet implementation class AddChildServlet
 */
@WebServlet("/AddChildServlet")
public class AddChildServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddChildServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ���ñ���
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		//��ȡ���ݹ����ĺ��ӵ���Ϣ
		String name = request.getParameter("name");
		String idNum = request.getParameter("idNum");
		String sex = request.getParameter("sex");
		String phone = request.getParameter("parentPhone");
		System.out.println("AddChildServlet");
		ChildrenService childrenService = ChildrenService.getInstance();
		//���жϺ����Ƿ��Ѿ�����
		if(!childrenService.searchChild(name, phone)) {
			System.out.println("���Ӳ�����");
			//�жϺ����Ƿ����׶�԰���Ѿ�����
			if(idNum!=null) {
				boolean a=childrenService.searchIdNumIsRight(idNum);
				if(a) {
					//�жϸ����֤���뺢�ӵ������Ƿ��Ǻ�
					boolean c=childrenService.searchNameAndId(name, idNum);
					if(c) {
						boolean b=childrenService.addChild(name, idNum, sex, phone);
						if(b) {//��ӳɹ�
							System.out.println("�ɹ���Ӻ���");
							response.getWriter().write("success");
						}else {
							response.getWriter().write("faliure");
						}
					}else {
						System.out.println("���ӵ����������֤�Ų�����");
						response.getWriter().write("���ӵ����������֤�Ų���");
					}
				}else {
					System.out.println("���ӻ�û�����׶�԰������");
					response.getWriter().write("���ӻ�û�����׶�԰����");
				}
			}else {
				System.out.println("���֤��Ϊ�գ�");
			}
		}else {
			System.out.println("�����Ѿ���ӹ��ˣ�");
			response.getWriter().write("�����Ѿ���ӹ���");
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
