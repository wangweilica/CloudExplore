package com.sunsoft.study.pattern.observer;

import org.junit.Test;

public class ObserverTest {
	
	@Test
	public void test() {
		Subject subject = new MySubject();
		Observer o1 = new Observer1();
		Observer o2 = new Observer2();
		subject.add(o1);
		subject.add(o2);
		
		subject.operation();
	}
}
