package com.tree;

public class BtreeNode {
	private int value;
	private BtreeNode leftNode;
	private BtreeNode rightNode;
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public BtreeNode getLeftNode() {
		return leftNode;
	}
	public void setLeftNode(BtreeNode leftNode) {
		this.leftNode = leftNode;
	}
	public BtreeNode getRightNode() {
		return rightNode;
	}
	public void setRightNode(BtreeNode rightNode) {
		this.rightNode = rightNode;
	}



}
