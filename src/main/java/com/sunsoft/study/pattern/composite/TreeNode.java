package com.sunsoft.study.pattern.composite;

import java.util.Iterator;
import java.util.Vector;

public class TreeNode {
	
	private String name;
	
	private TreeNode parent;
	
	private Vector<TreeNode> children = new Vector<TreeNode>();

	public TreeNode(String name){  
        this.name = name;  
    }  
	
	public void add(TreeNode node) {
		children.addElement(node);
	}
	
	public void remove(TreeNode node) {
		children.remove(node);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public TreeNode getParent() {
		return parent;
	}

	public void setParent(TreeNode parent) {
		this.parent = parent;
	}

	public Iterator<TreeNode> getChildren() {
		return children.iterator();
	}
}
