package com.deepak.trees;

public class DistanceFromRoot 
{
	   public static int distance(TreeNode root, int data,int level)
	   {
		   
		   if(root==null)
			   return -1;
		   if(root.data==data)
			   return level;
		   int leftLevel=distance(root.left,data,level+1);
		   int rightLevel=distance(root.right,data,level+1);
		   
		   return leftLevel!=-1?leftLevel:rightLevel;
	   }
	
       public static void main(String[] args)
       {
		    TreeNode root=new TreeNode(5);
		    root.left=new TreeNode(4);
		    root.right=new TreeNode(6);
		    root.left.left=new TreeNode(3);
		    root.right.left=new TreeNode(2);
		    root.right.right=new TreeNode(7);
		    root.left.left.left=new TreeNode(12);
		    
		    int distance=distance(root,6,0);
		    System.out.println(distance);
	   }
}
