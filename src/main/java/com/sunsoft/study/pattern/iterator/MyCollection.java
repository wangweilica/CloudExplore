package com.sunsoft.study.pattern.iterator;

public class MyCollection implements Collection<String> {

	public String[] array;
	
	@SuppressWarnings("unused")
	private MyCollection() {};
	
	public MyCollection(String[] array) {
		this.array = array;
	}

	public Iterator<String> iterator() {
		return new MyIterator<String>(this);
	}

	public String get(int index) {
		return array[index];
	}

	public int size() {
		return array.length;
	}

}
