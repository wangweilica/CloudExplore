package com.sunsoft.study.pattern.iterator;

import org.junit.Assert;

import org.junit.Test;

public class IteratorTest {
	
	@Test
	public void test() {
		String array[] = {"A","B","C","D","E"};  
		
		Collection<String> collection = new MyCollection(array);
		Iterator<String> iterator = collection.iterator();
		while(iterator.hasNext()) {
			String o = iterator.next();
			System.out.println(o);
		}
		String o = iterator.previous();
		Assert.assertEquals("D", o);
	}
	 
}
