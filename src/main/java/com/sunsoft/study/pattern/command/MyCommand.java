package com.sunsoft.study.pattern.command;

public class MyCommand implements Command {

	private Receiver receiver;
	
	@SuppressWarnings("unused")
	private MyCommand() {}
	
	public MyCommand(Receiver receiver) {
		this.receiver = receiver;
	}
	
	public void exe() {
		receiver.action();
	}

}
