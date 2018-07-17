package com.dpp.rent.app.web.databind;

import java.beans.PropertyEditorSupport;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dpp.rent.app.api.constant.BusinessErrorCode;
import com.dpp.rent.app.api.constant.MSConstant;
import com.dpp.rent.app.api.exception.BizException;

public class JsonPropertyEditor extends PropertyEditorSupport {
	private static Log log = LogFactory.getLog(JsonPropertyEditor.class);
	
	private Class<?> targeClass;
	
	private HttpServletRequest request;
	
	public JsonPropertyEditor(Class<?> targeClass,HttpServletRequest request) {
		this.targeClass = targeClass;
		this.request = request;
	}
	
	public Object getValue() {
		String reqData = (String) request.getAttribute(MSConstant.REQDATA);
		if (StringUtils.isBlank(reqData)) {
			log.error("reqData is blank");
			throw new BizException(BusinessErrorCode.REQDATA_IS_NULL.getId());
		}
			
		try {
			JSONObject jsonObject = JSONObject.parseObject(reqData);
			reqData = jsonObject.toString();
			return JSON.parseObject(reqData, targeClass);
		} catch (Exception e) {
			log.error("Json parse error,reqData=" + reqData, e);
			throw new BizException(BusinessErrorCode.ILLEGAL_REQUEST.getId());
		}
	}
	

}
