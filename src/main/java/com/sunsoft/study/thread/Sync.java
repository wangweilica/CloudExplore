package com.sunsoft.study.thread;

/**
 * 
 * @File: Sync.java
 * @Date: 2015年7月22日
 * @Author: wwei
 * @Copyright: 版权所有 (C) 2015 王伟所有.
 * synchronized锁住的是代码还是对象
 */
class Sync {
	/**
	 * 锁住的是调用这个方法的对象
	 */
	public synchronized void test1() {
		System.out.println("test开始..");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("test结束..");
	}

	/**
	 * 锁住的是调用synchronized括号中的对象
	 */
	public void test2() {
		synchronized (this) {
			System.out.println("test开始..");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("test结束..");
		}
	}
	
	/**
	 * 锁住的是这个类对应的Class对象。
	 */
	public void test3() {
		synchronized (Sync.class) {
			System.out.println("test开始..");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("test结束..");
		}
	}
	
	public void test4() {
		synchronized (Sync.class) {
			System.out.println("runnging...processss");
		};
	}
}