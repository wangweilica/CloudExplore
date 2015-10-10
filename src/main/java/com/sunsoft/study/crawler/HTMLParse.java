package com.sunsoft.study.crawler;

import java.io.InputStream;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * @File: HTMLParse.java
 * @Date: 2015年10月9日
 * @Author: wwei
 * @Copyright: 版权所有 (C) 2015 
 *
 * @注意：本内容仅限于本人使用，禁止外泄以及用于其他的商业目的
 */
public class HTMLParse implements Parse {
	
	public void parse(InputStream input) {
		Document doc;
		try {
			doc = Jsoup.parse(input, "UTF-8", "");
			Element element = doc.getElementsByClass("x1h").get(0);
			Elements rows = element.getElementsByTag("tr");
			int size = rows != null ? rows.size() : 0;
			for (int i = 0; i < size; i++) {
				Element row = rows.get(i);
				Elements cols = i==0?row.getElementsByTag("th"):row.getElementsByTag("td");
				for(Element col : cols) {
					System.out.print(col.text() + "\t");
				}
				System.out.print("\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
