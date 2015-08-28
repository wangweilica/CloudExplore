package com.sunsoft.study.pattern.composite;

import java.util.Iterator;

public class Tree {
	TreeNode root = null;

	public Tree(String name) {
		root = new TreeNode(name);
	}
	
	public String iteratorTree(TreeNode tree)
	{
		StringBuilder buffer = new StringBuilder();
		if(tree != null) 
		{	
			buffer.append(tree.getName()+"\n");
			Iterator<TreeNode> iterator = tree.getChildren();
			while(iterator.hasNext()) {
				TreeNode node = iterator.next();
				
				if (node.getChildren() != null && node.getChildren().hasNext()) 
				{	
					buffer.append(iteratorTree(node));
				} else {
					buffer.append(node.getName());
				}
			}
		}
		
		buffer.append("\n");
		
		return buffer.toString();
	}
}
