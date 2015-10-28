package com.sunsoft.study;

import org.junit.Test;

import com.sunsoft.study.crawler.ImitateLogin;

public class CrawlerTest {
	
	@Test
	public void testGetData() throws Exception {
		ImitateLogin login = new ImitateLogin();
		login.getData();
	}
}
