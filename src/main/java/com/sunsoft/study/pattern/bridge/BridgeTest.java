package com.sunsoft.study.pattern.bridge;

import org.junit.Test;

public class BridgeTest {

	@Test
	public void test() {
		Bridge bridge = new Bridge();
		
		Sourceable source1 = new SourceSub1();
		bridge.setSource(source1);
		bridge.method();
		
		Sourceable source2 = new SourceSub2();
		bridge.setSource(source2);
		bridge.method();
	}
	
}
