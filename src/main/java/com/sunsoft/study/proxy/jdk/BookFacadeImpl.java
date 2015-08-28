package com.sunsoft.study.proxy.jdk;

public class BookFacadeImpl implements BookFacade,Book {

	public void addBook() {
		System.out.println("增加图书方法。。。");  
	}

	public void read() {
		System.out.println("阅读图书方法。。。");  
	}

}
