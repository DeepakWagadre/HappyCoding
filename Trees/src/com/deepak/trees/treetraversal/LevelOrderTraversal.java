package com.deepak.trees.treetraversal;

import com.deepak.trees.TreeNode;

public class LevelOrderTraversal 
{
	//function to calculate TreeHeight
	public static int treeHeight(TreeNode root)
	{
		 if(root==null)
			 return 0;
		 if(root.left==null&&root.right==null)
			 return 1;
		 int ls=treeHeight(root.left);
		 int rs=treeHeight(root.right);
		 return(Math.max(ls, rs)+1);
	}
	
	public static void levelOrderTraversal(TreeNode root,int height)
	{
		if(root==null)
			return;
		if(root.left==null&&root.right==null)
		{
			System.out.println(root.data);
			return;
		}
		
		if(height==1)
		{
			System.out.println(root.data);
		}
		else if(height>1)
		{
			 levelOrderTraversal(root.left, height-1);
			 levelOrderTraversal(root.right, height-1);
			 
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
	              int treeHeight=treeHeight(root);
	              System.out.println("Tree Height is "+treeHeight);
	              
	              for(int i=1;i<=treeHeight;i++)
	              {
	            	  levelOrderTraversal(root, i);
	              }
	              
	              
       }
}
