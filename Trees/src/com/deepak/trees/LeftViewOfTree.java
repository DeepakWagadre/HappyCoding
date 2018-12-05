package com.deepak.trees;

public class LeftViewOfTree
{
     static int MAX_INT=0;
	public static int treeHeight(TreeNode root)
	{
		if(root==null)
			return 0;
		if(root.left==null&&root.right==null)
			return 1;
		int lheight=treeHeight(root.left);
		int rheight=treeHeight(root.right);
		return Math.max(lheight,rheight)+1;
	}
	 public static void leftView1(TreeNode root,int  level)
	 {
		 if(root==null)
			 return;
		
		 if(level==1)
		 {
			
			 System.out.println(root.data);
			 return;
		 }
		 else if(level>1)
		 {
			 
				 leftView1(root.left, level-1);	 
			 
			
				 leftView1(root.right,level-1);
			 
		 }
	 }
	 
	 public static void leftView2(TreeNode root,int height)
	 {
		 if(root==null)
			 return;
		 
		// height++;
		 if(height>MAX_INT)
		 {
			 System.out.println(root.data);
			 MAX_INT=height;
		 }
		 leftView2(root.left,height+1);
		 leftView2(root.right,height+1);
		
	 }
	
     public static void main(String[] args) 
     {
	      TreeNode root=new TreeNode(5);
	      root.left=new TreeNode(6);
	      root.right=new TreeNode(7);
	      root.left.left=new TreeNode(8);
	      root.left.right=new TreeNode(9);
	      root.left.right.left=new TreeNode(10);
	      int height=treeHeight(root);
	      for(int i=1;i<=height;i++)
	      {
	    	 // leftView1(root,i);
	      }
	      leftView2(root, 1);
	 }
}
