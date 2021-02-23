
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
 * 字符过滤器 Servlet Filter implementation class CharacterFilter
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
		// 设置编码
		request.setCharacterEncoding("UTF-8");
		//response.setContentType("text/html;charset=utf-8");
		chain.doFilter(request, response);
		
		HttpServletRequest req = (HttpServletRequest)request;
		/**
		 * 防止css和js文件被拦截
		 */
		String uri = req.getRequestURI();// 获得请求路径
		if(uri.endsWith(".css") || uri.endsWith(".js") || uri.endsWith(".jpg") || uri.endsWith(".png")) {// 如果是css或js文件，直接放行
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
