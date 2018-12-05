package com.deepak.trees;

public class NodesKDistantfromRoot 
{
	 public static void nodesAtKDistance(TreeNode root,int k)
	 {
		 if(root==null)
			 return;
		 if(k==0)
		 {
			 System.out.println(root.data);
			 return;
		 }
		 
		 nodesAtKDistance(root.left, k-1);
		 nodesAtKDistance(root.right, k-1);
	 }
      public static void main(String[] args)
      {
		  TreeNode root=new TreeNode(5);
		  root.left=new TreeNode(6);
		    root.right=new TreeNode(8);
		    root.left.left=new TreeNode(10);
		    root.left.right=new TreeNode(4);
		    root.right.left=new TreeNode(11);
		    nodesAtKDistance(root, 2);
      }
}
