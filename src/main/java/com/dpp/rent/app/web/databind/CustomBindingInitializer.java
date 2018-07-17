package com.dpp.rent.app.web.databind;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import com.dpp.rent.app.api.model.BaseForm;

public class CustomBindingInitializer implements WebBindingInitializer{
	
	private static Log log = LogFactory.getLog(CustomBindingInitializer.class);
	
	@Override
	public void initBinder(WebDataBinder binder, WebRequest request) {
		Object  target = binder.getTarget();
		if (target instanceof BaseForm) {
			HttpServletRequest servletRequest = (HttpServletRequest) ((ServletWebRequest)request).getNativeRequest();
			binder.registerCustomEditor(target.getClass(), new JsonPropertyEditor(target.getClass(),servletRequest));
		}
	}

}
