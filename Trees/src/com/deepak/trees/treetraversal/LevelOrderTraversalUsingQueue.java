package com.deepak.trees.treetraversal;

import java.util.LinkedList;
import java.util.Queue;

import com.deepak.trees.TreeNode;

public class LevelOrderTraversalUsingQueue 
{
	
	  public static void levelOrderTraverse(TreeNode root)
	  {
		  Queue<TreeNode> queue=new LinkedList<TreeNode>();
		  if(root!=null)
		      queue.add(root);
		  while(!queue.isEmpty()){
			  
			  TreeNode temp=queue.poll();
			  System.out.print(temp.data+", ");
			  if(temp.left!=null)
				  queue.add(temp.left);
			  
			  if(temp.right!=null)
				  queue.add(temp.right);
			  
		  }
		  
	  }
	  public static void spiralLevelOrderTraverse(TreeNode root)
	  {
		  Queue<TreeNode> queue=new LinkedList<TreeNode>();
		  boolean flipper=true;
		  if(root!=null)
		      queue.add(root);
		  while(!queue.isEmpty()){
			  
			  TreeNode temp=queue.poll();
			  System.out.print(temp.data+", ");
			  if(flipper)
			  {    if(temp.left!=null)
				       queue.add(temp.left);
			  
			       if(temp.right!=null)
				       queue.add(temp.right);
			  }
			  else
			  {

			       if(temp.right!=null)
				       queue.add(temp.right);
			       if(temp.left!=null)
				       queue.add(temp.left);
			  }
			  flipper=!flipper;
		  }
		  
	  }
	  
      public static void main(String[] args) 
      {
		 TreeNode root=new TreeNode(5);
		 root.left=new TreeNode(6);
         root.right=new TreeNode(8);
         root.left.left=new TreeNode(10);
         root.left.right=new TreeNode(4);
         root.right.left=new TreeNode(11);
         levelOrderTraverse(root);
         System.out.println("Spiral Level Order Traversal");
         spiralLevelOrderTraverse(root);
	  }
}
