package com.deepak.trees;

public class MirrorImageOfATree 
{
	public static void mirrorImageOfTree(TreeNode root)
	{
		if(root==null)
			return;
		if(root.left==null&&root.right==null)
			return;
		
		mirrorImageOfTree(root.left);
		mirrorImageOfTree(root.right);
		TreeNode temp=null;
		temp=root.left;
		root.left=root.right;
		root.right=temp;
	}
	
	public static void inOrderTreeTraversal(TreeNode root)
	{
		if(root==null)
			return;
		if(root.left==null&&root.right==null)
		{
			System.out.print(root.data+",");
			return;
		}
		System.out.print(root.data+",");
		inOrderTreeTraversal(root.left);
		inOrderTreeTraversal(root.right);
	}
	
    public static void main(String[] args) {
    	TreeNode root=new TreeNode(5);
		root.left=new TreeNode(6);
	    root.right=new TreeNode(8);
	    root.left.left=new TreeNode(10);
	    root.left.right=new TreeNode(4);
	    root.right.left=new TreeNode(11);
	    inOrderTreeTraversal(root);
	    mirrorImageOfTree(root);
	    System.out.println();
	    System.out.println("after mirror image");
	    inOrderTreeTraversal(root);
	}
}
