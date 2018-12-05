package com.deepak.trees.treetraversal.practise;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import com.deepak.trees.TreeNode;

class QNode
{
	TreeNode node;
	int hd;
}
public class TopBottomVertical
{
     
	public static void printTopView(TreeNode root)
	{
		Queue<QNode> queue=new LinkedList<QNode>();
		Set<Integer> set=new HashSet<Integer>();
		QNode qNode=new QNode();
		qNode.hd=0;
		qNode.node=root;
		queue.add(qNode);
		while(!queue.isEmpty())
		{
			QNode temp=queue.poll();
			int hd=temp.hd;
		    if(!set.contains(hd))
		    {
		    	set.add(hd);
		    	System.out.println(temp.node.data);
		    }
		    if(temp.node.left!=null)
		    {
		    //	TreeNode lNode=new TreeNode();
		    //	lhd=hd-1;
		    //	lNode=new TreeNode(temp.left);
		   // 	queue.add(lNode);
		    }
		    if(temp.node.right!=null)
		    {
		    	QNode rNode=new QNode();
		    	rNode.hd=hd+1;
		    	rNode.node=new TreeNode(temp.node.right.data);
		    	queue.add(rNode);
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
