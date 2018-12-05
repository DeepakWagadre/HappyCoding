package com.deepak.trees;

public class PrintSumOfAllPaths 
{
	
	public static void printAllPathSum(TreeNode root,int[] paths,int pathLength)
	{
		if(root==null)
			return;
		paths[pathLength]=root.data;
		pathLength++;
		if(root.left==null&&root.right==null)
		{
			printSum(paths,pathLength);
		}
		else
		{
		   printAllPathSum(root.left, paths, pathLength);
		   printAllPathSum(root.right,paths,pathLength);
		}
	}
	public static void printSum(int []arr,int pathLength)
	{
		int sum=0;
		for(int i=0;i<pathLength;i++)
		{
			sum+=arr[i];
		}
		System.out.println(sum+",");
	}
     public static void main(String[] args)
     {
	      TreeNode root=new TreeNode(5);
	      root.left=new TreeNode(4);
	      root.left.left=new TreeNode(2);
	      root.left.right=new TreeNode(1);
	      root.right=new TreeNode(3);
	      root.right.left=new TreeNode(15);
	      root.right.right=new TreeNode(8);
	      int [] paths=new int[1000];
	      printAllPathSum(root, paths,0);
	 }
 }
