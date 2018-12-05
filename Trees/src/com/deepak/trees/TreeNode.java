package com.deepak.trees;

public class TreeNode
{
     public int data;
     public TreeNode left;
     public TreeNode right;
     
     public int getData() {
		return data;
	}

	public void setData(int data) {
		this.data = data;
	}

	public TreeNode getLeft() {
		return left;
	}

	public void setLeft(TreeNode left) {
		this.left = left;
	}

	public TreeNode getRight() {
		return right;
	}

	public void setRight(TreeNode right) {
		this.right = right;
	}

	public TreeNode(int data)
     {
    	 this.data=data;
     }
}
