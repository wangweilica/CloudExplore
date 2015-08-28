package com.sunsoft.study.pattern.command;

import org.junit.Test;

public class CommandTest {
	
	@Test
	public void test() {
		Receiver receiver = new Receiver();
		Command command = new MyCommand(receiver);
		Invoker invoker = new Invoker(command);
		invoker.action();
	}
}
