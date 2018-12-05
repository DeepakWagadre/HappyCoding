package com.deepak.trees;

import java.util.Stack;

/**
*  							   5
*    						  / \
*                            4   3
*  							/ \   \
*                          2   1   6
*                         / \
*                        14  15
*                        
*                        o/p - > 2,4,3 for k==1
*                        
 * @author dwagadre
 *
 */
public class NodesKDistantFromLeaf
{
	public static void nodesKDistanceFromLeaves(TreeNode root,int[] path,int pathLength,boolean[] visited,int k)
	{
		if(root==null)
			return;
		path[pathLength]=root.data;
		pathLength++;
		if(root.left==null&&root.right==null&&(pathLength-k-1)>=0&&visited[pathLength-k-1]==false)
		{
			System.out.println(path[pathLength-k-1]);
			visited[pathLength-k-1]=true;
		}
		nodesKDistanceFromLeaves(root.left, path, pathLength, visited, k);
		nodesKDistanceFromLeaves(root.right, path, pathLength, visited, k);
		
	}
	/*public static void nodesKDistanceFromLeaves(TreeNode root,int[] path,int pathLength,Stack<Integer> visited,int target)
	{
		if(root==null)
			return;
		path[pathLength]=root.data;
		pathLength++;
		
		nodesKDistanceFromLeaves(root.left, path, pathLength, visited, target);
		nodesKDistanceFromLeaves(root.right, path, pathLength, visited, target);
		
	}*/
	
	
     public static void main(String[] args)
     {
		TreeNode root=new TreeNode(5);
		root.left=new TreeNode(4);
		root.right=new TreeNode(3);
		root.left.left=new TreeNode(2);
		root.right.right=new TreeNode(6);
		root.left.right=new TreeNode(1);
		root.left.left.left=new TreeNode(14);
		root.left.left.right=new TreeNode(15);
		//nodesKDistanceFromLeaves(root,new int[100],0, new Stack<Integer>(), 1);
		
	 }
}
