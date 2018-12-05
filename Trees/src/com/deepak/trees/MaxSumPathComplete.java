package com.deepak.trees;

public class MaxSumPathComplete
{
	public static int maxSumPath(TreeNode root)
	{
		if(root==null)
			return 0;
		int ls=maxSumPath(root.left);
		int rs=maxSumPath(root.right);
		int sumPathCurrent=Math.max(ls, rs)+root.data;
		
		int rootPathSum=Math.max(ls+rs+root.data,sumPathCurrent);
		return rootPathSum;
	}
	
   public static void main(String[] args)
   {
	    TreeNode root=new TreeNode(5);
	    root.left=new TreeNode(6);
 		root.right=new TreeNode(8);
 		root.left.left=new TreeNode(10);
 		root.left.right=new TreeNode(4);
 	    root.right.left=new TreeNode(-11);
 	    int maxSum=maxSumPath(root);
 	    System.out.println(maxSum);
    }    
}
