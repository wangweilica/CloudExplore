package com.sunsoft.study.http;

import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;

/**
 * @File: RequestObject.java
 * @Date: 2015年9月29日
 * @Author: wwei
 * @Copyright: 版权所有 (C) 2015
 *
 * @注意：本内容仅限于本人使用，禁止外泄以及用于其他的商业目的
 */
public class RequestObject {
	private static Log logger;

	// 参数
	private ArrayList<NameValuePair> params = new ArrayList<>();

	private String encoding;

	private RequestType requestType;

	private RequestEntityType requestEntityType;

	// 访问路径
	private String path;

	public RequestObject() {
		if (logger == null) {
			logger = LogFactory.getLog(RequestObject2.class);
		}

		this.encoding = "utf-8";
	}

	public String toUrl() {
		StringBuilder builder = new StringBuilder();

		if (this.path != null) {
			builder.append(this.path);
		}

		builder.append("?");

		int paramSize = this.params.size();
		if (this.params != null) {
			for (int i = 0; i < paramSize - 1; i++) {
				builder.append(((NameValuePair) this.params.get(i)).getName())
						.append("=")
						.append(((NameValuePair) this.params.get(i)).getValue())
						.append("&");
			}
			builder.append(
					((NameValuePair) this.params.get(paramSize - 1)).getName())
					.append("=")
					.append(((NameValuePair) this.params.get(paramSize - 1))
							.getValue());
		}

		return builder.toString();
	}

	public RequestObject putParam(String name, String value) {
		this.params.add(new BasicNameValuePair(name, value));
		return this;
	}

	public HttpEntity buildRequestEntity() {
		HttpEntity entity = null;

		if (this.requestEntityType == null) {
			this.requestEntityType = RequestEntityType.URL;
		}
		try {
			entity = new UrlEncodedFormEntity(this.params, this.encoding);

		} catch (Exception e) {
			logger.error("Unsupported coding format " + e.toString());
		}

		return entity;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	public RequestType getRequestType() {
		return requestType;
	}

	public void setRequestType(RequestType requestType) {
		this.requestType = requestType;
	}

	public RequestEntityType getRequestEntityType() {
		return requestEntityType;
	}

	public void setRequestEntityType(RequestEntityType requestEntityType) {
		this.requestEntityType = requestEntityType;
	}
}