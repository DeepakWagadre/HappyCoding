package com.deepak.trees;

public class TreeHeightDepth {
	
	public static int treeDepth(TreeNode root)
	{
		if(root==null)
			return 0;
		if(root.left==null&&root.right==null)
			return 1;
		int ld=treeDepth(root.left);
		int rd=treeDepth(root.right);
		return Math.max(ld, rd)+1;
	}

	public static void main(String[] args)
	{
		TreeNode root=new TreeNode(5);
	    root.left=new TreeNode(6);
		root.right=new TreeNode(8);
		root.left.left=new TreeNode(10);
		root.left.right=new TreeNode(4);
		root.right.left=new TreeNode(11);
		int depth=treeDepth(root);
		System.out.println("depth of the tree is "+depth);

	}

}
