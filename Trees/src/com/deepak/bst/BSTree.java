package com.deepak.bst;

import com.deepak.trees.RightViewOfATree;
import com.deepak.trees.TreeNode;

public class BSTree
{
	
	public static TreeNode insert(TreeNode root,int data)
	{
		if(root==null)
		{
			root=new TreeNode(data);
			return root;
		}
		if(root.data>data)
		{
			root.left=insert(root.left,data);
		}
		else if(root.data<data)
		{
			root.right=insert(root.right,data);
		}
		
		return root;
		
	}
	public static TreeNode delete(TreeNode root,int data)
	{
		if(root==null)
			return root;
		if(root.data>data)
		{
			root.left=delete(root.left,data);
		}
		else if(root.data<data)
		{
			root.right=delete(root.right,data);
		}
		
		//if root is same as node to be deleted
		else
		{
			//if root has no right or either one child.
			
			if(root.left==null)
				return root.right;
			else if(root.right==null)
				return root.left;
			// if root has both the child . 
			//Then find the lowest element in the right subtree ie. rightmost successor
			//or in-order successor
			root.data=rigthMostSuccessor(root.right);
			root.right=delete(root.right, root.data);
		}
		return root;
	}
	
	/**
	 * This method finds the in-order successor in a Tree
	 * The successor will be the leftmost element in the right subtree of a given tree.
	 * @param root
	 * @return
	 */
	public static int rigthMostSuccessor(TreeNode root)
	{
		int minVal=root.data;
		while(root!=null)
		{
			if(minVal>root.data)
				minVal=root.data;
			root=root.left;
		}
		System.out.println(minVal);
		return minVal;
	}
	
	public static void inOrderTraversal(TreeNode root)
	{
		if(root==null)
		     return;
		if(root.left==null&&root.right==null)
		{
		    System.out.print(root.data+",");
		    return;
		}
		inOrderTraversal(root.left);
		System.out.print(root.data+",");
		inOrderTraversal(root.right);
	}
	
     public static void main(String[] args) 
     {
    	 TreeNode root=new TreeNode(50);
	      insert(root,60);
	      insert(root,70);
	      insert(root,80);
	      insert(root,20);
	      insert(root,30);
	      insert(root,40);
	      inOrderTraversal(root);
	      System.out.println(" -> -> After Deletion >>>>>>>>>>");
	      delete(root,50);
	      inOrderTraversal(root);
	       
	 }
     
}
