package com.sunsoft.study.http;

import net.sf.json.JSONObject;

import org.apache.http.HttpResponse;

/**
 * @File: HttpUtil.java
 * @Date: 2015年9月22日
 * @Author: wwei
 * @Copyright: 版权所有 (C) 2015 
 *
 * @注意：本内容仅限于本人使用，禁止外泄以及用于其他的商业目的
 */
public class HttpUtil {
	
	public static JSONObject  send(){
		
		RequestObject obj = init("112.33.1.160",81,"/healthcare_service_restful/s/userService/getUserByUid");
		
		HttpResponse response = RequestManager.getInstance().sendRequest(obj);
		String responseMsg = RequestManager.parseResponse(response);
		JSONObject json=JSONObject.fromObject(responseMsg);
		
		return json;
	}
	
	 /**
	  * @param host
	  * @param port
	  * @param path
	  * @param vo
	  * @return
	  */
	private static RequestObject init(String host, int port, String path) {
		RequestObject obj = new RequestObject();
		obj.setRequestType(RequestType.POST);

		obj.setScheme("http");
		obj.setHost(host);
		obj.setPath(path);
		obj.setPort(port);
		obj.setEncoding(RequestConstants.ENCODING_UTF_8);
		obj.putParam("invokeKey", "[HAIJK-S]nHtIktPPQM")
				.putParam("appKey", "HAIJK").putParam("remoteAppKey", "HAIJK")
				.putParam("uid", "wanggang");

		
		return obj;
	}

	public static void main(String[] args) {
		JSONObject object = send();
		System.out.println(object.toString());
	}
}
