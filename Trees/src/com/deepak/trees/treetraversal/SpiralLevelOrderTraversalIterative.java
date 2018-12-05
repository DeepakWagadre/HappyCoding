package com.deepak.trees.treetraversal;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import com.deepak.trees.TreeNode;

public class SpiralLevelOrderTraversalIterative 

{
	public static void spiralTreeTraversal(TreeNode root)
	{
		if(root==null)
			return;
		Stack<TreeNode> s1=new Stack<TreeNode>();
		Stack<TreeNode> s2=new Stack<TreeNode>();
		s1.add(root);
        while(!s1.isEmpty()||s2.isEmpty())
        {
		      while(!s1.isEmpty())
		      {
		    	  TreeNode temp=s1.peek();
		    	  s1.pop();
			       System.out.println(temp.data);
			       if(temp.right!=null)
				       s2.add(temp.right);
			       if(temp.left!=null)
				       s2.add(temp.left);
			       
		       }
			while(!s2.isEmpty())
				{
					TreeNode temp=s2.peek();
					s2.pop();
					//s2.pop();
					System.out.println(temp.data);
					
					if(temp.left!=null)
						s1.add(temp.left);
					if(temp.right!=null)
						s1.add(temp.right);
				}
        }
	}
	
   public static void main(String[] args) {
	   TreeNode root=new TreeNode(5);
	   root.left=new TreeNode(8);
	   root.right=new TreeNode(9);
	   root.left.left=new TreeNode(7);
	   root.left.right=new TreeNode(12);
	   root.right.left=new TreeNode(18);
	   root.right.right=new TreeNode(3);
	   root.left.left.left=new TreeNode(15);
	   root.left.left.right=new TreeNode(10);
	   spiralTreeTraversal(root);
	 
    }
}
