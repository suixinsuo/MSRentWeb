package com.dpp.rent.app.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.dpp.rent.app.api.domain.User;

/**
 * className:WebLoginInterceptor.java
 * description: web的action拦截器
 * date: 2018年7月12日
 * author:jpg
 */
public class WebLoginInterceptor implements HandlerInterceptor{
	private static Log log = LogFactory.getLog(WebLoginInterceptor.class);


	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		String contextPath = "http://localhost:8080" +  request.getContextPath();
		log.info("contextPath:" + contextPath);
		String url = request.getRequestURI();
		log.info("url:" + url);
		String requestUrl = url.substring(url.lastIndexOf("/")+1,url.lastIndexOf("."));
		log.info("requestUrl:" + requestUrl);
		if ("web_login".equals(requestUrl)) {
			// 登陆接口无需 校验
			return true;
		}
		User user = (User) request.getSession().getAttribute("user");
		if (user==null) {
			((HttpServletResponse)response).sendRedirect(((HttpServletRequest)request).getContextPath()+"/login.jsp");
			// 缓存失效
			return false;
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

}
