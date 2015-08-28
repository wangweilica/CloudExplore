package com.sunsoft.study.pattern.command;

/**
 * 调用者（司令员）
 * @File: Invoker.java
 * @Date: 2015年5月13日
 * @Author: wwei
 * @Copyright: 版权所有 (C) 2015 中国移动 杭州研发中心.
 *
 * @注意：本内容仅限于中国移动内部传阅，禁止外泄以及用于其他的商业目的
 */
public class Invoker {
	private Command command;
		
	@SuppressWarnings("unused")
	private Invoker() {}
	
	public Invoker(Command command) {
		this.command = command;
	}
	
	public void action() {
		command.exe();
	}
}
