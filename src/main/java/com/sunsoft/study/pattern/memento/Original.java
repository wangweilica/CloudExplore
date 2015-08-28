package com.sunsoft.study.pattern.memento;

public class Original {
	private String value;
	
	public Original(String value) {
		setValue(value);
	}
	
	public Memento createMemento() {
		return new Memento(value); 
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public void restoreMemento(Memento memento) {
		  this.value = memento.getValue();  
	}
}
