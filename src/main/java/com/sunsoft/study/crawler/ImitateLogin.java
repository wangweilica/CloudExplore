package com.sunsoft.study.crawler;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HeaderIterator;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.ParseException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.cookie.CookieSpecProvider;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.impl.cookie.BestMatchSpecFactory;
import org.apache.http.impl.cookie.BrowserCompatSpecFactory;
import org.apache.http.util.EntityUtils;
import org.junit.Assert;

import com.sunsoft.study.http.RequestObject;
import com.sunsoft.study.http.RequestType;


public class ImitateLogin {
	// 1282513548
	private String userName = "P0055912";
	// w11112222_
	private String password = "111222";
	private String loginUrl = "http://expenses.pactera.com:8088/OA_HTML/single_login.jsp";
	private String loginErrorUrl = loginUrl ;
	private String dataUrl = "http://expenses.pactera.com:8088/OA_HTML/OA.jsp?OAFunc=OAHOMEPAGE";
	// 创建HttpClientContext实例
	private static HttpClientContext context = null;
	// 创建CookieStore实例
	private static CookieStore cookieStore = null;
			
	private static CloseableHttpClient httpClient;
	
	public ImitateLogin() {}
	
	private static CloseableHttpClient getInstance() {
		if (httpClient == null) {
			httpClient = HttpClients.custom()
					.setDefaultCookieStore(cookieStore).build();
		}
		return httpClient;
	}
	
	public void HttpClientLogin() throws Exception {
		cookieStore = new BasicCookieStore();
		// 自动注入cookieStore
		httpClient = getInstance();
		RequestObject o = new RequestObject();
		o.putParam("username", userName)
		 .putParam("password", password)
		 .putParam("p_langCode", "ZHS");
		o.setRequestType(RequestType.POST);
		o.setPath(loginUrl);
		HttpPost httpPost = new HttpPost(loginUrl);
		httpPost.setHeader("VerificationToken", "_xMCYreWm3lBNCHsDdoz4R3KEq_bn-VF8XqC3CoaD3E25x6T7q16W3b_1lJIPiigKOVWfdcExbhfFi_I-YJqgzSRVe01:8_qUrm4plomjAqrnJb9xZ29bVx37yvhkU1jCEb2uPC7CMqO4Cdbrh9dBwBYIkjJsgMk4S5so_gBPIUlMwOnO5_BhBHU1");
		httpPost.setEntity(o.buildRequestEntity());
		HttpResponse httpResponse  = httpClient.execute(httpPost, context);
		Assert.assertNotNull("登陆失败！", httpResponse.getFirstHeader("Location"));
		String location1 = httpResponse.getFirstHeader("Location").getValue();
		Assert.assertTrue("登陆失败！即将跳往："+location1, location1 != null && !location1.startsWith(loginErrorUrl));
		
		int i = 0;
		int status = httpResponse.getStatusLine().getStatusCode();
        boolean flag = true;
		 if ((status == HttpStatus.SC_MOVED_TEMPORARILY) ||
	                (status == HttpStatus.SC_MOVED_PERMANENTLY) ||
	                (status == HttpStatus.SC_SEE_OTHER) ||
	                (status == HttpStatus.SC_TEMPORARY_REDIRECT)){
	            flag = true;
	        }else{
	            flag = false;
	        }
	    
	       
		while (flag) {
			i++;
			System.out.println("第" + i + "步");
			if (location1 != null) {
				HttpGet httpGet = new HttpGet(location1);
				CloseableHttpResponse  response = httpClient.execute(httpGet, context);
				status = response.getStatusLine().getStatusCode();
				if ((status == HttpStatus.SC_MOVED_TEMPORARILY)
						|| (status == HttpStatus.SC_MOVED_PERMANENTLY)
						|| (status == HttpStatus.SC_SEE_OTHER)
						|| (status == HttpStatus.SC_TEMPORARY_REDIRECT)) {
					flag = true;
				} else {
					flag = false;
				}
			  response.close();
			}
		}
		 
		 
		 
		// setCookieStore(httpResponse);
		  setContext();
	}
	
	  public void getData() throws Exception {
		HttpClientLogin();
		// 使用context方式
		httpClient = getInstance();
		HttpGet httpGet = new HttpGet(dataUrl);
		// 执行get请求
		HttpResponse httpResponse = httpClient.execute(httpGet, context);
		InputStream input = getContent(httpResponse);
		Parse parse = new HTMLParse();
		parse.parse(input);
	  }
	 
	 
	 private InputStream getContent(HttpResponse response) {
		 HttpEntity entity = response.getEntity();
		 try {
			 if(entity != null) {
				 return entity.getContent();
			 }
		} catch (IllegalStateException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	 }
	 
	 /**
	  * 手动注入cookie
	  * @param httpResponse
	  */
	@SuppressWarnings("unused")
	private static void setCookieStore(HttpResponse httpResponse) {
		System.out.println("----setCookieStore");
		cookieStore = new BasicCookieStore();
		// JSESSIONID
		String setCookie = httpResponse.getFirstHeader("Set-Cookie").getValue();
		String JSESSIONID = setCookie.substring("JSESSIONID=".length(),
				setCookie.indexOf(";"));
		System.out.println("JSESSIONID:" + JSESSIONID);
		// 新建一个Cookie
		BasicClientCookie cookie = new BasicClientCookie("JSESSIONID",
				JSESSIONID);
		cookie.setVersion(0);
		cookie.setDomain("expenses.pactera.com");
		cookie.setPath("/");
		// cookie.setAttribute(ClientCookie.VERSION_ATTR, "0");
		// cookie.setAttribute(ClientCookie.DOMAIN_ATTR, "127.0.0.1");
		// cookie.setAttribute(ClientCookie.PORT_ATTR, "8080");
		// cookie.setAttribute(ClientCookie.PATH_ATTR, "/CwlProWeb");
		cookieStore.addCookie(cookie);
	}

	private static void setContext() {
		context = HttpClientContext.create();
		Registry<CookieSpecProvider> registry = RegistryBuilder
				.<CookieSpecProvider> create()
				.register(CookieSpecs.BEST_MATCH, new BestMatchSpecFactory())
				.register(CookieSpecs.BROWSER_COMPATIBILITY,
						new BrowserCompatSpecFactory()).build();
		context.setCookieSpecRegistry(registry);
		context.setCookieStore(cookieStore);
	}
	
	@SuppressWarnings("unused")
	private static void printResponse(HttpResponse httpResponse)
			throws ParseException, IOException {
		// 获取响应消息实体
		HttpEntity entity = httpResponse.getEntity();
		// 响应状态
		System.out.println("status:" + httpResponse.getStatusLine());
		System.out.println("headers:");
		HeaderIterator iterator = httpResponse.headerIterator();
		while (iterator.hasNext()) {
			System.out.println("\t" + iterator.next());
		}
		// 判断响应实体是否为空
		if (entity != null) {
			String responseString = EntityUtils.toString(entity);
			System.out.println("response length:" + responseString.length());
		}
	}
}