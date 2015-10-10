package com.sunsoft.study.crawler;

import java.io.InputStream;

public interface Parse {
	
	/**
	 * 解析流数据
	 * @param input
	 */
	void parse (InputStream input) ;
}
