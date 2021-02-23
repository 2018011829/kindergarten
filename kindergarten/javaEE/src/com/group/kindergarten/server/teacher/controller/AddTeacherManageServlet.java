package com.group.kindergarten.server.teacher.controller;

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

import com.group.kindergarten.teacher.entity.Teacher;
import com.group.kindergarten.teacher.service.TeacherServiceImpl;

/**
 * 服务端新增教师信息
 */
@WebServlet("/addTeacherManage")
public class AddTeacherManageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddTeacherManageServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Teacher teacher = new Teacher();
		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		TeacherServiceImpl teacherServiceImpl = new TeacherServiceImpl();
		try {
			List<FileItem> list = upload.parseRequest(request);
			for (FileItem item : list) {
				if (item.isFormField()) {// 判断是否为文件
					if (item.getFieldName().equals("teacherName")) {
						String teacherName = item.getString("utf-8");
						if (teacherName != null && !teacherName.equals("")) {
							teacher.setName(teacherName);
						}else {
							response.sendRedirect("addTeacher.jsp");
							return;
						}
					}
					if (item.getFieldName().equals("teacherPosition")) {
						String teacherPosition = item.getString("utf-8");
						if (teacherPosition != null && !teacherPosition.equals("")) {
							teacher.setPosition(teacherPosition);
						}else {
							response.sendRedirect("addTeacher.jsp");
							return;
						}
					}
					if (item.getFieldName().equals("teacherPhone")) {
						String teacherPhone = item.getString("utf-8");
						if (teacherPhone != null && !teacherPhone.equals("")) {
							teacher.setPhone(teacherPhone);
						}else {
							response.sendRedirect("addTeacher.jsp");
							return;
						}
					}
					if (item.getFieldName().equals("teacherMotto")) {
						String teacherMotto = item.getString("utf-8");
						if (teacherMotto != null && !teacherMotto.equals("")) {
							teacher.setMotto(teacherMotto);
						}
					}
				} else {// 是文件 进行文件的读写
					String imgName = item.getName();
					if (imgName != null && !imgName.equals("")) {
						String path = this.getServletContext().getRealPath("/teacherImgs");
						String name = "" + System.currentTimeMillis();
						String ext = imgName.substring(item.getName().lastIndexOf("."), item.getName().length());
						teacher.setPicture("teacherImgs/" + name + ext);
						item.write(new File(path + "/" + name + ext));
					} else {
						teacher.setPicture(null);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		boolean flag = teacherServiceImpl.addTeacherService(teacher);
		if(flag) {
			response.sendRedirect("teacherManage");
		}else {
			response.sendRedirect("addTeacher.jsp");
		}
	}

}
