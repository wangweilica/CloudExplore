package com.sunsoft.study.pattern.memento;

import org.junit.Test;

public class MementoTest {
	
	@Test
	public void test() {
		Original original = new Original("egg");
		Storage storage = new Storage(original.createMemento());
		// 修改原始类的状态  
        System.out.println("初始化状态为：" + original.getValue());  
        original.setValue("niu");  
        System.out.println("修改后的状态为：" + original.getValue());  
        
        // 回复原始类的状态  
        original.restoreMemento(storage.getMemento());  
        System.out.println("恢复后的状态为：" + original.getValue());  
	}
}
