package com.dpp.rent.app.web.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dpp.rent.app.api.constant.BusinessErrorCode;
import com.dpp.rent.app.api.constant.MSConstant;
import com.dpp.rent.app.api.exception.BizException;

/**
 * className:ParseForm.java
 * description: 将json转成form
 * date: 2018年6月30日
 * author:jpg
 */
public class DataUtil {
	private static Log log = LogFactory.getLog(DataUtil.class);
	
	/**
	 * description: 将json转成form
	 * retrun_type:Object
	 * date: 2018年6月30日
	 * author:jpg
	 */
	public static Object ParseForm(String reqData,Class<?> targeClass) {
		if (StringUtils.isBlank(reqData)) {
			throw new BizException(BusinessErrorCode.REQDATA_IS_NULL.getId());
		}
		Object object  = null;
		try {
			object  = JSON.parseObject(reqData, targeClass);
		} catch (Exception e) {
			throw new BizException(BusinessErrorCode.REQDATA_PARSEOBJECT_IS_NULL.getId());
		}
		return object;
	}
	
	
	public static void responseDataToJson(Object object,HttpServletRequest request) {
		request.setAttribute(MSConstant.REP_DATA, object);
	}
	
	
}
