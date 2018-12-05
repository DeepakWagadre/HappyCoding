package com.deepak.bst;

import com.deepak.trees.TreeNode;

public class MaxMinElemInBST
{
	
	public static int minElem(TreeNode root)
	{
		int minValue=0;
		
		while(root.left!=null)
		{
			
			root=root.left;
			minValue=root.data;
		}
		return minValue;
	}
	
	public static int maxElem(TreeNode root)
	{
		int maxValue=0;
		while(root.right!=null)
		{
			root=root.right;
			maxValue=root.data;
		}
		return maxValue;
	}
	
	
   public static void main(String[] args)
   {
	   TreeNode root=new TreeNode(50);
	      BSTree.insert(root,60);
	      BSTree.insert(root,70);
	      BSTree.insert(root,80);
	      BSTree.insert(root,20);
	      BSTree.insert(root,30);
	      BSTree.insert(root,40);
	      BSTree.inOrderTraversal(root);
	      System.out.println("");
	      int minElem=minElem(root);
	      int maxElem=maxElem(root);
	      System.out.println("Minimum Element is "+minElem);
	      System.out.println("Maximum Element is "+maxElem);
   }
}
