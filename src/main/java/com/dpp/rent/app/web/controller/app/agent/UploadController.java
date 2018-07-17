package com.dpp.rent.app.web.controller.app.agent;

import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dpp.rent.app.api.constant.BusinessErrorCode;
import com.dpp.rent.app.api.exception.BizException;
import com.dpp.rent.app.api.model.CommonForm;
import com.dpp.rent.app.api.model.JsonReturn;
import com.qiniu.util.Auth;

@Controller
public class UploadController {
	private static Log log = LogFactory.getLog(UploadController.class);
	// 七牛账号密钥
	private static String ACCESSKEY = null;
	private static String SECRETKEY = null;
	private static String BUCKET = null;
	
	static {
		ACCESSKEY = ResourceBundle.getBundle("config").getString("accessKey");
		SECRETKEY = ResourceBundle.getBundle("config").getString("secretKey");
		BUCKET = ResourceBundle.getBundle("config").getString("bucket");
	}
	
	@RequestMapping(value = "/getUploadToken",method = RequestMethod.POST)
	@ResponseBody
    public JsonReturn getUploadToken(String reqData,CommonForm commonForm,HttpServletRequest request){
		String upToken ="";
		try {
			Auth auth = Auth.create(ACCESSKEY, SECRETKEY);
			upToken = auth.uploadToken(BUCKET);
			log.info("token:"+ upToken + ",accessKey:" + ACCESSKEY +
					",secretKey:" + SECRETKEY + ",bucket:" + BUCKET);
		} catch (Exception e) {
			log.error(e);
			throw new BizException(BusinessErrorCode.QI_NIU_ISERROR.getId());
		} 
		return JsonReturn.ok(upToken);
    }

}
