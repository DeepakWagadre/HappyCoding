package com.deepak.trees;

public class RightViewOfATree
{
	public static int maxHeight=0;
	public static void rightView(TreeNode root,int cHeight)
	{
		if(root==null)
			return;
		if(cHeight>maxHeight)
		{
			System.out.println(root.data);
			maxHeight=cHeight;
		}
		rightView(root.right,cHeight+1);
		rightView(root.left,cHeight+1);
	}
  public static void main(String[] args) 
  {
		TreeNode root=new TreeNode(5);
		root.left=new TreeNode(7);
		root.right=new TreeNode(19);
		root.left.right=new TreeNode(15);
		root.right.left=new TreeNode(9);
		root.left.left=new TreeNode(10);
		root.right.right=new TreeNode(11);
		root.left.right.left=new TreeNode(151);
		rightView(root,1);
   }
}
