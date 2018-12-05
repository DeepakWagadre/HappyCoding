package com.deepak.trees;

/**
 * This class prints the max sum path in binary tree
 * @author dwagadre
 *
 */
public class MaxSumPath 
{
	    public static int maxSumPath(TreeNode root)
	    {
	    	if(root==null)
	    		return 0;
	    	int ls=maxSumPath(root.left);
	    	int rs=maxSumPath(root.right);
	    	return Math.max(ls,rs)+root.data;
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
	   		int max=maxSumPath(root);
	   		System.out.println("max path sum is "+max);
	   }
}
