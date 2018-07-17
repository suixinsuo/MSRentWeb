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
import com.dpp.rent.app.api.model.request.agent.AddAgentForm;
import com.dpp.rent.app.api.model.request.agent.AddAgenthouseForm;
import com.dpp.rent.app.api.model.request.agent.SearchAgentForm;
import com.dpp.rent.app.api.model.response.AgentResponse;
import com.dpp.rent.app.api.service.agent.AgentService;
import com.dpp.rent.app.web.util.DataUtil;

@Controller
public class AgentManagerController {
	private static Log log = LogFactory.getLog(AgentManagerController.class);
	
	@Autowired
	private AgentService agentService;
	/**
	 * 新增经纪人
	 */
	@RequestMapping(value = "/addAgent",method = RequestMethod.POST)
	@ResponseBody
    public JsonReturn addAgent(String reqData,AddAgentForm addAgentForm,HttpServletRequest request){
		addAgentForm = (AddAgentForm) DataUtil.ParseForm(reqData, addAgentForm.getClass());
		agentService.addAgent(addAgentForm);
		return JsonReturn.ok();
    }
	
	/**
	 * 查询经纪人
	 */
	@RequestMapping(value = "/getAgent",method = RequestMethod.POST)
	@ResponseBody
    public JsonReturn getAgent(String reqData,SearchAgentForm searchAgentForm,HttpServletRequest request){
		searchAgentForm = (SearchAgentForm) DataUtil.ParseForm(reqData, searchAgentForm.getClass());
		AgentResponse response = agentService.getAgent(searchAgentForm);
		return JsonReturn.ok(response);
    }
	
	
	
	

}
