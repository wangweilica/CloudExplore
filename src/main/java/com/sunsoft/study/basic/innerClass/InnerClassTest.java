package com.sunsoft.study.basic.innerClass;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.sunsoft.study.basic.innerClass.MainExample.Test1;

public class InnerClassTest {
	 
	@Test
	public void test() {
		MainExample example = new MainExample();
		Test1 a = example.new Test1();
		assertTrue("luffyke".equals(a.getName()));
		System.out.println("ĞÕÃû:" + example.showName());
		System.out.println("ÄêÁä:" + example.showAge());
	}
}
