/*
 * Copyright Walker Studio
 * All Rights Reserved.
 * 
 * 文件名称： TreeNode.java
 * 摘 要：
 * 作 者： Walker
 * 创建时间： 2013-03-19
 */
package com.sunsoft.study.tree;

/**
 * 树节点
 * 
 * @author Walker
 * @version 1.0.0.0
 */
public class TreeNode 
{
	/** 节点Id*/
	private String nodeId;
	/** 父节点Id*/
	private String parentId;
	/** 文本内容*/
	private String text;
	
	/**
	 * 构造函数
	 * 
	 * @param nodeId 节点Id
	 */
	public TreeNode(String nodeId)
	{
		this.nodeId = nodeId;
	}
	
	/**
	 * 构造函数
	 * 
	 * @param nodeId 节点Id
	 * @param parentId 父节点Id
	 */
	public TreeNode(String nodeId, String parentId)
	{
		this.nodeId = nodeId;
		this.parentId = parentId;
	}

	public String getNodeId() {
		return nodeId;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
}
