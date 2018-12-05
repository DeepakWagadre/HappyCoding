package com.deepak.bst;

import com.deepak.trees.TreeNode;

public class DeleteNodeInBST 
{
	  
	public static TreeNode insert(TreeNode root,int data)
	{
	    if(root==null)
	    {	
	    	root=new TreeNode(data);
	    	return root;
	    }
	    if(root.data>data)
	    	root.left=insert(root.left,data);
	    if(root.data<data)
	    	 root.right=insert(root.right,data);
	    return root;
	     
	}
	
	public static TreeNode deleteBSTNode(TreeNode root,int data)
	{
		if(root==null)
			return root;
		if(root.data>data)
		{
			root.left=deleteBSTNode(root.left, data);
		}
		else if(root.data<data)
		{
			root.right=deleteBSTNode(root.right,data);
		}
		else
		{
		   if(root.data==data)
		  {
			if(root.left==null&&root.right==null)
				root=null;
			else if(root.left==null)
				root=root.right;
			else if(root.right==null)
				root=root.left;
			else
			{
				TreeNode inOrderSuccessor=getInorderSuccessor(root.right);
				root.data=inOrderSuccessor.data;
				root.right=deleteBSTNode(root.right,inOrderSuccessor.data);
			}
				
	  	  }
		}
		return root;
	}
	public static TreeNode getInorderSuccessor(TreeNode root)
	{
		if(root==null)
			return root;
		while(root.left!=null)
		{
			root=root.left;
		}
		return root;
	}
	public static void traverseBST(TreeNode root)
	{
		if(root==null)
			return;
		traverseBST(root.left);
		System.out.println(root.data);
		traverseBST(root.right);
	}
    public static void main(String[] args) {
		
    	TreeNode root=new TreeNode(6);
    	insert(root,16);
    	insert(root,17);
    	insert(root,4);
    	insert(root,3);
    	insert(root,18);insert(root,5);
    	traverseBST(root);
        deleteBSTNode(root,4);
        System.out.println("------------------------------------------------------------");
    	traverseBST(root);

    	
	}
}
