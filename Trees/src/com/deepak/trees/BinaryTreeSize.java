package com.deepak.trees;

public class BinaryTreeSize
{
    public static int treeSize1(TreeNode root)
    {
    	if(root==null)
    	       return 0;
    	if(root.left==null&&root.right==null)
    		  return 1;
    	int ls=treeSize1(root.left);
    	int rs=treeSize1(root.right);
    	return ls+rs+1;
    }
}
