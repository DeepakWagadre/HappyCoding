package com.deepak.trees;

import java.util.LinkedList;
import java.util.Queue;

public class LargestPathSum
{
	/**
	 *           5
	 *          / \
	 *         8   2
	 *        / \   \
	 *       3   5   2
	 * @param root
	 * @return
	 */
	public static int maxPathSum(TreeNode root)
	{ 
		if( root==null)
			return 0;
		int ls=maxPathSum(root.left);
		int rs=maxPathSum(root.right);
		return Math.max(ls,rs)+root.data;
	}
	
	/**
	 * 
	 * @param root
	 * @return
	 */
	public static int maxPathSum2(TreeNode root)
	{
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		int maxSum=0;
		if(root!=null)
		     queue.add(root);
		while(!queue.isEmpty())
		{
			TreeNode temp=queue.poll();
			
			maxSum+=temp.data;
			if(temp.left!=null&&temp.right==null)
				queue.add(temp.left);
			else if(temp.left==null&&temp.right!=null)
				queue.add(temp.right);
			else if(temp.left!=null&&temp.right!=null)
			{
				 if(temp.left.data>temp.right.data)
					   queue.add(temp.left);
				 else
					 queue.add(temp.right);
			}
		}
		return maxSum;
	}
	
     public static void main(String[] args)
     {
	     TreeNode root=new TreeNode(5);
	     root.left=new TreeNode(8);
	     root.right=new TreeNode(2);
	     root.left.left=new TreeNode(3);
	     root.left.right=new TreeNode(5);
	     root.right.left=new TreeNode(2);
	     int maxSum1=maxPathSum(root);
	     int maxSum2=maxPathSum2(root.left);
	     System.out.println("max path sum in this tree is "+maxSum1);
	     System.out.println("max path sum in this tree is "+maxSum2);
	     
	 }
}
