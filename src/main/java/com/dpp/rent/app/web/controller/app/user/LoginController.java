package com.dpp.rent.app.web.controller.app.user;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dpp.rent.app.api.model.JsonReturn;
import com.dpp.rent.app.api.model.request.LoginForm;
import com.dpp.rent.app.api.model.request.QuickLoginForm;
import com.dpp.rent.app.api.model.response.LoginResponse;
import com.dpp.rent.app.api.service.user.UserService;
import com.dpp.rent.app.web.util.DataUtil;

/**
 * className:LoginController.java
 * description: 登录业务处理
 * date: 2018年6月23日
 * author:jpg
 */
@Controller
public class LoginController {
	private static Log log = LogFactory.getLog(LoginController.class);
	
	@Autowired
	private UserService userService;
	
	/**
	 * 登录入口
	 */
	@RequestMapping(value = "/login",method = RequestMethod.POST)
	@ResponseBody
    public JsonReturn login(String reqData,LoginForm loginForm,HttpServletRequest request){
		loginForm = (LoginForm) DataUtil.ParseForm(reqData, loginForm.getClass());
		LoginResponse response = userService.userLogin(loginForm);
		DataUtil.responseDataToJson(response,request);
		return JsonReturn.ok(response);
    }
	
	/**
	 * 快速登录入口
	 */
	@RequestMapping(value = "/quickLogin",method = RequestMethod.POST)
	@ResponseBody
    public JsonReturn quickLogin(String reqData,QuickLoginForm quickLoginForm,HttpServletRequest request){
		quickLoginForm = (QuickLoginForm) DataUtil.ParseForm(reqData, quickLoginForm.getClass());
		LoginResponse response = userService.quickLogin(quickLoginForm);
		DataUtil.responseDataToJson(response,request);
		return JsonReturn.ok(response);
    }
}
