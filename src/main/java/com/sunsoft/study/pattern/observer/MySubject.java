package com.sunsoft.study.pattern.observer;

public class MySubject extends AbstractSubject {

	public void operation() {
		notifyObservers();
	}	
}
