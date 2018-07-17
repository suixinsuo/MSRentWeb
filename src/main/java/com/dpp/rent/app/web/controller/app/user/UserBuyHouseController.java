package com.dpp.rent.app.web.controller.app.user;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dpp.rent.app.api.model.JsonReturn;
import com.dpp.rent.app.api.model.request.user.UserBuyHouseForm;
import com.dpp.rent.app.api.model.request.user.UserLeaseHouseForm;
import com.dpp.rent.app.api.model.request.user.UserRentHouseForm;
import com.dpp.rent.app.api.model.request.user.UserSellHouseForm;
import com.dpp.rent.app.api.service.user.UserBuyHouseService;
import com.dpp.rent.app.web.util.DataUtil;

/**
 * className:UserBuyHouseController.java
 * description: 用户发布出售，求购请求入口
 * date: 2018年7月5日
 * author:jpg
 */
@Controller
public class UserBuyHouseController {
	
	private static Log log = LogFactory.getLog(UserBuyHouseController.class);
	
	@Autowired
	private UserBuyHouseService userBuyHouseService;
	
	/**
	 *  用户发布出售房源意向记录
	 */
	@RequestMapping(value = "/addUserSellHouse",method = RequestMethod.POST)
	@ResponseBody
    public JsonReturn addUserSellHouse(String reqData,UserSellHouseForm userSellHouseForm){
		userSellHouseForm = (UserSellHouseForm) DataUtil.ParseForm(reqData, userSellHouseForm.getClass());
		userBuyHouseService.addUserSellHouse(userSellHouseForm);
		return JsonReturn.ok();
    }
	
	/**
	 *  用户发布求购房源意向记录
	 */
	@RequestMapping(value = "/addUserBuyHouse",method = RequestMethod.POST)
	@ResponseBody
    public JsonReturn addUserBuyHouse(String reqData,UserBuyHouseForm userBuyHouseForm){
		userBuyHouseForm = (UserBuyHouseForm) DataUtil.ParseForm(reqData, userBuyHouseForm.getClass());
		userBuyHouseService.addUserBuyHouse(userBuyHouseForm);
		return JsonReturn.ok();
    }
	
	/**
	 *  用户发布出租房源意向记录
	 */
	@RequestMapping(value = "/addUserLeaseHouse",method = RequestMethod.POST)
	@ResponseBody
    public JsonReturn addUserLeaseHouse(String reqData,UserLeaseHouseForm userLeaseHouseForm){
		userLeaseHouseForm = (UserLeaseHouseForm) DataUtil.ParseForm(reqData, userLeaseHouseForm.getClass());
		userBuyHouseService.addUserLeaseHouse(userLeaseHouseForm);
		return JsonReturn.ok();
    }
	
	/**
	 *  用户发布求租房源意向记录
	 */
	@RequestMapping(value = "/addUserRentHouse",method = RequestMethod.POST)
	@ResponseBody
    public JsonReturn addUserRentHouse(String reqData,UserRentHouseForm userRentHouseForm){
		userRentHouseForm = (UserRentHouseForm) DataUtil.ParseForm(reqData, userRentHouseForm.getClass());
		userBuyHouseService.addUserRentHouse(userRentHouseForm);
		return JsonReturn.ok();
    }
}
