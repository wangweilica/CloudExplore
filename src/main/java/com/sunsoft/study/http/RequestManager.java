package com.sunsoft.study.http;

import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

public class RequestManager
{
  private static ThreadSafeClientConnManager cm;
  private static RequestManager rm;
  private static HttpContext context;
  private static HttpClient httpClient;
  private static HttpParams httpParams;
  private static CookieStore cookieStore;
  private static ManagerConfig managerConfig;
  private static Log logger;

  private RequestManager()
  {
    if (cm == null) {
      cm = new ThreadSafeClientConnManager();

      cm.setMaxTotal(2000);

      cm.setDefaultMaxPerRoute(1000);

      cookieStore = new BasicCookieStore();

      context = new BasicHttpContext();
      context.setAttribute("http.cookie-store", cookieStore);

      httpParams = new BasicHttpParams();
      httpParams.setParameter("http.socket.timeout", 
        Integer.valueOf(6000)).setParameter(
        "http.connection.timeout", 
        Integer.valueOf(10000));

      httpClient = new DefaultHttpClient(cm, httpParams);

      logger = LogFactory.getLog(RequestManager.class);
    }
  }

  public void config(ManagerConfig config)
  {
    if (config == null) {
      return;
    }

    managerConfig = config;

    cm.setMaxTotal(config.getConnection());

    cm.setDefaultMaxPerRoute(config.getPreroute());

    httpParams.setParameter("http.socket.timeout", 
      Integer.valueOf(managerConfig.getSocketTimeout())).setParameter(
      "http.connection.timeout", 
      Integer.valueOf(managerConfig.getConnectionTimout()));

    httpClient = new DefaultHttpClient(cm, httpParams);
  }

  public static RequestManager getInstance()
  {
    if (rm == null)
    {
      rm = new RequestManager();
    }

    return rm;
  }

  public HttpResponse sendRequest(RequestObject reqObj)
  {
    HttpResponse response = null;

    String url = reqObj.toUrl();
    try
    {
      if (reqObj.getRequestType() == RequestType.GET) {
        HttpGet httpGet = new HttpGet(url);
        response = httpClient.execute(httpGet, context);
      }
      else if (reqObj.getRequestType() == RequestType.POST) {
        HttpPost httpPost = new HttpPost(url);

        httpPost.setEntity(reqObj.buildRequestEntity());

        response = httpClient.execute(httpPost, context);
      }
      else if (reqObj.getRequestType() == RequestType.PUT) {
        HttpPost httpPut = new HttpPost(url);

        reqObj.putParam("_method", RequestType.PUT.toString());
        httpPut.setEntity(reqObj.buildRequestEntity());

        response = httpClient.execute(httpPut, context);
      }
      else if (reqObj.getRequestType() == RequestType.DELETE) {
        HttpDelete httpDelete = new HttpDelete(url);

        reqObj.putParam("_method", RequestType.DELETE.toString());
        response = httpClient.execute(httpDelete, context);
      }
    }
    catch (ClientProtocolException e) {
      logger.error("Client protocol error " + e.toString());
    } catch (IOException e) {
      logger.error("Client I/O error " + e.toString());
    }

    return response;
  }

  public static String parseResponse(HttpResponse response)
  {
    String content = null;

    if (response == null) {
      return content;
    }

    int responseCode = response.getStatusLine().getStatusCode();
    if (responseCode == 200) {
      try {
        content = EntityUtils.toString(response.getEntity());
      } catch (ParseException e) {
        logger.error("Response parsing failed " + e.toString());
      } catch (IOException e) {
        logger.error("Response I/O error " + e.toString());
      }
    }
    else
    {
      logger.error(Integer.valueOf(responseCode));
    }

    return content;
  }

  public static ManagerConfig getManagerConfig()
  {
    return managerConfig;
  }

  public Log getLogger()
  {
    return logger;
  }
}