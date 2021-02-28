package com.group.kindergarten.costMoney.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.group.kindergarten.costMoney.util.Util;

/**
 * Servlet implementation class UploadScreenshotPhoto
 */
@WebServlet("/UploadScreenshotPhoto")
public class UploadScreenshotPhoto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadScreenshotPhoto() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * 将上传的截图保存到站点根目录
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取图片名称
		String screenshotName=Util.screenshotName;
		//获取输入流
		InputStream is=request.getInputStream();
		//通过输出流保存到站点imgs文件夹下的screenshot文件夹下
		OutputStream os=new FileOutputStream(new File("imgs/screenshot/"+screenshotName));
		int b=-1;
		while((b=is.read())!=-1) {
			os.write(b);
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
