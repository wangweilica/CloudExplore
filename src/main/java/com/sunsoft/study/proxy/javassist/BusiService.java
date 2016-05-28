package com.sunsoft.study.proxy.javassist;

public class BusiService implements Service {

	@Override
	public void Action(int a) {
		System.out.println("do Action" + a);
	}

	@Override
	public Double getObject(String s) {
		System.out.println(s);
		return Math.random();
	}
}