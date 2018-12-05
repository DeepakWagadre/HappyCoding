package com.deepak.trees;

public class PrintLeafNodes 
{
	public static void printLeafNodes(TreeNode root)
	{
		if(root==null)
			return;
		if(root.left==null&&root.right==null)
		{
			System.out.println(root.data);
			return;
		}
		printLeafNodes(root.left);
		printLeafNodes(root.right);
	}
    public static void main(String[] args)
    {
	    TreeNode root=new TreeNode(5);
	    root.left=new TreeNode(6);
  		root.right=new TreeNode(8);
  		root.left.left=new TreeNode(10);
  		root.left.right=new TreeNode(4);
  		root.right.left=new TreeNode(11);
  		printLeafNodes(root);
	}
}
