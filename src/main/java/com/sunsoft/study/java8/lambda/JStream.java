package com.sunsoft.study.java8.lambda;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.Test;


public class JStream {
	
	@Test
	public void test1() {
		List<Integer> nums =  Arrays.asList(1, 2, null, 3, 5, 6);
		Long a = nums.stream().filter(num -> nums!=null).count();
		System.out.println(a);
		Stream.iterate(10, item1 -> item1 + 1).limit(10).forEach(System.out::println);

	}
}
