package com.sunsoft.study.basic.innerClass;

public class MainExample {

	public class Test1 extends Example1 {
		public String getName() {
			return super.getName();
		}
	}

	private class Test2 extends Example2 {
		public int getAge() {
			return super.getAge();
		}
	}

	public String showName() {
		return new Test1().getName();
	}

	public int showAge() {
		return new Test2().getAge();
	}

}