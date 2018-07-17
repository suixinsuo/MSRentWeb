package com.dpp.rent.app.web.controller.web;

import java.util.ResourceBundle;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dpp.rent.app.api.model.JsonReturn;

@Controller
public class OtherProjectController {
	private static Log log = LogFactory.getLog(OtherProjectController.class);
	// 控制其它app是否自毁，0.否 ，1.是
	private static String APP_ACTIVE=null;
	
	static {
		APP_ACTIVE = ResourceBundle.getBundle("config").getString("app_active");
	}
	/**
	 * 其余的项目控制自毁接口
	 */
	@RequestMapping(value = "/appActive",method = RequestMethod.GET)
	@ResponseBody
    public JsonReturn otherLogin(){
		return JsonReturn.ok(APP_ACTIVE);
    }
}
