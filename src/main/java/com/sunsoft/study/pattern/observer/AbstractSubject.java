package com.sunsoft.study.pattern.observer;

import java.util.Iterator;
import java.util.Vector;

public abstract class AbstractSubject implements Subject {
	
	private Vector<Observer> vector = new Vector<Observer>();
	
	public void add(Observer observer) {
		vector.add(observer);
	}
	
	public void del(Observer observer) {
		vector.remove(observer);
	}
	
	public void notifyObservers() {
		Iterator<Observer> iterator = vector.iterator();
		while (iterator.hasNext()) {
			Observer observer = iterator.next();
			observer.update();
		}
	}
}
