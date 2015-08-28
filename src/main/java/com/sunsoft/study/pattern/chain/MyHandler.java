package com.sunsoft.study.pattern.chain;

public class MyHandler extends AbstractHandler implements Handler {

	private String name;
	
	@SuppressWarnings("unused")
	private MyHandler() {}
	
	public MyHandler(String name) {
		this.name = name;
	}
	
	public void operation() {
		System.out.println(name + " started!");
		if (getHandler() != null) {
			getHandler().operation();
		}
		System.out.println(name + " ended!");
	}
}
