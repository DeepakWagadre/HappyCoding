package com.deepak.trees;


public class CheckIfHeightBlancedTree {
	
	public static boolean  isBalanced(TreeNode root,TreeHeight height)
	{
		if(root==null)
		{
			height.height=0;
			return true;
		}
		TreeHeight lheight=new TreeHeight();
		TreeHeight rheight=new TreeHeight();
		boolean ls=isBalanced(root.left,lheight);
		boolean rs=isBalanced(root.right, rheight);
		
		if(Math.abs(lheight.height-rheight.height)>=2)
			return false;
		height.height=Math.max(lheight.height,rheight.height)+1;
		return ls&&rs;
	}
	
	public static void main(String[] args) 
	
	{
		    TreeHeight height=new TreeHeight();
	     	TreeNode root=new TreeNode(1);
	        root.left = new TreeNode(2);
	        root.right = new TreeNode(3);
	        root.left.left = new TreeNode(4);
	        root.left.right = new TreeNode(5);
	        root.right.right = new TreeNode(6);
	        root.left.left.left = new TreeNode(7);
	        root.left.left.left.left=new TreeNode(8);
	        if(isBalanced(root, height))
	        {
	        	System.out.println("tree is balanced");
	        }
	        else
	        {
	        	System.out.println("tree is not balanced");
	        }
	        
	}
}
