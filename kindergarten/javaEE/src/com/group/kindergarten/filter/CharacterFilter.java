
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

/**
 * �ַ������� Servlet Filter implementation class CharacterFilter
 */
@WebFilter("/*")
public class CharacterFilter implements Filter {
	

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
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// ���ñ���
		request.setCharacterEncoding("UTF-8");
		//response.setContentType("text/html;charset=utf-8");
		chain.doFilter(request, response);
		
		HttpServletRequest req = (HttpServletRequest)request;
		/**
		 * ��ֹcss��js�ļ�������
		 */
		String uri = req.getRequestURI();// �������·��
		if(uri.endsWith(".css") || uri.endsWith(".js") || uri.endsWith(".jpg") || uri.endsWith(".png")) {// �����css��js�ļ���ֱ�ӷ���
			chain.doFilter(request, response);
		}
	
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
