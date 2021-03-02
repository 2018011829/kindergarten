
package com.group.kindergarten.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * �ַ������� Servlet Filter implementation class CharacterFilter
 */
@WebFilter("/*")
public class CharacterFilter implements Filter {

	// �洢�����ʽ��Ϣ
	private String encode = null;

	/**
	 * Default constructor.
	 */
	public CharacterFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
//		// ���ñ���
//		request.setCharacterEncoding("UTF-8");
//		//response.setContentType("text/html;charset=utf-8");
//		chain.doFilter(request, response);
//		
//		HttpServletRequest req = (HttpServletRequest)request;
//		/**
//		 * ��ֹcss��js�ļ�������
//		 */
//		String uri = req.getRequestURI();// �������·��
//		if(uri.endsWith(".css") || uri.endsWith(".js") || uri.endsWith(".jpg") || uri.endsWith(".png")) {// �����css��js�ļ���ֱ�ӷ���
//			chain.doFilter(request, response);
//		}

		// ת��
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;

		/*
		 * �ж���web.xml�ļ����Ƿ������˱����ʽ����Ϣ ���Ϊ�գ������ñ����ʽΪ�����ļ��еı����ʽ ��������ʽ����ΪUTF-8
		 */
		if (this.encode != null && !this.encode.equals("")) {
			request.setCharacterEncoding(this.encode);
			response.setCharacterEncoding(this.encode);
		} else {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
		}

		/*
		 * ʹ��doFilter�����������е���һ����������Ŀ����Դ��servlet��JSPҳ�棩��
		 * chain.doFilter��������������ಿ�֣�����еĻ��������մ��������servlet��JSPҳ�档
		 */
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// ��ȡ��web.xml�ļ��������˵ı����ʽ����Ϣ
		this.encode = fConfig.getInitParameter("encode");
	}

}
