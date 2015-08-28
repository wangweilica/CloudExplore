package com.sunsoft.study.pattern.factory.General;
public class Test {  
  
	@org.junit.Test
    public void test() {  
        SendFactory factory = new SendFactory();  
        Sender sender = factory.produce("sms");  
        sender.Send();  
    }  
}  