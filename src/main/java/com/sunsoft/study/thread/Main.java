package com.sunsoft.study.thread;
public class Main {

	public static void main1() {
		for (int i = 0; i < 3; i++) {
			Thread thread = new MyThread();
			thread.start();
		}
	}
	public static void main2() {
		Thread thread = new Thread() {
			@Override
			public void run() {
				Sync sync = new Sync();
				sync.test4();
			}
		};
		
		thread.start();
	}
	
	public static void main(String[] args) {
		main1();
		main2();
	}
}