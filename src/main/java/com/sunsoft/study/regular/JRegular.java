package com.sunsoft.study.regular;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JRegular {
	
	public static void main(String[] args) {
		System.out.println("ab234cdef".replaceAll("\\w{2}(?!$)", "$0\\."));
		Pattern p=Pattern.compile("\\w{2}(?!$)");
		Matcher matcher = p.matcher("ab234cdef");
		while(matcher.find()) {
			System.out.println(matcher.start());
		}
	}
}
