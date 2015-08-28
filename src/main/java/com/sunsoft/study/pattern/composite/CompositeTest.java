package com.sunsoft.study.pattern.composite;

import org.junit.Test;

public class CompositeTest {
	
	@Test
	public void test() {
		Tree tree = new Tree("0");
		
		TreeNode nodeB = new TreeNode("00");
		TreeNode nodeC = new TreeNode("000");
		TreeNode nodeD = new TreeNode("01");
		TreeNode nodeE = new TreeNode("010");
		
		nodeB.add(nodeC);
		nodeD.add(nodeE);
		tree.root.add(nodeB);
		tree.root.add(nodeD);
		
		System.out.println(tree.iteratorTree(tree.root));
	}
}	
