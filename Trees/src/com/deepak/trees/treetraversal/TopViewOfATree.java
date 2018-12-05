package com.deepak.trees.treetraversal;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import com.deepak.trees.TreeNode;


 class QItem
{
	int hd=0;
	TreeNode node;
	public QItem(int hd, TreeNode node) 
	{
		super();
		this.hd = hd;
		this.node = node;
	}
}

public class TopViewOfATree 
{
	
	public static void printTopView(TreeNode root)
	{
		if(root==null)
			return;
		
		Set<Integer> set=new HashSet<Integer>();
		Queue<QItem> queue=new LinkedList<QItem>();
		queue.add(new QItem(0,root));
		
		while(!queue.isEmpty())
		{
			QItem item=queue.poll();
		    int hd=item.hd;
		    TreeNode temp=item.node;
		    if(!set.contains(hd))
		    {
		    	set.add(hd);
		    	System.out.println(temp.data);
		    }
		    if(item.node.left!=null)
		    {
		    	queue.add(new QItem(hd-1,item.node.left));
		    }
		    if(item.node.right!=null)
		    {
		    	queue.add(new QItem(hd+1,item.node.right));
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
