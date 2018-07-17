package com.dpp.rent.app.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.dpp.rent.app.api.constant.BusinessErrorCode;
import com.dpp.rent.app.api.constant.CacheConstant;
import com.dpp.rent.app.api.constant.MSConstant;
import com.dpp.rent.app.api.domain.User;
import com.dpp.rent.app.api.exception.BizException;
import com.dpp.rent.app.api.util.CacheService;
import com.dpp.rent.service.dao.user.UserDao;

/**
 * className:LoginInterceptor.java
 * description: 所有登录之后的请求
 * date: 2018年6月30日
 * author:jpg
 */
public class LoginInterceptor implements HandlerInterceptor{
	
	private static Log log = LogFactory.getLog(LoginInterceptor.class);
	
	@Autowired
	private CacheService cacheService;
	
	@Autowired
	private UserDao userDao;
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		// reqData判空
		String reqData = request.getParameter(MSConstant.REQDATA);
		if (StringUtils.isBlank(reqData)) {
			throw new BizException(BusinessErrorCode.REQDATA_IS_NULL.getId());
		}
		
		// token校验
		JSONObject jsonObject = JSONObject.parseObject(reqData);
		String token = jsonObject.getString("token");
		String id = jsonObject.getString("id");
		String Loginkey = CacheConstant.getLoginKey(id);;
		String loginToken = (String) cacheService.get(Loginkey);
		if (StringUtils.isBlank(loginToken)||StringUtils.isBlank(id)) {
			log.info("登录缓存token为空，无效或者已失效");
			throw new BizException(BusinessErrorCode.TOKEN_IS_INVALID.getId());
		}
		// 校验token是否正确
		if (!token.equals(loginToken)) {
			log.info("token不正确，非法请求，mobile:" + id);
			throw new BizException(BusinessErrorCode.TOKEN_IS_INVALID.getId());
		}
		
		// 校验用户id是否存在及状态是否正常
		User user= userDao.getUser(id);
		if (user==null || !MSConstant.USER_STATUS_NORMAL.equals(user.getStatus())) {
			log.error("用户不存在或者状态不正常");
			throw new BizException(BusinessErrorCode.SYSTEM_ERROR.getId());
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
