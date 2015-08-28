package com.sunsoft.study.pattern.iterator;

public class MyIterator<T> implements Iterator<T> {

	public Collection<T> collection;
	
	public int pos = -1;
	
	public MyIterator(Collection<T> collection) {
		this.collection = collection;
	}
	
	public T previous() {
		if (pos <= 0) {
			System.out.println("excess range!");
		} 
		return collection.get(--pos);
	}

	public T next() {
		if (pos >= collection.size()-1) {
			System.out.println("excess range!");
		} 
		return collection.get(++pos);
	}

	public boolean hasNext() {
		if(pos<collection.size()-1) {
			return true;
		} else {
			return false;
		}
	}

	public T first() {
		return collection.get(0);
	}

}
