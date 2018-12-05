package com.deepak.trees;

import java.util.Stack;

public class IterativePreOrderTraversal

{
	public static void  iterativeTreeTraversal(TreeNode root)
	{
          if(root==null)
        	  return;
          Stack stack=new Stack<TreeNode>();
          
          //push left element into the stack.
         while(root!=null)
         {
        	 stack.push(root);
        	 root=root.left;
         }
         
         while(stack.size()>0)
         {
        	 
        	 root=(TreeNode) stack.pop();
        	 System.out.println(root.data);
        	 if(root.right!=null)
        	 {
        	    root=root.right;
        	    while(root!=null)
        	    {
        		   stack.push(root);
        		   root=root.left;
        	    }
        	 }
        	 
         }
        
          
	}
    public static void main(String[] args) {	
		
    	TreeNode root=new TreeNode(5);
    	root.left=new TreeNode(3);
    	root.right=new TreeNode(7);
    	root.left.left=new TreeNode(8);
    	root.left.right=new TreeNode(9);
    	root.right.left=new TreeNode(11);
    	iterativeTreeTraversal(root);
    	
	}
}
