package com.dpp.rent.app.web.controller.web;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import com.dpp.rent.app.api.constant.MSConstant;
import com.dpp.rent.app.api.domain.Cooperation;
import com.dpp.rent.app.api.domain.User;
import com.dpp.rent.app.api.exception.BizException;
import com.dpp.rent.app.api.model.JsonReturn;
import com.dpp.rent.app.api.model.request.LoginForm;
import com.dpp.rent.app.api.model.response.LoginResponse;
import com.dpp.rent.app.api.service.user.UserService;
import com.dpp.rent.app.web.util.DataUtil;
import com.dpp.rent.service.dao.area.CooperationDao;
import com.dpp.rent.service.dao.user.UserDao;

/**
 * className:LoginController.java
 * description: 登录业务处理放在controller里处理
 * date: 2018年6月23日
 * author:jpg
 */
@Controller
public class WebLoginController {
	private static Log log = LogFactory.getLog(WebLoginController.class);
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private CooperationDao cooperationDao;
	
	/**
	 * 退出系统
	 */
	@RequestMapping(value = "/web_loginOut",method = RequestMethod.GET)
    public String web_loginOut(HttpServletRequest request,Model model){ 
		HttpSession session = request.getSession();
		session.invalidate();
		return "login";
	}
	
	/**
	 * 登录入口
	 */
	@RequestMapping(value = "/web_login",method = RequestMethod.POST)
    public String login(HttpServletRequest request,Model model){
		String id = request.getParameter("userName");
		String password = request.getParameter("password");
		if (StringUtils.isBlank(id) || StringUtils.isBlank(password)) {
			log.error("非法请求，参数为空");
			return "error";
		}
		// 获取user对象
		User user = userDao.getUser(id);
		if (user == null) {
			log.error(BusinessErrorCode.USER_IS_NOT_REGISTER.getDescription());
			model.addAttribute("userIdError","用户不存在");
			return "login";
		}
		// 非正常状态用户不允许登录
		if (!MSConstant.USER_NORMAL_STATUS.equals(user.getStatus())) {
			log.error(BusinessErrorCode.USER_IS_NOT_NORMAL.getDescription());
			model.addAttribute("userIdError","用户状态不正常");
			return "login";
		}
		
		// 判断用户密码是否正确
		if (!user.getPassword().equals(password)) {
			log.error("用户：" + id + "登录密码错误");
			model.addAttribute("userPwdError","用户密码错误");
			return "login";
		}
		HttpSession session = request.getSession();
		session.setMaxInactiveInterval(60*60);//单位为秒
		session.setAttribute("user", user);
		return "main";
    }
	
	/**
	 * 新增经纪人
	 */
	@RequestMapping(value = "/web_addManager",method = RequestMethod.POST)
	@ResponseBody
    public String addManager(HttpServletRequest request,Model model){
		String id = request.getParameter("mobile");
		// 判断是否注册过
		User ur = userDao.getUser(id);
		if (ur!=null) {
			log.info("用户已注册");
			return "1";
		}
		String type = request.getParameter("type");
		User useReq = new User();
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String linkCode = request.getParameter("linkCode");
		String tradeId = request.getParameter("tradeId");
		String cooId = request.getParameter("cooId");
		String remark = request.getParameter("remark");
		String cooName = request.getParameter("cooName");
		String cooAddress = request.getParameter("cooAddress");
		if ((StringUtils.isBlank(cooId)|| "-1".equals(cooId)) && StringUtils.isNotBlank(cooAddress) && StringUtils.isNotBlank(cooName)) {
			// 新增小区
			String maxCode = cooperationDao.getMaxCooId();
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
			cooperationDao.saveCooperation(cooperation);
		}
		if (!type.equals("0") && !type.equals("2") && !type.equals("6") ) {
			// 经纪人则要生成注册链接以及绑定小区
			useReq.setTradeId(tradeId);
			useReq.setCooId(cooId);
			useReq.setLinkCode(linkCode);
		}
		useReq.setId(id);
		useReq.setName(name);
		useReq.setPassword(password);
		useReq.setType(type);
		useReq.setStatus("1");
		useReq.setCreateDate(new Date());
		useReq.setRemark(remark);
		userDao.saveUser(useReq);
		return "0";
	}
		
	
	
}
