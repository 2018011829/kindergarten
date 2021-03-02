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
		String weChatLast = null;// ԭ΢���տ���·��
		String weChatName = null;// ԭ΢���տ�������
		String alipayLast = null;// ԭ֧�����տ���·��
		String alipayName = null;// ԭ֧�����տ�������
		String url = request.getRealPath("/") + "imgs\\charge\\";
		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		CostMoneyService costMoneyService = new CostMoneyService();
		try {
			List<FileItem> list = upload.parseRequest(request);
			for (FileItem item : list) {
				if (item.isFormField()) {// �ж��Ƿ�Ϊ�ļ�
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
						System.out.println("ִ��");
						weChatName = item.getString("utf-8");
						weChatLast = url + weChatName;
					}
					if (item.getFieldName().equals("alipayOld")) {
						alipayName = item.getString("utf-8");
						alipayLast = url + alipayName;
					}
				} else {// ���ļ� �����ļ��Ķ�д
					if (item.getFieldName().equals("weChat")) {
						String imgName = item.getName();
						if (imgName != null && !imgName.equals("")) {
							deleteLastPicture(weChatLast);
							String path = this.getServletContext().getRealPath("/imgs/charge");
							System.out.println("path1:" + path);
							String[] temp;
							temp = weChatName.split("\\."); // �ָ��ַ���
							SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//�������ڸ�ʽ
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
							temp = alipayName.split("\\."); // �ָ��ַ���
							SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//�������ڸ�ʽ
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
		//ɾ��ԭͼƬ
		File file = new File(pictureLast);
        //�ж��ļ��Ƿ����
        if (file.exists() == true){
            System.out.println("ͼƬ���ڣ���ִ��ɾ������");
            Boolean flag = false;
            flag = file.delete();
            if (flag){
                System.out.println("�ɹ�ɾ��ͼƬ"+file.getName());
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
