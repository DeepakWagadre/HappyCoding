package com.deepak.bst;

import com.deepak.trees.TreeNode;

public class LCAInBST 
{
	
	
	
	public static TreeNode  checkLCA(TreeNode root,int n1,int n2)
	{
		if(root==null)
			return root;
		if(root.data>n1&&root.data>n2)
			return checkLCA(root.left,n1,n2);
		if(root.data<n1&&root.data<n2)
			return checkLCA(root.right, n1, n2);
		return root;
	}
	
    public static void main(String[] args) 
    {
		TreeNode root=new TreeNode(15);
		root.left=new TreeNode(12);
		root.right=new TreeNode(18);
		root.left.left=new TreeNode(10);
        root.left.right=new TreeNode(13);
        root.right.left=new TreeNode(16);
        root.right.right=new TreeNode(20);
        TreeNode lca =checkLCA(root,12,13);
        System.out.println(lca.data);
	}
}
