package com.sunsoft.study.pattern.factory.Abstract;

import com.sunsoft.study.pattern.factory.General.Sender;

public class Test {  
  
	@org.junit.Test
    public void test() {  
        Provider provider = new SendMailFactory();  
        Sender sender = provider.produce();  
        sender.Send();  
    }  
}  