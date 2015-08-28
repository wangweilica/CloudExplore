package com.sunsoft.study.pattern.decorator;

import org.junit.Test;

public class DecoratorTest {  
  
	@Test
    public void test() {  
        Sourceable source = new Source();  
        Sourceable obj = new Decorator(source);  
        obj.method();  
    }  
}  