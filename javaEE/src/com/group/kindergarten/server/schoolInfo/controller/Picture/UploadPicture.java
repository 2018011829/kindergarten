package com.group.kindergarten.server.schoolInfo.controller.Picture;

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

import com.group.kindergarten.schoolInfo.entity.Picture;
import com.group.kindergarten.schoolInfo.service.PictureServce;
import com.group.kindergarten.teacher.entity.Teacher;
import com.group.kindergarten.teacher.service.TeacherServiceImpl;

/**
 * Servlet implementation class UploadPicture
 */
@WebServlet("/UploadPicture")
public class UploadPicture extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadPicture() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Picture picture = new Picture();
		String pictureLast = null;
		String url = request.getRealPath("/")+"imgs\\schoolInfoPicture\\";
		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		PictureServce pictureServce = new PictureServce();
		try {
			List<FileItem> list = upload.parseRequest(request);
			for (FileItem item : list) {
				if (item.isFormField()) {// 判断是否为文件
					if (item.getFieldName().equals("id")) {
						String id = item.getString();
						if (id != null && !id.equals("")) {
							picture.setId(Integer.parseInt(id));
						}
					}
					if (item.getFieldName().equals("descriptionId")) {
						String descriptionId = item.getString("utf-8");
						if (descriptionId != null && !descriptionId.equals("")) {
							picture.setDescriptionId(Integer.parseInt(descriptionId));
						}
					}
					if (item.getFieldName().equals("pictureLast")) {
						pictureLast = url+item.getString("utf-8");
					}
				}else {// 是文件 进行文件的读写
					String imgName = item.getName();
					if (imgName != null && !imgName.equals("")) {
						String path = this.getServletContext().getRealPath("/imgs/schoolInfoPicture");
						System.out.println("path:"+path);
						String name = "" + System.currentTimeMillis();
						System.out.println("name:"+name);
						String ext = imgName.substring(item.getName().lastIndexOf("."), item.getName().length());
						System.out.println("ext:"+ext);
						picture.setPicture(name + ext);
						item.write(new File(path + "/" + name + ext));
					} else {
						picture.setPicture(null);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		boolean flag = pictureServce.updatePictureService(picture,pictureLast);
		if(flag) {
			response.sendRedirect("PictureManageServlet");
		}else {
			response.sendRedirect("updatePictureServlet");
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
