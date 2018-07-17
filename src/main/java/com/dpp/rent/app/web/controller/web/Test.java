package com.dpp.rent.app.web.controller.web;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;

public class Test {

	/**
	 * 本地上传
	 */
	public static void main(String[] args) {
		
		Configuration cfg = new Configuration(Zone.zone0());
		//...其他参数参考类注释
		UploadManager uploadManager = new UploadManager(cfg);
		//...生成上传凭证，然后准备上传
		String accessKey = "ztLPdPrJBAJdRC8JlRscLAfs_slmv5Izkri1cs0l";
		String secretKey = "2BMYr_D8JJN-qVdksdCwTBA5lgBYyWRa90DF6ItS";
		String bucket = "jpg1";
		//如果是Windows情况下，格式是 D:\\qiniu\\test.png
//		String localFilePath = "/home/qiniu/test.png";
		String localFilePath = "D:\\cs\\img\\1.png";
		//默认不指定key的情况下，以文件内容的hash值作为文件名
		String key = "365/0715/07151632.png";
		Auth auth = Auth.create(accessKey, secretKey);
		String upToken = auth.uploadToken(bucket);
//		upToken =  "ztLPdPrJBAJdRC8JlRscLAfs_slmv5Izkri1cs0l:Oy77VtQhhJAXu5swMsNe9bQFJN4=:eyJzY29wZSI6ImpwZzEiLCJkZWFkbGluZSI6MTUzMTY1MDM5MX0=";
		upToken = "ztLPdPrJBAJdRC8JlRscLAfs_slmv5Izkri1cs0l:QvICxBOSmpkUXeYnkYlu54iH6_4=:eyJzY29wZSI6ImpwZzEiLCJkZWFkbGluZSI6MTUzMTY1MDY1NH0=";
		System.out.println("token:" + upToken);
		
		try {
		    Response response = uploadManager.put(localFilePath, key, upToken);
		    //解析上传成功的结果
		    DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
		    System.out.println(putRet.key);
		    System.out.println(putRet.hash);
		} catch (QiniuException ex) {
		    Response r = ex.response;
		    System.err.println(r.toString());
		    try {
		        System.err.println(r.bodyString());
		    } catch (QiniuException ex2) {
		        //ignore
		    }
		}
	}

}
