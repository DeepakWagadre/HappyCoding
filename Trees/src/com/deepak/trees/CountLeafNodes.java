package com.deepak.trees;

public class CountLeafNodes {
	
	static int leafCount=0;
	public static int countLeafNodes(TreeNode root)
	{
		if(root==null)
			return 0;
		 if(root.left==null&&root.right==null)
		 {
			 return 1;
		 }
		 int ls=countLeafNodes(root.left);
		 int rs=countLeafNodes(root.right);
		 leafCount=ls+rs;
		 return leafCount;
		
	}
	public static void main(String[] args)
	{
		TreeNode root=new TreeNode(5);
		root.left=new TreeNode(4);
		root.right=new TreeNode(3);
		root.left.left=new TreeNode(2);
		root.right.left=new TreeNode(8);
		root.right.right=new TreeNode(6);
		root.left.right=new TreeNode(1);
		
		countLeafNodes(root);
		System.out.println(leafCount);
	}

}
