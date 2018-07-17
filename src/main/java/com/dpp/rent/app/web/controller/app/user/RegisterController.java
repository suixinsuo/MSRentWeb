package com.dpp.rent.app.web.controller.app.user;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dpp.rent.app.api.model.JsonReturn;
import com.dpp.rent.app.api.model.request.LoginForm;
import com.dpp.rent.app.api.model.request.RegisterForm;
import com.dpp.rent.app.api.model.response.LoginResponse;
import com.dpp.rent.app.api.service.user.UserService;
import com.dpp.rent.app.web.util.DataUtil;

/**
 * className:RegisterController.java
 * description: 用户注册入口
 * date: 2018年6月24日
 * author:jpg
 */
@Controller
public class RegisterController {
	private static Log log = LogFactory.getLog(RegisterController.class);
	
	@Autowired
	private UserService userService;
	
	/**
	 * 注册入口
	 */
	@RequestMapping(value = "/register",method = RequestMethod.POST)
	@ResponseBody
    public JsonReturn register(String reqData,RegisterForm registerForm,HttpServletResponse response){
		registerForm = (RegisterForm) DataUtil.ParseForm(reqData, registerForm.getClass());
		userService.register(registerForm);
		return JsonReturn.ok();
    }
	
	/**
	 * 修改密码
	 */
	@RequestMapping(value = "/modifyPwd",method = RequestMethod.POST)
	@ResponseBody
    public JsonReturn modifyPwd(String reqData,RegisterForm registerForm){
		registerForm = (RegisterForm) DataUtil.ParseForm(reqData, registerForm.getClass());
		userService.modifyUserPwd(registerForm);
		return JsonReturn.ok();
    }
}
