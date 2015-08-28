package com.sunsoft.study.proxy.cglib;

import org.junit.Test;


public class TestCglib {
	
	@Test
	public void test() {
		BookFacadeCglib proxy = new BookFacadeCglib();
		BookFacadeImpl1 BookFacade = (BookFacadeImpl1)proxy.getInstance(new BookFacadeImpl1());
		BookFacade.addBook();
	}
}
