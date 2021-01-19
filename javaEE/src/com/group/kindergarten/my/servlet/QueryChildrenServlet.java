package com.group.kindergarten.my.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.group.kindergarten.my.entity.Child;
import com.group.kindergarten.my.service.ChildrenService;

/**
 * Servlet implementation class QueryChildrenServlet
 */
@WebServlet("/QueryChildrenServlet")
public class QueryChildrenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public QueryChildrenServlet() {
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
		String phone = request.getParameter("phone");
		System.out.println("�ֻ���" + phone);
		List<Child> list = new ArrayList<Child>();
		list = ChildrenService.getInstance().queryChildrenByPhone(phone);
		if (list != null) {
			JSONArray jsonArray = new JSONArray();
			for (Child child : list) {
				JSONObject jObject = new JSONObject();
				jObject.put("id", child.getId());
				jObject.put("name", child.getName());
				jObject.put("grade", child.getGrade());
				jObject.put("sex", child.getSex());
				jObject.put("parentPhone", child.getParentPhone());

				jsonArray.put(jObject);
			}

			JSONObject jChildren = new JSONObject();
			jChildren.put("children", jsonArray);
			String json = jChildren.toString();
			System.out.println("json��" + json);
			response.getWriter().write(json);
		}else {
			response.getWriter().write("����û����Ӻ���");
			System.out.println("����û����Ӻ���");
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
