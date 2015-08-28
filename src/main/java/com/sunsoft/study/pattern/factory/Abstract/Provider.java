package com.sunsoft.study.pattern.factory.Abstract;

import com.sunsoft.study.pattern.factory.General.Sender;

public interface Provider {
	public Sender produce();
}