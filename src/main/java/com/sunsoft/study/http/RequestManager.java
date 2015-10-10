package com.sunsoft.study.http;

import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

public class RequestManager
{
  private static HttpClientBuilder httpClientBuilder;
  private static RequestManager rm;
  private static CloseableHttpClient httpClient;
  private static RequestConfig requestConfig;
  private static ManagerConfig managerConfig;
  private static Log logger;

	private RequestManager() {
		if (httpClientBuilder == null) {
			httpClientBuilder = HttpClientBuilder.create();

			managerConfig = ManagerConfig.build();
			httpClientBuilder.setMaxConnTotal(managerConfig.getConnection());
			httpClientBuilder.setMaxConnPerRoute(managerConfig.getPreroute());

			requestConfig = RequestConfig.custom()
					.setCookieSpec(CookieSpecs.BEST_MATCH)
					.setSocketTimeout(managerConfig.getSocketTimeout())
					.setConnectTimeout(managerConfig.getConnectionTimout())
					.build();// 设置请求和传输超时时间
			httpClientBuilder.setDefaultRequestConfig(requestConfig);
			httpClient = httpClientBuilder.build();
			logger = LogFactory.getLog(RequestManager.class);
		}
	}

  public void config(ManagerConfig config)
  {
    if (config == null) {
    	return;
    }

    managerConfig = config;
    httpClientBuilder.setMaxConnTotal(config.getConnection());
    httpClientBuilder.setMaxConnPerRoute(config.getPreroute());
    httpClient = httpClientBuilder.build();
  
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
        response = httpClient.execute(httpGet);
      }
      else if (reqObj.getRequestType() == RequestType.POST) {
        HttpPost httpPost = new HttpPost(reqObj.getPath());
        httpPost.setEntity(reqObj.buildRequestEntity());

        response = httpClient.execute(httpPost);
      }
      else if (reqObj.getRequestType() == RequestType.PUT) {
        HttpPost httpPut = new HttpPost(reqObj.getPath());
        reqObj.putParam("_method", RequestType.PUT.toString());
        httpPut.setEntity(reqObj.buildRequestEntity());

        response = httpClient.execute(httpPut);
      }
      else if (reqObj.getRequestType() == RequestType.DELETE) {
        HttpDelete httpDelete = new HttpDelete(url);
        reqObj.putParam("_method", RequestType.DELETE.toString());
        response = httpClient.execute(httpDelete);
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
      System.out.println(responseCode);
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