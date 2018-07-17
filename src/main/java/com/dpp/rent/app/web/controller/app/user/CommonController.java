package com.dpp.rent.app.web.controller.app.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dpp.rent.app.api.domain.Cooperation;
import com.dpp.rent.app.api.model.CommonForm;
import com.dpp.rent.app.api.model.JsonReturn;
import com.dpp.rent.app.api.model.request.LoginForm;
import com.dpp.rent.app.api.model.request.SmsForm;
import com.dpp.rent.app.api.model.response.AreaResponse;
import com.dpp.rent.app.api.model.response.CooperationResponse;
import com.dpp.rent.app.api.model.response.TradeAreaResponse;
import com.dpp.rent.app.api.service.CommonService;
import com.dpp.rent.app.web.util.DataUtil;
import com.dpp.rent.service.dao.area.CooperationDao;

/**
 * className:CommonController.java
 * description: 公共业务入口
 * date: 2018年6月24日
 * author:jpg
 */
@Controller
public class CommonController {
	private static Log log = LogFactory.getLog(CommonController.class);
	
	@Autowired
	private CommonService commonService;
	
	@Autowired
	private CooperationDao cooperationDao;
	
	/**
	 * 发送短信入口
	 */
	@RequestMapping(value = "/sendSms",method = RequestMethod.POST)
	@ResponseBody
    public JsonReturn login(String reqData,SmsForm smsForm){
		smsForm = (SmsForm) DataUtil.ParseForm(reqData, smsForm.getClass());
		commonService.sendSms(smsForm);;
		return JsonReturn.ok();
    }
	
	/**
	 * 获取所属区域商圈
	 */
	@RequestMapping(value = "/getTradeArea",method = RequestMethod.POST)
	@ResponseBody
   public JsonReturn getTradeArea(){
		TradeAreaResponse response = commonService.getAllTradeArea();
		return JsonReturn.ok(response);
   }
	
	/**
	 * 获取商圈id中小区
	 */
	@RequestMapping(value = "/getCooperation",method = RequestMethod.POST)
	@ResponseBody
   public JsonReturn getCooperation(HttpServletRequest request){
		String tradeId = request.getParameter("tradeId");
		String cooName = request.getParameter("cooName");
		Map<String,String> map = new HashMap<String, String>();
		map.put("tradeId", tradeId);
		map.put("cooName", cooName);
		CooperationResponse response = new CooperationResponse();
		List<Cooperation> list =  cooperationDao.getAllCooperation(map);
		response.setCooperation(list);
		DataUtil.responseDataToJson(response,request);
		return JsonReturn.ok(response);
	}
	
	/**
	 * 新增小区
	 */
	@RequestMapping(value = "/addCooperation",method = RequestMethod.POST)
	@ResponseBody
   public JsonReturn addCooperation(HttpServletRequest request){
		
		
		String cooName = request.getParameter("cooName");
		String cooAddress = request.getParameter("cooAddress");
		String tradeId = request.getParameter("tradeId");
		String remark = request.getParameter("remark");
		String maxCode = cooperationDao.getMaxCooId();
		String cooId = null;
		if (StringUtils.isBlank(maxCode)) {
			// 为空则从0001开始
			cooId = "00000001";
		} else {
			// 则自动加1并且格式化成4位数，前面补0
			int res = Integer.valueOf(maxCode)+1;
			cooId =  String.format("%08d", res);
		}
		Cooperation cooperation = new Cooperation();
		cooperation.setCooId(cooId);
		cooperation.setCooName(cooName);
		cooperation.setCooAddress(cooAddress);
		cooperation.setStatus("0");
		cooperation.setTradeId(tradeId);
		cooperation.setRemark(remark);
		cooperationDao.saveCooperation(cooperation);
		return JsonReturn.ok();
   }
	
	
	
	
	
//	/**
//	 * 获取所有区域
//	 */
//	@RequestMapping(value = "/getArea",method = RequestMethod.POST)
//	@ResponseBody
//    public JsonReturn getArea(HttpServletRequest request){
//		AreaResponse response = commonService.getAllArea();
//		DataUtil.responseDataToJson(response,request);
//		return JsonReturn.ok(response);
//    }
//	
//	/**
//	 * 获取所属区域商圈
//	 */
//	@RequestMapping(value = "/getTradeArea",method = RequestMethod.POST)
//	@ResponseBody
//    public JsonReturn getTradeArea(HttpServletRequest request){
//		String areaId = request.getParameter("areaId");
//		TradeAreaResponse response = commonService.getTradeArea(areaId);
//		DataUtil.responseDataToJson(response,request);
//		return JsonReturn.ok(response);
//    }
//	
//	/**
//	 * 获取商圈id中小区
//	 */
//	@RequestMapping(value = "/getCooperation",method = RequestMethod.POST)
//	@ResponseBody
//    public JsonReturn getCooperation(HttpServletRequest request){
//		String tradeId = request.getParameter("tradeId");
//		CooperationResponse response = new CooperationResponse();
//		List<Cooperation> list =  cooperationDao.getAllCooperation(tradeId);
//		response.setCooperation(list);
//		DataUtil.responseDataToJson(response,request);
//		return JsonReturn.ok(response);
//    }
}
