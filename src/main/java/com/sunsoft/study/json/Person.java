package com.sunsoft.study.json;

import java.io.Serializable;

public class Person implements Serializable{
	
	private static final long serialVersionUID = 4967976257909412025L;

	private String name;
	
	private Integer age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}
	
	
}
