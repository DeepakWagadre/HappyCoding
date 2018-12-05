package com.deepak.trees;

public class SumLeafNodes 
{
	   public static int sumLeafNodes(TreeNode root)
	   {
		   if(root==null)
			   return 0;
		   if(root.left==null&&root.right==null)
			   return root.data;
		   int ls=sumLeafNodes(root.left);
		   int rs=sumLeafNodes(root.right);
		   return ls+rs;
	   }
       public static void main(String[] args) 
       {
			TreeNode root=new TreeNode(5);
			root.left=new TreeNode(6);
		    root.right=new TreeNode(8);
		    root.left.left=new TreeNode(10);
		    root.left.right=new TreeNode(4);
		    root.right.left=new TreeNode(11);
		    int leafesSum=sumLeafNodes(root);
		    System.out.println(leafesSum);
	  }
}
