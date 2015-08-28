package com.sunsoft.study.proxy.jdk;

import org.junit.Test;

public class TestProxy {
	
	@Test
	public void test() {
		 BookFacadeProxy proxy = new BookFacadeProxy(); 
	     BookFacade bookFacadeProxy = (BookFacade) proxy.bind(new BookFacadeImpl());
	     Book bookProxy = (Book) proxy.bind(new BookFacadeImpl());
	     bookProxy.read();
	     bookFacadeProxy.addBook();
	}
}	
