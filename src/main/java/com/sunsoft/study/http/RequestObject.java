package com.sunsoft.study.http;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;

public class RequestObject
{
  private static Log logger;
  private String scheme;
  private String host;
  private ArrayList<NameValuePair> params = new ArrayList<>();
  private RequestType requestType;
  private int port;
  private String path;
  private String encoding;
  private RequestEntityType requestEntityType;

  public RequestObject()
  {
    if (logger == null) {
      logger = LogFactory.getLog(RequestObject.class);
    }

    this.encoding = "utf-8";
  }

  public String toUrl()
  {
    StringBuilder builder = new StringBuilder();
    builder.append(this.scheme).append("://").append(this.host).append(":")
      .append(this.port);

    if (!this.path.startsWith("/")) {
      builder.append("/");
    }

    if (this.path != null) {
      builder.append(this.path);
    }

    if (((this.requestType == RequestType.GET ? 1 : 0) | (
      this.requestType == RequestType.DELETE ? 1 : 0)) != 0) {
      builder.append("?");

      int paramSize = this.params.size();
      if (this.params != null) {
        for (int i = 0; i < paramSize - 1; i++) {
          builder.append(((NameValuePair)this.params.get(i)).getName()).append("=")
            .append(((NameValuePair)this.params.get(i)).getValue()).append("&");
        }
        builder.append(((NameValuePair)this.params.get(paramSize - 1)).getName()).append("=")
          .append(((NameValuePair)this.params.get(paramSize - 1)).getValue());
      }

    }

    return builder.toString();
  }

  public RequestObject putParam(String name, String value)
  {
    this.params.add(new BasicNameValuePair(name, value));
    return this;
  }

  @Deprecated
  public UrlEncodedFormEntity buildFormEntity()
  {
    UrlEncodedFormEntity entity = null;
    try
    {
      entity = new UrlEncodedFormEntity(this.params, this.encoding);
    } catch (UnsupportedEncodingException e) {
      logger.error("Unsupported coding format " + e.toString());
    }

    return entity;
  }

  public HttpEntity buildRequestEntity()
  {
    HttpEntity entity = null;

    if (this.requestEntityType == null) {
      this.requestEntityType = RequestEntityType.URL;
    }
    try
    {
        entity = new UrlEncodedFormEntity(this.params, this.encoding);

    }
    catch (Exception e)
    {
      logger.error("Unsupported coding format " + e.toString());
    }

    return entity;
  }

  public String getScheme()
  {
    return this.scheme;
  }

  public void setScheme(String scheme)
  {
    this.scheme = scheme;
  }

  public void setHost(String host)
  {
    this.host = host;
  }

  public RequestType getRequestType()
  {
    return this.requestType;
  }

  public void setRequestType(RequestType requestType)
  {
    this.requestType = requestType;
  }

  public void setPort(int port)
  {
    this.port = port;
  }

  public void setPath(String path)
  {
    this.path = path;
  }

  public void setEncoding(String encoding)
  {
    this.encoding = encoding;
  }

  public void setRequestEntityType(RequestEntityType requestEntityType)
  {
    this.requestEntityType = requestEntityType;
  }

  public static Log getLogger()
  {
    return logger;
  }
}