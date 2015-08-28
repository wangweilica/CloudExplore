package com.sunsoft.study.pattern.strategy;

import org.junit.Test;

public class StrategyTest {
	
	@Test
	public void test() {
		Integer result = 0;
		ICalculator calculator = new Plus();
		result = calculator.calculate("34+12");
		System.out.println(result);
	    calculator = new Multiply();
		result = calculator.calculate("345*543");
		System.out.println(result);
	}
}
