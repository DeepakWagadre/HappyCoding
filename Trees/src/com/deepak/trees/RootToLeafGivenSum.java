package com.deepak.trees;

public class RootToLeafGivenSum 
{
      
	public static boolean isPathSumAvailable(TreeNode root, int sum)
	{
		if(root==null)
			return sum==0;
		else
		{
			boolean flag=false;
			int subSum=sum-root.data;
			if(root.left==null&&root.right==null&&sum==0)
				return true;
			
			/**
			 * both  works fine
			 */
			//flag=flag||isPathSumAvailable(root.left,subSum);
			//flag=flag||isPathSumAvailable(root.right, subSum);
			boolean flag1=isPathSumAvailable(root.left,subSum);
			 boolean flag2=isPathSumAvailable(root.right, subSum);
			return (flag1||flag2);
			
		}
		
	}
	
	
	public static void main(String[] args) 
	{
	     TreeNode root=new TreeNode(10);
	     root.left=new TreeNode(8);
	     root.right=new TreeNode(2);
	     root.left.left=new TreeNode(3);
	     root.left.right=new TreeNode(5);
	     root.right.left=new TreeNode(2);
	     boolean flag=isPathSumAvailable(root, 23);
	     System.out.println(flag);
	}
}
