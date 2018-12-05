package com.deepak.trees;

public class TreeTraversal
{
    public static void preOrder(TreeNode root)
    {
    	if(root==null)
    		return;
    	if(root.left==null&&root.right==null)
    	{
    		System.out.print(root.data+",");
    		return;
    	}
    	System.out.print(root.data+",");
    	preOrder(root.left);
    	preOrder(root.right);
    }
	
    public static void inOrder(TreeNode root)
    {
    	
    	if(root==null)
    		return;
    	if(root.left==null&&root.right==null)
    	{
    		System.out.print(root.data+",");
    		return;
    	}
    	inOrder(root.left);
    	System.out.print(root.data+",");
    	inOrder(root.right);
    }
    
    public static void postOrder(TreeNode root)
    {
    	if(root==null)
    		return;
    	if(root.left==null&&root.right==null)
    	{
    		System.out.print(root.data+",");
    		return;
    	}
         postOrder(root.left);
         postOrder(root.right);
         System.out.print(root.data+",");
    }
	
	public static void main(String[] args) {
		
		TreeNode root=new TreeNode(5);
	    root.left=new TreeNode(6);
		root.right=new TreeNode(8);
		root.left.left=new TreeNode(10);
		root.left.right=new TreeNode(4);
		root.right.left=new TreeNode(11);
		System.out.println("PreOrder Traversal -> Root,Left,Right");
		preOrder(root);
		System.out.println();
		System.out.println("InOrder Traversal -> Left,Root,Right");
		inOrder(root);
		System.out.println();
		System.out.println("PostOrder Traversal -> Left,Right,Root");
		postOrder(root);
		
		
	}
}
