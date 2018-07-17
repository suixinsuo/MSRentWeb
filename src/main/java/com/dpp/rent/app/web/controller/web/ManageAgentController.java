package com.dpp.rent.app.web.controller.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dpp.rent.app.api.constant.BusinessErrorCode;
import com.dpp.rent.app.api.domain.User;
import com.dpp.rent.app.api.exception.BizException;
import com.dpp.rent.app.api.model.JsonReturn;
import com.dpp.rent.app.api.model.request.LoginForm;
import com.dpp.rent.app.api.model.response.LoginResponse;
import com.dpp.rent.app.web.util.DataUtil;
import com.dpp.rent.service.dao.user.UserDao;

/**
 * className:ManageAgentController.java
 * description: 经纪人管理页面
 * date: 2018年7月11日
 * author:jpg
 */
@Controller
public class ManageAgentController {
	private static Log log = LogFactory.getLog(ManageAgentController.class);
	
	@Autowired
	private UserDao userDao;
	
	/**
	 * 跳转到新增经纪人页面
	 */
	@RequestMapping(value = "/web_addAgentBefore",method = RequestMethod.GET)
    public String web_addAgentBefore(Model model){
		// 生成经纪人4位推荐生成码
		String maxCode = userDao.getMaxLinkCode();
		if (StringUtils.isBlank(maxCode)) {
			// 为空则从0001开始
			maxCode = "0001";
		} else {
			if (Integer.valueOf(maxCode)>=9999) {
				log.error("新增已达上限");
				throw new BizException(BusinessErrorCode.SYSTEM_ERROR.getId());
			}
			// 则自动加1并且格式化成4位数，前面补0
			int res = Integer.valueOf(maxCode)+1;
			maxCode =  String.format("%04d", res);
		}
		model.addAttribute("linkCode",maxCode);
		return "addAgentBefore";
	}
	
	/**
	 * 经纪人管理页面,查询页面
	 */
	@RequestMapping(value = "/web_manager",method = RequestMethod.GET)
    public String web_manager(HttpServletRequest request,Model model){
		List<User> list = userDao.getAllUser();
		model.addAttribute("userList",list);
		return "manageAgent";
	}
	
//	@RequestMapping(value = "/getAllUser",method = RequestMethod.POST)
//    public JsonReturn getAllUser(HttpServletRequest request,Model model){
////		List<User> list = userDao.getAllUser();
////		model.addAttribute("userList",list);
////		return JsonReturn.ok(list);
//    }
}
