package com.sunsoft.study;

public class Test {
	public static void main(String[] args) {
		String a = "";
		for(int i = 0 ;i < 65534;i++) {
			a += i+"";
		}
		System.out.println(a.length());
	}
}
