package com.sunsoft.study.pattern.iterator;

public interface Collection<T> {
	
	public Iterator<T> iterator();
	
	public T get(int index);
	
	public int size();
}
