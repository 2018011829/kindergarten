package com.group.kindergarten.server.schoolInfo.controller.BasicInfo;

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

import com.group.kindergarten.schoolInfo.entity.BasicInfo;
import com.group.kindergarten.schoolInfo.entity.Picture;
import com.group.kindergarten.schoolInfo.service.BasicInfoService;
import com.group.kindergarten.schoolInfo.service.PictureServce;

/**
 * Servlet implementation class UploadBasicInfo
 */
@WebServlet("/UploadBasicInfo")
public class UploadBasicInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadBasicInfo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BasicInfo basicInfo = new BasicInfo();
		String introduceLast = null;
		String url = request.getRealPath("/")+"imgs\\schoolInfoPicture\\";
		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		BasicInfoService basicInfoService = new BasicInfoService();
		try {
			List<FileItem> list = upload.parseRequest(request);
			for (FileItem item : list) {
				if (item.isFormField()) {// 判断是否为文件
					if (item.getFieldName().equals("id")) {
						String id = item.getString();
						if (id != null && !id.equals("")) {
							basicInfo.setId(Integer.parseInt(id));
						}
					}
					if (item.getFieldName().equals("address")) {
						String address = item.getString("utf-8");
						if (address != null && !address.equals("")) {
							basicInfo.setAddress(address);
						}
					}
					if (item.getFieldName().equals("introduceLast")) {
						introduceLast = url+item.getString("utf-8");
						System.out.println("introduceLast"+introduceLast);
					}
				}else {// 是文件 进行文件的读写
					String imgName = item.getName();
					if (imgName != null && !imgName.equals("")) {
						
						deleteFile(introduceLast);
						String path = this.getServletContext().getRealPath("/imgs/schoolInfoPicture");
						System.out.println("path:"+path);
						String name = "园所简介";
						System.out.println("name:"+name);
						String ext = imgName.substring(item.getName().lastIndexOf("."), item.getName().length());
						System.out.println("ext:"+ext);
						basicInfo.setIntroduceFile(name + ext);
						item.write(new File(path + "/" + name + ext));
					} else {
						basicInfo.setIntroduceFile(null);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		boolean flag =basicInfoService.updateBasicInfoService(basicInfo);
		if(flag) {
			response.sendRedirect("BasicInfoServlet");
		}else {
			response.sendRedirect("UpdateBasicInfo");
		}
	}

	public void deleteFile(String introduceLast) {
		//删除原图片
		File file = new File(introduceLast);
        //判断文件是否存在
        if (file.exists() == true){
            System.out.println("图片存在，可执行删除操作");
            Boolean flag = false;
            flag = file.delete();
            if (flag){
                System.out.println("成功删除图片"+file.getName());
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

}
