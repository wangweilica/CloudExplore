package com.sunsoft.study.pattern.factory.Abstract;

import com.sunsoft.study.pattern.factory.General.Sender;
import com.sunsoft.study.pattern.factory.General.SmsSender;

public class SendSmsFactory implements Provider {

	public Sender produce() {
		return new SmsSender();
	}
}