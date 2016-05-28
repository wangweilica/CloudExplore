package com.sunsoft.study.proxy.javassist;

public class Test {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BusiService c = new BusiService();
		Service i = (Service) DProxy.createProxy(BusiService.class,
				new MyInterceptor(c));
//		i.Action(123);
		double a = i.getObject("1");
		System.out.println(a);
	}


}
