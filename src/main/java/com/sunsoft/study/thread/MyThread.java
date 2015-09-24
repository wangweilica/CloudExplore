package com.sunsoft.study.thread;
class MyThread extends Thread {
	@SuppressWarnings("unused")
	private static Sync sync = new Sync();
	public void run() {
		Sync sync = new Sync();
		sync.test3();
	}
}