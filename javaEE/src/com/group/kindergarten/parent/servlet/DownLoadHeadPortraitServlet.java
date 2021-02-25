package com.group.kindergarten.parent.servlet;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DownLoadHeadPortraitServlet
 */
@WebServlet("/DownLoadHeadPortraitServlet")
public class DownLoadHeadPortraitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DownLoadHeadPortraitServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 设置编码方式
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		//获取全局变量
		ServletContext application = this.getServletContext();
		// 下载客户端发来的图片
		InputStream is = request.getInputStream();
		// 获取本地输出流
		String path = getServletContext().getRealPath("/");
		System.out.println(path);
		String picture = "android" + System.currentTimeMillis() + ".png";//图片命名
		application.setAttribute("headImgName",picture);
		System.out.println(picture);
		
		BufferedInputStream bufferedInputStream = new BufferedInputStream(is);

		System.out.println(path);
		OutputStream outputStream = new FileOutputStream(path + "avatars/" + picture);
		BufferedOutputStream bufferedOutputStream = new BufferedOutputStream( outputStream );

		byte[] b=new byte[1024*1024];//代表一次最多读取1KB的内容

		int length = 0 ; //代表实际读取的字节数
		while( (length = bufferedInputStream.read( b ) )!= -1 ){
			//length 代表实际读取的字节数
			bufferedOutputStream.write(b, 0, length );
		}
        //缓冲区的内容写入到文件
		bufferedOutputStream.flush();
		System.out.println("下载完成！");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
