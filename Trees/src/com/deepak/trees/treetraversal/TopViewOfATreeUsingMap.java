package com.deepak.trees.treetraversal;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import com.deepak.trees.TreeNode;

public class TopViewOfATreeUsingMap 
{
	/**
	 * We need a instance with each Node added.This is the problem here in this method.
	 * @param root
	 */
	public static void printTopView(TreeNode root)
	{
		if(root==null)
			return;
		Map<Integer,Integer> map=new HashMap<Integer,Integer>();
		Queue<TreeNode> queue=new LinkedList<TreeNode>();
		queue.add(root);
		int hd=0;
		while(!queue.isEmpty())
		{
			TreeNode temp=queue.poll();
			if(!map.containsKey(hd))
			{
				map.put(hd, temp.data);
				System.out.println(temp.data);
			}
			if(temp.left!=null)
			{
			      hd=hd-1;
			      queue.add(temp.left);
			}
			if(temp.right!=null)
			{
				hd=hd+1;
				queue.add(temp.right);
			}
		}
		
		
	}
     public static void main(String[] args)
     {

		   TreeNode root=new TreeNode(1);
		   root.left=new TreeNode(2);
	       root.right=new TreeNode(3);
	       root.left.right=new TreeNode(4);
	       root.left.right.right=new TreeNode(5);
	       root.left.right.right.right=new TreeNode(6);
		   printTopView(root);
		
	   
     }
}
