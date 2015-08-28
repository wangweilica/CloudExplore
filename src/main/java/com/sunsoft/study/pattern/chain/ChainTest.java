package com.sunsoft.study.pattern.chain;

import org.junit.Test;

public class ChainTest {
	
	@Test
	public void test() {
		MyHandler handler1 = new MyHandler("handler1");
		MyHandler handler2 = new MyHandler("handler2");
		MyHandler handler3 = new MyHandler("handler3");
		handler1.setHandler(handler2);
		handler2.setHandler(handler3);
		handler1.operation();
		
	}
}

