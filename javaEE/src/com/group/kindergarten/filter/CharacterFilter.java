
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
 * 字符过滤器 Servlet Filter implementation class CharacterFilter
 */
@WebFilter("/*")
public class CharacterFilter implements Filter {

	// 存储编码格式信息
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
//		// 设置编码
//		request.setCharacterEncoding("UTF-8");
//		//response.setContentType("text/html;charset=utf-8");
//		chain.doFilter(request, response);
//		
//		HttpServletRequest req = (HttpServletRequest)request;
//		/**
//		 * 防止css和js文件被拦截
//		 */
//		String uri = req.getRequestURI();// 获得请求路径
//		if(uri.endsWith(".css") || uri.endsWith(".js") || uri.endsWith(".jpg") || uri.endsWith(".png")) {// 如果是css或js文件，直接放行
//			chain.doFilter(request, response);
//		}

		// 转换
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;

		/*
		 * 判断在web.xml文件中是否配置了编码格式的信息 如果为空，则设置编码格式为配置文件中的编码格式 否则编码格式设置为UTF-8
		 */
		if (this.encode != null && !this.encode.equals("")) {
			request.setCharacterEncoding(this.encode);
			response.setCharacterEncoding(this.encode);
		} else {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
		}

		/*
		 * 使用doFilter方法调用链中的下一个过滤器或目标资源（servlet或JSP页面）。
		 * chain.doFilter处理过滤器的其余部分（如果有的话），最终处理请求的servlet或JSP页面。
		 */
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// 获取在web.xml文件中配置了的编码格式的信息
		this.encode = fConfig.getInitParameter("encode");
	}

}
