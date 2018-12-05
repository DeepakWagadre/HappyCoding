package com.deepak.trees;

public class MaxElementInTree 
{
	public static int maxElem(TreeNode root)
	{
		if(root==null)
			return 0;
		int ls=maxElem(root.left);
		int rs=maxElem(root.right);
		
		return Math.max(Math.max(ls,rs),root.data);
	}
	
   public static void main(String[] args) 
   {
	    TreeNode root=new TreeNode(5);
	    root.left=new TreeNode(7);
		root.right=new TreeNode(19);
		root.left.right=new TreeNode(15);
		root.right.left=new TreeNode(9);
		root.left.left=new TreeNode(10);
		root.right.right=new TreeNode(11);
		root.left.right.left=new TreeNode(151);
		int max=maxElem(root);
		System.out.println(max);
   }
}
