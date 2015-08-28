package com.sunsoft.study.pattern.factory.Abstract;

import com.sunsoft.study.pattern.factory.General.MailSender;
import com.sunsoft.study.pattern.factory.General.Sender;

public class SendMailFactory implements Provider {

	public Sender produce() {
		return new MailSender();
	}
}