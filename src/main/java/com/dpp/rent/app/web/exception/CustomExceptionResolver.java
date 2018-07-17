package com.dpp.rent.app.web.exception;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.dpp.rent.app.api.constant.BusinessErrorCode;
import com.dpp.rent.app.api.exception.BizException;
import com.dpp.rent.app.api.model.JsonReturn;

/**
 * className:CustomExceptionResolver.java
 * description: 全局异常捕捉
 * date: 2018年6月30日
 * author:jpg
 */
public class CustomExceptionResolver implements HandlerExceptionResolver{
	
	private static Log log =LogFactory.getLog(CustomExceptionResolver.class);

	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		BusinessErrorCode bseCode = null;
		if (ex instanceof BizException ) {
			BizException bizException = (BizException) ex;
			String errorCode = bizException.getErrorCode();
			String errorMessage = BusinessErrorCode.getDes(errorCode);
			bseCode = BusinessErrorCode.getObject(errorCode);
			log.error("发生异常，errorCode:" + errorCode + ",errorMessage：" + errorMessage);
			ex.printStackTrace();
		} else {
			// 系统异常
			ex.printStackTrace();
			bseCode = BusinessErrorCode.getObject("SYSTEM_ERROR");
		}
		// 转成json
		Map<String, String> map = new HashMap<String, String>();
		map.put("errorCode", bseCode.getId());
		map.put("errorMsg", bseCode.getDescription());
		JsonReturn jsonReturn = JsonReturn.fail(bseCode.getId(),bseCode.getDescription());
        String msg = JSON.toJSONString(jsonReturn); 
        printWrite(msg,response);
        return new ModelAndView();
	}
	
	/**
	 * description: response返回参数
	 * retrun_type:void
	 * date: 2018年6月30日
	 * author:jpg
	 */
	public static void printWrite(String msg, HttpServletResponse response) {  
	     try{     
	       PrintWriter pw = response.getWriter();   
	       pw.write(msg);   
	       pw.flush();   
	       pw.close();  
	     } catch(Exception e) {    
	       e.printStackTrace();  
	     } 
	  }
}
