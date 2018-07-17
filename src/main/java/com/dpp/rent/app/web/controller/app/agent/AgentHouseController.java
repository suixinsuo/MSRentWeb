package com.dpp.rent.app.web.controller.app.agent;

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
import com.dpp.rent.app.api.model.request.agent.AddAgenthouseForm;
import com.dpp.rent.app.api.model.request.agent.AgentHouseForm;
import com.dpp.rent.app.api.model.response.AgentHouseResponse;
import com.dpp.rent.app.api.model.response.LoginResponse;
import com.dpp.rent.app.api.service.agent.AgentHouseService;
import com.dpp.rent.app.api.service.user.UserService;
import com.dpp.rent.app.web.util.DataUtil;

/**
 * className:AgentHouseController.java
 * description: 经纪人房源处理
 * date: 2018年6月23日
 * author:jpg
 */
@Controller
public class AgentHouseController {
	private static Log log = LogFactory.getLog(AgentHouseController.class);
	
	@Autowired
	private AgentHouseService agentHouseService;
	/**
	 * 新增房源
	 */
	@RequestMapping(value = "/addAgentHouse",method = RequestMethod.POST)
	@ResponseBody
    public JsonReturn addAgentHouse(String reqData,AddAgenthouseForm addAgenthouseForm,HttpServletRequest request){
		addAgenthouseForm = (AddAgenthouseForm) DataUtil.ParseForm(reqData, addAgenthouseForm.getClass());
		agentHouseService.addAgentHouse(addAgenthouseForm);
		return JsonReturn.ok();
    }
	
	/**
	 * 查询房源
	 */
	@RequestMapping(value = "/getAgentHouse",method = RequestMethod.POST)
	@ResponseBody
    public JsonReturn getAgentHouse(String reqData,AgentHouseForm agentHouseForm,HttpServletRequest request){
		agentHouseForm = (AgentHouseForm) DataUtil.ParseForm(reqData, agentHouseForm.getClass());
		AgentHouseResponse response = agentHouseService.getAgentHouse(agentHouseForm);
		DataUtil.responseDataToJson(response,request);
		return JsonReturn.ok(response);
    }
}
