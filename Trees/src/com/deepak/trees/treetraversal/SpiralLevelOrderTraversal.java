package com.deepak.trees.treetraversal;

import com.deepak.trees.TreeNode;

public class SpiralLevelOrderTraversal 
{
	  public static  int treeHeight(TreeNode root)
	  {
		  if(root ==null)
			  return 0;
		  if(root.left==null &&root.right==null)
			  return 1;
		  int ls=treeHeight(root.left);
		  int rs=treeHeight(root.right);
		  return Math.max(ls, rs)+1;
	  }
	  public static void spiralLevelOrderTraversal(TreeNode root,int level,boolean flag)
	  {
		   if(root==null)
			   return;
		 if(root.left==null&&root.right==null)
		   {
			   System.out.println(root.data);
			   return;
		   }
		   if(level==1)
		   {
			   System.out.println(root.data); 
			  
		   }
		   else if(level>1)
		   {
			   if(flag)
			   {
				   spiralLevelOrderTraversal(root.left,level-1, flag);
				   spiralLevelOrderTraversal(root.right, level-1, flag);
			   }
			   else
			   {
				   spiralLevelOrderTraversal(root.right,level-1,flag);
				   spiralLevelOrderTraversal(root.left,level-1,flag);
			   }
		   }
	  }
	  public static void main(String[] args) 
      {
	       TreeNode root=new TreeNode(5);
	       root.left=new TreeNode(6);
           root.right=new TreeNode(8);
           root.left.left=new TreeNode(10);
           root.left.right=new TreeNode(4);
           root.right.left=new TreeNode(11);
           int height=treeHeight(root);
           boolean flipper=false;
           for(int i=1;i<=height;i++)
           {
        	   spiralLevelOrderTraversal(root,i,flipper);
        	   flipper=!flipper;
           }
	   }
}
