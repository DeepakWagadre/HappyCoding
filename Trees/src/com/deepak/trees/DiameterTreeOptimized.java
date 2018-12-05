package com.deepak.trees;

public class DiameterTreeOptimized
{
	public static int diameter(TreeNode root,TreeHeight height)
	{
		if(root==null)
			return 0;
		TreeHeight lheight=new TreeHeight();
		TreeHeight rheight=new TreeHeight();
		int lsDiameter=diameter(root.left,lheight);
		int rdDiameter=diameter(root.right,rheight);
		height.height=Math.max(lheight.height,rheight.height)+1;
		return Math.max((lheight.height+rheight.height)+1,Math.max(lsDiameter, rdDiameter));
		//return Math.max(height.height, Math.max(lsDiameter, rdDiameter));
	}
	
   public static void main(String[] args) 
   {
	      TreeNode root=new TreeNode(5);
	      root.left=new TreeNode(6);
	      root.right=new TreeNode(7);
	      root.left.left=new TreeNode(8);
	      root.left.right=new TreeNode(9);
	      root.right.left=new TreeNode(10);
	      root.right.left.right=new TreeNode(11);
	      int diameter=diameter(root, new TreeHeight());
	      System.out.println(diameter);
   }                                                        
}
