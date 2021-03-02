package com.group.kindergarten.server.costMoney.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
 * Servlet implementation class UploadChargeServlet
 */
@WebServlet("/UploadChargeServlet")
public class UploadChargeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadChargeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Charge charge = new Charge();
		String weChatLast = null;// 原微信收款码路径
		String weChatName = null;// 原微信收款码名称
		String alipayLast = null;// 原支付宝收款码路径
		String alipayName = null;// 原支付宝收款码名称
		String url = request.getRealPath("/") + "imgs\\charge\\";
		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		CostMoneyService costMoneyService = new CostMoneyService();
		try {
			List<FileItem> list = upload.parseRequest(request);
			for (FileItem item : list) {
				if (item.isFormField()) {// 判断是否为文件
					if (item.getFieldName().equals("id")) {
						String id = item.getString();
						if (id != null && !id.equals("")) {
							charge.setId(Integer.parseInt(id));
						}
					}
					if (item.getFieldName().equals("babyClass")) {
						String babyClass = item.getString("utf-8");
						if (babyClass != null && !babyClass.equals("")) {
							charge.setBabyClass(babyClass);
						}
					}
					if (item.getFieldName().equals("teacher")) {
						String teacher = item.getString("utf-8");
						if (teacher != null && !teacher.equals("")) {
							charge.setTeacher(teacher);
						}
					}
					if (item.getFieldName().equals("weChatOld")) {
						System.out.println("执行");
						weChatName = item.getString("utf-8");
						weChatLast = url + weChatName;
					}
					if (item.getFieldName().equals("alipayOld")) {
						alipayName = item.getString("utf-8");
						alipayLast = url + alipayName;
					}
				} else {// 是文件 进行文件的读写
					if (item.getFieldName().equals("weChat")) {
						String imgName = item.getName();
						if (imgName != null && !imgName.equals("")) {
							deleteLastPicture(weChatLast);
							String path = this.getServletContext().getRealPath("/imgs/charge");
							System.out.println("path1:" + path);
							String[] temp;
							temp = weChatName.split("\\."); // 分割字符串
							SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
							String name = df.format(new Date())+"w";
							System.out.println("name:" + name);
							String ext = imgName.substring(item.getName().lastIndexOf("."), item.getName().length());
							System.out.println("ext:" + ext);
							charge.setWeChat(name+ext);
							System.out.println(name+ext);
							item.write(new File(path + "/" + name+ext));
						} else {
							charge.setWeChat(weChatName);
						}
					}else if(item.getFieldName().equals("alipay")) {
						String imgName = item.getName();
						if (imgName != null && !imgName.equals("")) {
							deleteLastPicture(alipayLast);
							String path = this.getServletContext().getRealPath("/imgs/charge");
							System.out.println("path2:" + path);
							String[] temp;
							temp = alipayName.split("\\."); // 分割字符串
							SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
							String name = df.format(new Date())+"a";
							System.out.println("name:" + name);
							String ext = imgName.substring(item.getName().lastIndexOf("."), item.getName().length());
							System.out.println("ext:" + ext);
							charge.setAlipay(name+ext);
							System.out.println(name+ext);
							item.write(new File(path + "/" + name+ext));
						} else {
							charge.setAlipay(alipayName);
						}
					}
					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(charge.toString());
		boolean flag = costMoneyService.updateCharge(charge);
		if (flag) {
			response.sendRedirect("ChargeManagerServlet");
		} else {
			response.sendRedirect("UpdateChargeServlet");
		}
	}
	public void deleteLastPicture(String pictureLast) {
		//删除原图片
		File file = new File(pictureLast);
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
