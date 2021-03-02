package com.group.kindergarten.server.costMoney.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.group.kindergarten.costMoney.entity.Charge;
import com.group.kindergarten.costMoney.service.CostMoneyService;
import com.group.kindergarten.schoolInfo.entity.Picture;
import com.group.kindergarten.schoolInfo.service.PictureServce;

/**
 * Servlet implementation class AddChargeServlet
 */
@WebServlet("/AddChargeServlet")
public class AddChargeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddChargeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Charge charge = new Charge();
		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		CostMoneyService costMoneyService = new CostMoneyService();
		try {
			List<FileItem> list = upload.parseRequest(request);
			for (FileItem item : list) {
				if (item.isFormField()) {// 判断是否为文件
					if (item.getFieldName().equals("babyClass")) {
						String babyClass = item.getString("utf-8");
						if (babyClass != null && !babyClass.equals("")) {
							charge.setBabyClass(babyClass);
						}else {
							response.sendRedirect("addCharge.jsp");
							return;
						}
					}else if(item.getFieldName().equals("teacher")) {
						String teacher = item.getString("utf-8");
						if (teacher != null && !teacher.equals("")) {
							charge.setTeacher(teacher);
						}else {
							response.sendRedirect("addCharge.jsp");
							return;
						}
					}
				} else {// 是文件 进行文件的读写
					if (item.getFieldName().equals("weChart")) {
						String imgName = item.getName();
						if (imgName != null && !imgName.equals("")) {
							String path = this.getServletContext().getRealPath("/imgs/charge");
							System.out.println("path:"+path);
							String name = "" + System.currentTimeMillis();
							System.out.println("name:"+name);
							String ext = imgName.substring(item.getName().lastIndexOf("."), item.getName().length());
							System.out.println("ext:"+ext);
							charge.setWeChat(name + ext);
							item.write(new File(path + "/" + name + ext));
						} else {
							charge.setWeChat(null);
						}
					}else if(item.getFieldName().equals("alipay")) {
						String imgName = item.getName();
						if (imgName != null && !imgName.equals("")) {
							String path = this.getServletContext().getRealPath("/imgs/charge");
							System.out.println("path:"+path);
							String name = "" + System.currentTimeMillis();
							System.out.println("name:"+name);
							String ext = imgName.substring(item.getName().lastIndexOf("."), item.getName().length());
							System.out.println("ext:"+ext);
							charge.setAlipay(name + ext);
							item.write(new File(path + "/" + name + ext));
						} else {
							charge.setAlipay(null);
						}
					}
					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		boolean flag = costMoneyService.addCharge(charge);
		if(flag) {
			response.sendRedirect("ChargeManagerServlet");
		}else {
			response.sendRedirect("addCharge.jsp");
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
