/*
 * Copyright Walker Studio
 * All Rights Reserved.
 * 
 * 文件名称： ManyTreeNode.java
 * 摘 要：
 * 作 者： Walker
 * 创建时间： 2013-03-19
 */
package com.sunsoft.study.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 多叉树节点
 *
 * @author Walker
 * @verion 1.0.0.0
 */
public class ManyTreeNode 
{
	/** 树节点*/
	private TreeNode data;
	/** 子树集合*/
	private List<ManyTreeNode> childList;
	
	/**
	 * 构造函数
	 * 
	 * @param data 树节点
	 */
	public ManyTreeNode(TreeNode data)
	{
		this.data = data;
		this.childList = new ArrayList<ManyTreeNode>();
	}
	
	/**
	 * 构造函数
	 * 
	 * @param data 树节点
	 * @param childList 子树集合
	 */
	public ManyTreeNode(TreeNode data, List<ManyTreeNode> childList)
	{
		this.data = data;
		this.childList = childList;
	}

	public TreeNode getData() {
		return data;
	}

	public void setData(TreeNode data) {
		this.data = data;
	}

	public List<ManyTreeNode> getChildList() {
		return childList;
	}

	public void setChildList(List<ManyTreeNode> childList) {
		this.childList = childList;
	}

}
