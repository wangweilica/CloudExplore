package com.sunsoft.study.pattern.strategy;

public class Multiply extends AbstactCalculator implements ICalculator {

	public Integer calculate(String exp) {
		int[] result = split(exp, "\\*");
		if (result != null) return result[0] * result[1];
		return null;
	}
	
}
