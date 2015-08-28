package com.sunsoft.study.pattern.strategy;

public abstract class AbstactCalculator {
	
	public int[] split(String exp, String option) {
		int[] result = new int[2];
		String[] array = exp.split(option);
		if(array != null) {
			result[0] = Integer.valueOf(array[0]);
			result[1] = Integer.valueOf(array[1]);		
		}
	
		return result;
	}
}
