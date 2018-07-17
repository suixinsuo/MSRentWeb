package com.dpp.rent.app.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.dpp.rent.app.api.constant.MSConstant;
import com.google.gson.Gson;

/**
 * className:LogRequestInterceptor.java
 * description: 日志拦截器
 * date: 2018年6月30日
 * author:jpg
 */
public class LogRequestInterceptor implements HandlerInterceptor{
	
	private static Log log = LogFactory.getLog(LogRequestInterceptor.class);

	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		// 打印出请求过来的reqData日志
		String pathString = request.getServletPath();
		log.info(pathString);
		String reqData = request.getParameter(MSConstant.REQDATA);
		log.info("客户端请求过来的参数：" + reqData);
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		Object object = request.getAttribute(MSConstant.REP_DATA);
		if (null!=object) {
			Gson  gson = new Gson();
			log.info("返回给客户端的数据：" + gson.toJson(object));
		}
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
	}

}
